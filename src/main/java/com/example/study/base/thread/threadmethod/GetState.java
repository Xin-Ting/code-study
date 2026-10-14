package com.example.study.base.thread.threadmethod;

/**
 * Copyright (c) 2026 丁鑫.
 * All rights reserved.
 * Unauthorized copying of this file via any medium is strictly prohibited.
 * This code is proprietary and confidential.
 *
 * @author 丁鑫
 * @date 2026/10/14 13:51
 * @description TODO
 */
public class GetState {

    public static void main(String[] args) throws InterruptedException {
        Object lock = new Object();

        Thread thread = new Thread(() -> {
            System.out.println("开启多线程");

            synchronized (lock) {
                try {
                    System.out.println("子线程开始等待");
                    lock.wait(1000);
                    System.out.println("子线程恢复执行");
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }

            System.out.println(
                    "子线程状态：" + Thread.currentThread().getState()
            );
        });

        System.out.println(thread.getState()); // NEW

        thread.start();

        // 这里只是用于演示，实际项目不应该依赖 sleep 协调线程
        Thread.sleep(100);

        System.out.println(thread.getState()); // 通常为 TIMED_WAITING

        synchronized (lock) {
            lock.notifyAll();
        }

        thread.join();

        System.out.println(thread.getState()); // TERMINATED
    }
}