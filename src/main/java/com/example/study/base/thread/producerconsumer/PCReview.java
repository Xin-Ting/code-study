package com.example.study.base.thread.producerconsumer;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;

/**
 * Copyright (c) 2026 丁鑫.
 * All rights reserved.
 * Unauthorized copying of this file via any medium is strictly prohibited.
 * This code is proprietary and confidential.
 *
 * @author 丁鑫
 * @date 2026/10/16 09:49
 * @description TODO
 */
public class PCReview {

    private static final int CAPACITY = 10;

    private static final Object LOCK = new Object();

    public static void main(String[] args) {
        Queue<String> deque = new ArrayDeque<>();
        Thread producer = new Thread(() -> {
            try {
                new Producer(deque).produce();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupted();
            }
        }, "生产者");

        Thread consumer = new Thread(() -> {
            try {
                new Consumer(deque).consume();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupted();
            }
        }, "消费者");

        producer.start();
        consumer.start();


    }

    static class Producer {

        private Queue<String> queues;

        public Producer(Queue<String> queues) {
            this.queues = queues;
        }

        public void produce() throws InterruptedException {
            while (true) {
                synchronized (LOCK) {
                    // 队列满了，等待
                    while (queues.size() >= CAPACITY) {
                        LOCK.wait();
                    }
                    // 队列没满，生产产品
                    queues.offer("产品");
                    System.out.println(Thread.currentThread().getName() + "生产了一个产品，还剩：" + queues.size() + "个产品");

                    // 通知消费
                    LOCK.notifyAll();
                }
                Thread.sleep(100);
            }

        }

    }

    static class Consumer {
        private Queue<String> queues;

        public Consumer(Queue<String> queues) {
            this.queues = queues;
        }

        public void consume() throws InterruptedException {
            while (true) {
                synchronized (LOCK) {
                    // 队列空了，等待
                    while (queues.isEmpty()) {
                        LOCK.wait();
                    }
                    // 队列不空，消费
                    queues.poll();
                    System.out.println(Thread.currentThread().getName() + "消费了一个产品，还剩：" + queues.size() + "个产品");

                    // 通知生产
                    LOCK.notifyAll();
                }
                Thread.sleep(300);
            }
        }
    }


}