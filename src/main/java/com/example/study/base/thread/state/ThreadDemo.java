package com.example.study.base.thread.state;

public class ThreadDemo {
    public static void main(String[] args)
            throws InterruptedException {

        Thread worker = new Thread(() -> {
            Thread current = Thread.currentThread();

            System.out.println(
                current.getName() + " 开始工作"
            );

            try {
                for (int i = 1; i <= 5; i++) {
                    if (current.isInterrupted()) {
                        System.out.println("检测到中断");
                        return;
                    }

                    System.out.println("处理第 " + i + " 项");
                    Thread.sleep(500);
                }
            } catch (InterruptedException e) {
                // sleep 被中断后会进入这里
                current.interrupt();
                System.out.println("睡眠时收到中断");
            } finally {
                System.out.println("线程结束");
            }
        }, "工作线程");

        worker.setUncaughtExceptionHandler((thread, error) -> {
            System.out.println(
                thread.getName() + " 出错：" + error.getMessage()
            );
        });

        System.out.println(worker.getState()); // NEW

        worker.start();

        System.out.println(worker.isAlive());  // 通常为 true

        Thread.sleep(1200);
        worker.interrupt();

        worker.join();

        System.out.println(worker.getState()); // TERMINATED
        System.out.println("主线程结束");
    }
}