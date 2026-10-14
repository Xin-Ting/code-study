package com.example.study.base.thread;

/**
 * Copyright (c) 2026 丁鑫.
 * All rights reserved.
 * Unauthorized copying of this file via any medium is strictly prohibited.
 * This code is proprietary and confidential.
 *
 * @author 丁鑫
 * @date 2026/10/14 10:43
 * @description TODO
 */
public class PayMoney {

    private static int amount_a = 35;
    private static int amount_b = 35;

    // 两个线程同时扣款
    public static void main(String[] args) throws InterruptedException {
        // 线程A，扣款10元
        Thread threadA = new Thread(() -> {
            deduct(10, "已扣款10元");
        }, "线程A");

        // 线程B，扣款30元
        Thread threadB = new Thread(() -> {
            deduct(30, "已扣款30元");
        }, "线程B");

        threadA.start();
        threadB.start();

        threadA.join();
        threadB.join();

        System.out.println("未加锁余额：" + amount_a);
        System.out.println("-----------------------------------");

        // 线程C，扣款10元
        Thread threadC = new Thread(() -> {
            synchronizedDeduct(10, "已扣款10元");
        }, "线程C");

        // 线程D，扣款30元
        Thread threadD = new Thread(() -> {
            synchronizedDeduct(30, "已扣款30元");
        }, "线程D");

        threadC.start();
        threadD.start();

        threadC.join();
        threadD.join();

        System.out.println("加锁后余额：" + amount_b);

    }

    private static void deduct(int x, String template) {
        // 不加锁，两个线程可能都会扣款成功，实际余额不足以支付两个线程金额
        if (amount_a >= x) {
            try {
                Thread.sleep(1000); //仅用于放大并发问题
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            amount_a = amount_a - x;
            System.out.println(Thread.currentThread().getName() + template);
        } else {
            System.out.println(Thread.currentThread().getName() + "余额不足");
        }
    }

    private static synchronized void synchronizedDeduct(int x, String template) {
        // 加锁，该方法，同一时间只允许一个线程持锁进入
        if (amount_b >= x) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            amount_b = amount_b - x;
            System.out.println(Thread.currentThread().getName() + template);
        } else {
            System.out.println(Thread.currentThread().getName() + "余额不足");
        }
    }

}