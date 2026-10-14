package com.example.study.base.thread.create;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class UseImplCallable implements Callable {


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 创建runnable接口的实现类对象
        UseImplCallable callable = new UseImplCallable();
        // 创建线程对象，通过线程对象来开启线程，代理
        FutureTask<String> futureTask = new FutureTask<>(callable);
        new Thread(futureTask).start();
        System.out.println(futureTask.get());
    }

    @Override
    public Object call() throws Exception {
        for (int i = 0; i <100; i++) {
            System.out.println("我在学习多线程？？？" + i);
        }
        return "多线程执行成功";
    }
}
