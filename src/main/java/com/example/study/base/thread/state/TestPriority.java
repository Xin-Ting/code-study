package com.example.study.base.thread.state;

public class TestPriority implements Runnable {
    public static void main(String[] args) throws InterruptedException {
        // 主线程 默认优先级
        System.out.println(Thread.currentThread().getName() + "--->" + Thread.currentThread().getPriority());


        TestPriority testPriority = new TestPriority();
        Thread t1 = new Thread(testPriority, "t1");
        Thread t2 = new Thread(testPriority, "t2");
        Thread t3 = new Thread(testPriority, "t3");
        Thread t4 = new Thread(testPriority, "t4");
        Thread t5 = new Thread(testPriority, "t5");

        t2.setPriority(1);
        t3.setPriority(10);
        t4.setPriority(7);
        t5.setPriority(9);

        Thread.sleep(1000);

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "--->" + Thread.currentThread().getPriority());
    }
}
