package com.example.study.thread.syn;

/**
 * 线程不安全
 * 买票:会产生重复票和负数票问题
 */
public class UnsafeBuyTickets implements Runnable {
    // 票数
    private int ticketNums = 10;
    // 标志位
    boolean flag = true;

    @Override
    public void run() {
        while (flag) {
            if (ticketNums <= 0) {
                flag = false;
                return;
            }

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            System.out.println(Thread.currentThread().getName() + "买到第" + ticketNums-- + "张票");
        }

    }

    public static void main(String[] args) {
        UnsafeBuyTickets unsafeBuyTickets = new UnsafeBuyTickets();

        // 操作的时同一个对象，所以会引起竞争问题
        new Thread(unsafeBuyTickets,"张三").start();
        new Thread(unsafeBuyTickets,"李四").start();
        new Thread(unsafeBuyTickets,"黄牛").start();

    }
}
