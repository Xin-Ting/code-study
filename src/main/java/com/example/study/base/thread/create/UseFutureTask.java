package com.example.study.base.thread.create;

import java.util.concurrent.FutureTask;

public class UseFutureTask {
    public static void main(String[] args) {
        FutureTask<String> futureTask = new FutureTask<>(() -> {
            System.out.println("7......");
            return "zhuZi";
        });
        new Thread(futureTask).start();
    }
}