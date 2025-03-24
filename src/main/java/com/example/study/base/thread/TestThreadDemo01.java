package com.example.study.base.thread;

public class TestThreadDemo01 extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("我在学习多线程----" + i);
        }
    }

    public static void main(String[] args) {
        TestThreadDemo01 testThreadDemo01 = new TestThreadDemo01();
        testThreadDemo01.start();


        for (int i = 0; i < 200; i++) {
            System.out.println("多线程学习哪家强？？？" + i);
        }
    }

}
