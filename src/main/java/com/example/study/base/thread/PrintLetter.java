package com.example.study.base.thread;

/**
 * Copyright (c) 2026 丁鑫.
 * All rights reserved.
 * Unauthorized copying of this file via any medium is strictly prohibited.
 * This code is proprietary and confidential.
 *
 * @author 丁鑫
 * @date 2026/10/8 19:31
 * @description TODO
 */
public class PrintLetter {

    public static int order = 1;

    public static final Object LOCK = new Object();

    // 按顺序打印ABC
    public static void main(String args[]) {
        Thread threadA = new Thread(() -> {
            synchronized (LOCK) {
                while (order != 1) {
                    try {
                        LOCK.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                }

                System.out.println(Thread.currentThread().getName() + "打印字母A");
                // 通知其他线程
                order++;
                LOCK.notifyAll();
            }
        }, "线程A");

        Thread threadB = new Thread(() -> {
            synchronized (LOCK) {
                while (order != 2) {
                    try {
                        LOCK.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                }

                System.out.println(Thread.currentThread().getName() + "打印字母B");
                // 通知其他线程
                order++;
                LOCK.notifyAll();
            }
        }, "线程B");

        Thread threadC = new Thread(() -> {
            synchronized (LOCK) {
                while (order != 3) {
                    try {
                        LOCK.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                }

                System.out.println(Thread.currentThread().getName() + "打印字母C");
                // 通知其他线程
                order++;
                LOCK.notifyAll();
            }
        }, "线程C");

        threadA.start();
        threadB.start();
        threadC.start();
    }
}