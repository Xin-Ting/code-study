package com.example.study.base.thread;

public class PrintNumber {

    private static int number = 0;
    private static final Object LOCK = new Object();

    public static void main(String[] args) {

        Thread oddThread = new Thread(() -> {
            while (true) {
                synchronized (LOCK) {
                    // 不是奇数时，奇数线程等待
                    while (number < 100 && number % 2 == 0) {
                        try {
                            LOCK.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            return;
                        }
                    }

                    if (number >= 100) {
                        LOCK.notifyAll();
                        return;
                    }

                    System.out.println(
                            Thread.currentThread().getName()
                                    + "打印奇数：" + number
                    );

                    number++;
                    LOCK.notifyAll();
                }
            }
        }, "奇数线程-");

        Thread evenThread = new Thread(() -> {
            while (true) {
                synchronized (LOCK) {
                    // 不是偶数时，偶数线程等待
                    while (number < 100 && number % 2 == 1) {
                        try {
                            LOCK.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            return;
                        }
                    }

                    if (number >= 100) {
                        LOCK.notifyAll();
                        return;
                    }

                    System.out.println(
                            Thread.currentThread().getName()
                                    + "打印偶数：" + number
                    );

                    number++;
                    LOCK.notifyAll();
                }
            }
        }, "偶数线程-");

        oddThread.start();
        evenThread.start();
    }
}