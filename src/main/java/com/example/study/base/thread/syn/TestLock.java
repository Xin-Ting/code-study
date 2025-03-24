package com.example.study.base.thread.syn;

import java.util.concurrent.locks.ReentrantLock;

// 测试可重入锁
public class TestLock {

    public static void main(String[] args) {
        LockBuyTickets tickets = new LockBuyTickets();

        // 操作的时同一个对象，所以会引起竞争问题
        new Thread(tickets, "张三").start();
        new Thread(tickets, "李四").start();
        new Thread(tickets, "黄牛").start();
    }

}

class LockBuyTickets implements Runnable {
    // 票数
    private int ticketNums = 10;

    private final ReentrantLock lock = new ReentrantLock();

    @Override
    public void run() {
        while (true) {
            lock.lock(); // 加锁
            try {
                if (ticketNums <= 0) {
                    break;
                }
                System.out.println(Thread.currentThread().getName() + "买到第" + ticketNums-- + "张票");

            } finally {
                lock.unlock(); // 解锁
            }

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    }
}
