package com.example.study.thread.comm;

public class TestProducerConsumerDemo01 {
    public static void main(String[] args) throws InterruptedException {
        Container container = new Container();
        new Thread(new Producer(container)).start();
        new Thread(new Consumer(container)).start();
    }
}

// 生产者
class Producer implements Runnable {

    Container container;

    public Producer(Container container) {
        this.container = container;
    }

    @Override

    public void run() {
        for (int i = 1; i <= 100; i++) {
            try {
                container.push(new Product(i));
                System.out.println("生产了" + i + "个产品");
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }


    }
}

// 消费者
class Consumer implements Runnable {
    Container container;

    public Consumer(Container container) {
        this.container = container;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 100; i++) {
            try {
                Product pop = container.pop();
                System.out.println("消费了" + pop.id + "个产品");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

}

// 产品
class Product {
    int id;

    public Product(int id) {
        this.id = id;
    }

}

// 容器
class Container {

    // 容器大小为10的数组
    Product[] products = new Product[10];

    // 计数器
    int count = 0;

    // 生产者生产产品
    synchronized void push(Product product) throws InterruptedException {
        // 判断容器有没有满，如果满了，生产者等待
        if (count == products.length) {
            this.wait();
        }
        // 如果没有满，生产者生产产品，放入容器
        products[count] = product;
        count++;

        // 通知消费者消费
        this.notifyAll();

    }

    //消费者消费产品
    synchronized Product pop() throws InterruptedException {
        // 判断容器是不是空的，如果空的，消费者等待
        if (count == 0) {
            this.wait();
        }
        // 如果不是空的，消费者拿走产品
        count--; // 先--，因为数组是从0索引开始的，防止下标越界
        Product product = products[count];

        // 通知生产者生产
        this.notifyAll();
        return product;
    }

}