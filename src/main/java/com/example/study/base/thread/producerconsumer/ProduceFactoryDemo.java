package com.example.study.base.thread.producerconsumer;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (c) 2026 丁鑫.
 * All rights reserved.
 * Unauthorized copying of this file via any medium is strictly prohibited.
 * This code is proprietary and confidential.
 *
 * @author 丁鑫
 * @date 2026/10/14 14:12
 * @description TODO
 */
public class ProduceFactoryDemo {

    private static final Object LOCK = new Object();


    /**
     * 使用 `wait/notifyAll` 实现一个容量为 10 的生产者—消费者队列
     * @param args
     */
    public static void main(String[] args) {
        List<Product> products = new ArrayList<>();
        Thread producer = new Thread(() -> {
            try {
                new Producer(products).produce();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Thread consumer = new Thread(() -> {
            try {
                new Consumer(products).consume();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        producer.start();
        consumer.start();
    }


    static class Product {
        // 仅用于表示展品对象
    }

    // 生产者
    static class Producer {
        private List<Product> products;

        public Producer(List<Product> products) {
            this.products = products;
        }

        public void produce() throws InterruptedException {
            while (true) {
                synchronized (LOCK) {
                    // 生产队列是否满了,满了等待消费者消费
                    while (products.size() >= 10) {  //判断条件必须用while，if可能会因为虚假唤醒导致判断失效
                        LOCK.wait();
                    }
                    // 生产产品
                    products.add(new Product());
                    System.out.println("生产者生产了一个产品，一共有" + products.size() + "个产品");
                    // 通知消费者消费
                    LOCK.notifyAll();
                }
                Thread.sleep(100); // 仅用于展示控制台效果，生产更快些
            }

        }
    }

    static class Consumer {
        private List<Product> products;

        public Consumer(List<Product> products) {
            this.products = products;
        }

        public void consume() throws InterruptedException {
            while (true) {
                synchronized (LOCK) {
                    // 消费者队列是否空的,空的等待生产者生产
                    while (products.isEmpty()) {
                        LOCK.wait();
                    }
                    // 消费产品,直接移除第一个，随便哪个产品都行
                    products.remove(0);
                    System.out.println("消费者消费了一个产品，还剩" + products.size() + "个产品");
                    // 通知生产者生产
                    LOCK.notifyAll();
                }
                Thread.sleep(300); // 仅用于展示控制台效果
            }

        }
    }
}