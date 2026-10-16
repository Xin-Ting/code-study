package com.example.study.base.thread.syn;

/**
 * Copyright (c) 2026 丁鑫.
 * All rights reserved.
 * Unauthorized copying of this file via any medium is strictly prohibited.
 * This code is proprietary and confidential.
 *
 * @author 丁鑫
 * @date 2026/10/16 13:37
 * @description TODO
 */
public class Counter2 {
    // `final` 保证 `lock` 引用不会被重新赋值，避免不同调用意外使用不同的锁
    private final Object lock = new Object();
    private int count = 0;

    /**
     * 锁的是代码块，需要明确指定锁
     */
    public void increment() {
        synchronized (lock) {
            count++;
        }
    }

    public int getCount() {
        synchronized (lock) {
            return count;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Counter2 counter = new Counter2(); // 确保是同一个对象，使用的是同一把锁，如果是不同对象，锁不同

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                counter.increment();
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                counter.increment();
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println(counter.getCount()); // 2000
    }

}