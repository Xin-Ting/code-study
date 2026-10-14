package com.example.study.base.thread.create;

public class UseImplRunnable implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("我在学习多线程----" + i);
        }
    }

    public static void main(String[] args) {
        // 创建runnable接口的实现类对象
        UseImplRunnable useImplRunnable = new UseImplRunnable();
        // 创建线程对象，通过线程对象来开启线程，代理
//        Thread thread = new Thread(testThreadDemo03);
//        thread.start();

        new Thread(useImplRunnable).start();


        for (int i = 0; i < 200; i++) {
            System.out.println("多线程学习哪家强？？？" + i);
        }
    }
}
