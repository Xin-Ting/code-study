package com.example.study.base.thread.syn;

import java.util.concurrent.CopyOnWriteArrayList;

// 测试JUC安全类型的集合
public class SafeList {
    public static void main(String[] args) throws InterruptedException {
        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();

        for (int i = 0; i < 60000; i++) {
            new Thread(() -> {
                list.add(Thread.currentThread().getName());
            }).start();
        }
        Thread.sleep(3000);
        System.out.println(list.size());
    }

}
