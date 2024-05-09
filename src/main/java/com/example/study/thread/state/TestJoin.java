package com.example.study.thread.state;

public class TestJoin implements Runnable{
    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            System.out.println("大小姐驾到，统统闪开"+i);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        TestJoin testJoin = new TestJoin();
        Thread thread = new Thread(testJoin);
        thread.start();

        for (int i = 0; i < 500; i++) {
            if (i == 200) {
                thread.join(); // 插队
            }
            System.out.println("主线程在行动"+i);
        }
    }
}
