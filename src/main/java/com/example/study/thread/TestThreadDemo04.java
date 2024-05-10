package com.example.study.thread;

/**
 * 并发问题：多个线程同时操作同一资源，线程不安全
 */
public class TestThreadDemo04 implements Runnable {

    private int ticketNums = 10;

    @Override
    public void run() {

        while (true) {
            if (ticketNums <= 0) {
                break;
            }
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName() + "--->拿到了第" + ticketNums-- + "张票");
        }
    }

    public static void main(String[] args) {
        TestThreadDemo04 testThreadDemo04 = new TestThreadDemo04();
        // 操作的时同一个对象，所以会引起竞争问题
        new Thread(testThreadDemo04,"张三").start();
        new Thread(testThreadDemo04,"李四").start();
        new Thread(testThreadDemo04,"黄牛").start();

        // 操作不同对象，不会产生线程安全问题
//        new Thread(new TestThreadDemo04(),"张三").start();
//        new Thread(new TestThreadDemo04(),"李四").start();
//        new Thread(new TestThreadDemo04(),"黄牛").start();
    }
}
