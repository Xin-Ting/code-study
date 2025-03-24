package com.example.study.base.thread.syn;

import java.util.ArrayList;

public class UnsafeList {
    public static void main(String[] args) throws InterruptedException {
        ArrayList<String> list = new ArrayList<>();
        // 多个线程同一瞬间可能添加到同一个位置，导致最后size不是循环的次数
        for (int i = 0; i < 60000; i++) {
            new Thread(()->{
                list.add(Thread.currentThread().getName());
            }).start();
        }
        Thread.sleep(3000);
        System.out.println(list.size());
    }

}
