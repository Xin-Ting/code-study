package com.example.study.base.thread.create;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

public class UseForkJoinPool {
    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        forkJoinPool.execute(() -> {
            System.out.println(Thread.currentThread().getName() + "10A......");
        });
        // 并行流中主线程也可能会参与，parallelStream() 默认使用公共 ForkJoinPool
        // 并行流中foreach不能保证元素的处理顺序
        List<String> list = Arrays.asList("10B......","10C......","10D......","10E......");
        list.parallelStream().forEach(x -> System.out.println(Thread.currentThread().getName() + x));
    }
}