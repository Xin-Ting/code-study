package com.example.study.base.thread.create;

import java.util.Timer;
import java.util.TimerTask;

public class UseTimer {
    public static void main(String[] args) {
        Timer timer = new Timer();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "9......");
            }
        }, 0, 1000);
    }
}