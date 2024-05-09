package com.example.study.thread.state;

import com.example.study.thread.TestThreadDemo04;

/**
 * 模拟网络延时：放大问题的发生性
 */
public class TestSleep implements Runnable{

    private int ticketNums = 10;

    @Override
    public void run() {

        while (true) {
            if (ticketNums <= 0) {
                break;
            }
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName() + "--->拿到了第" + ticketNums-- + "张票");
        }
    }

    public static void main(String[] args) {
        TestThreadDemo04 testThreadDemo04 = new TestThreadDemo04();

        new Thread(testThreadDemo04,"张三").start();
        new Thread(testThreadDemo04,"李四").start();
        new Thread(testThreadDemo04,"黄牛").start();
    }
}
