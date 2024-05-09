package com.example.study.thread.state;

/**
 * 观测线程状态
 */
public class TestState {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(()->{
            // 线程阻塞5秒钟
            for (int i = 0; i < 5; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            // 阻塞结束
            System.out.println("-------");
        });
        Thread.State state = thread.getState(); // 获取线程状态
        System.out.println(state); //NEW

        thread.start(); //启动线程
        state = thread.getState(); // 获取线程状态
        System.out.println(state); //RUN

        while (state != Thread.State.TERMINATED) { //只要不终止就一直输出
            Thread.sleep(100);
            state = thread.getState(); // 更新线程状态
            System.out.println(state); //输出状态
        }

    }
}
