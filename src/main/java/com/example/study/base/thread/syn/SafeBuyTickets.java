package com.example.study.base.thread.syn;

/**
 * 线程不安全
 * 买票:会产生重复票和负数票问题
 */
public class SafeBuyTickets implements Runnable {
    // 票数
    private int ticketNums = 10;
    // 标志位
    boolean flag = true;

    @Override
    public void run() {
        while (flag) {
            buy();
            try {
                // 将sleep方法移出是因为sleep不会释放锁，然后cpu执行太快了，导致一直是第一个线程在跑，或者将票数放大
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args) {
        SafeBuyTickets unsafeBuyTickets = new SafeBuyTickets();

        // 操作的时同一个对象，所以会引起竞争问题
        new Thread(unsafeBuyTickets, "张三").start();
        new Thread(unsafeBuyTickets, "李四").start();
        new Thread(unsafeBuyTickets, "黄牛").start();

    }
    // synchronized 同步方法，锁的是this
    private synchronized void buy() {
        if (ticketNums <= 0) {
            flag = false;
            return;
        }

        System.out.println(Thread.currentThread().getName() + "买到第" + ticketNums-- + "张票");
    }
}
