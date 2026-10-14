package com.example.study.base.thread.create;

import java.util.concurrent.*;

public class UseExecutorService {
    public static void main(String[] args) {
        ExecutorService poolA = Executors.newFixedThreadPool(2);
        poolA.execute(() -> {
            System.out.println(Thread.currentThread().getName() + "4A......");
        });
        poolA.shutdown();

        // 又或者自定义线程池
        ThreadPoolExecutor poolB = new ThreadPoolExecutor(2, 3, 0,
                TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(3),
                Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());
        poolB.submit(() -> {
            System.out.println(Thread.currentThread().getName() + "4B......");
        });
        poolB.shutdown();
    }
}