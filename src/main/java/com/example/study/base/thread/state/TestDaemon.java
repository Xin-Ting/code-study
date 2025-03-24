package com.example.study.base.thread.state;

public class TestDaemon {

    public static void main(String[] args) {
        Daemon daemon = new Daemon();
        Thread thread = new Thread(daemon);
        thread.setDaemon(true); // 设置守护线程，默认为false
        thread.start();

        // 用户线程启动
        new Thread(new User()).start();
    }
}

class Daemon implements Runnable {
    @Override
    public void run() {
        while (true) {
            System.out.println("守护线程-------->");
        }
    }
}

class User implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            System.out.println("用户线程。。。。。。。");
        }
        System.out.println("---------------------------------------");
    }
}
