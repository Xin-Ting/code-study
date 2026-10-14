package com.example.study.base.thread.create;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class UseCompletableFuture {


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(()->{
            for (int i = 0; i < 100; i++) {
                System.out.println(Thread.currentThread().getName()+"我在学习多线程"+i);
            }
            return "多线程执行成功";
        });

        System.out.println(future.join());

    }


}
