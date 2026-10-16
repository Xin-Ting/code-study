package com.example.study.base.thread.safe;

/**
 * Copyright (c) 2026 丁鑫.
 * All rights reserved.
 * Unauthorized copying of this file via any medium is strictly prohibited.
 * This code is proprietary and confidential.
 *
 * @author 丁鑫
 * @date 2026/10/16 11:38
 * @description TODO
 */
public class OrderDispose {

    static int data = 0;
    static volatile boolean ready = false;

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 100; i++) {
            Thread threadA = new Thread(() -> {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                // 线程 A
                data = 42;
                ready = true;
            });

            Thread threadB = new Thread(() -> {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                // 线程 B
                if (ready) {
                    System.out.println(data);
                }
            });
            threadA.start();
            threadB.start();
            threadA.join();
            threadB.join();
        }

    }
}