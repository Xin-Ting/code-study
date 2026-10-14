package com.example.study.base.thread.create;

public class UseExtendThread extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("我在学习多线程----" + i);
        }
    }

    public static void main(String[] args) {
        UseExtendThread useExtendThread = new UseExtendThread();
        useExtendThread.start();

        // 主线程打印200
        for (int i = 0; i < 200; i++) {
            System.out.println(Thread.currentThread().getName()+"多线程学习哪家强？？？" + i);
        }
    }

}
