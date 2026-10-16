package com.example.study.base.thread.safe;

/**
 * Copyright (c) 2026 丁鑫.
 * All rights reserved.
 * Unauthorized copying of this file via any medium is strictly prohibited.
 * This code is proprietary and confidential.
 *
 * @author 丁鑫
 * @date 2026/10/16 11:10
 * @description TODO
 */
public class WatchFlag2 {

    private static volatile boolean flag = false;

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            flag = true;
            System.out.println("我修改了flag为true......");
        });

        Thread t2 = new Thread(() -> {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if (flag) {
                System.out.println("flag为true，我开始工作了......");
                return;
            }
            System.out.println("flag为false，我开始摸鱼了......");
        });
        t1.start();
        t2.start();
    }
}