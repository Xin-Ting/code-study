package com.example.study.base.thread.safe;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Copyright (c) 2026 丁鑫.
 * All rights reserved.
 * Unauthorized copying of this file via any medium is strictly prohibited.
 * This code is proprietary and confidential.
 *
 * @author 丁鑫
 * @date 2026/10/16 10:18
 * @description TODO
 */
public class CountNumber {
    private static int count1 = 0;
    private static int count2 = 0;

    public void addCount() {
        count1++;
    }

    public synchronized void synchronizedAddCount() {
        count2++;
    }

    public static void main(String[] args) throws InterruptedException {
        CountNumber countNumber1 = new CountNumber();
        Thread threadA = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                countNumber1.addCount();
            }
        });

        Thread threadB = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                countNumber1.addCount();
            }
        });

        threadA.start();
        threadB.start();
        threadA.join();
        threadB.join();
        System.out.println("count1:" + count1);

        System.out.println("——————————");

        CountNumber countNumber2 = new CountNumber(); //要用同一个实例
        Thread threadC = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                countNumber2.synchronizedAddCount();
            }
        });

        Thread threadD = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                countNumber2.synchronizedAddCount();
            }
        });

        threadC.start();
        threadD.start();
        threadC.join();
        threadD.join(); //让主线程等待工作线程执行结束，并保证能看到它们的写入结果，不然线程没执行完，主线程就打印结果了
        System.out.println("count2:" + count2);


        AtomicInteger count3 = new AtomicInteger(0);
        Thread threadE = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                count3.incrementAndGet();
            }
        });

        Thread threadF = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                count3.incrementAndGet();
            }
        });

        threadE.start();
        threadF.start();
        threadE.join();
        threadF.join(); //让主线程等待工作线程执行结束，并保证能看到它们的写入结果，不然线程没执行完，主线程就打印结果了
        System.out.println("count3:" + count3);

    }
}