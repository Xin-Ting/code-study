package com.example.study.base.thread;

/**
 * 龟兔赛跑：路程100米，兔子要睡觉，乌龟不睡觉，看谁先到重点
 */
public class Race implements Runnable {

    private static String winner;

    @Override
    public void run() {
        for (int i = 0; i <= 100; i++) {

            if ("兔子".equals(Thread.currentThread().getName()) && i % 10 == 0) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            boolean flag = gameOver(i);
            if (flag) {
                break;
            }

            System.out.println(Thread.currentThread().getName() + "跑了" + i + "米");
        }
    }

    private boolean gameOver(int steps) {
        // 判断是否有胜利者
        if (winner != null) { // 已经存在胜利者
            return true;
        }
        if (steps >= 100) {
            winner = Thread.currentThread().getName();
            System.out.println("胜利者是：" + winner);
            return true;
        }
        return false;

    }

    public static void main(String[] args) {
        Race race = new Race();

        Thread t1 = new Thread(race, "乌龟");
        Thread t2 = new Thread(race, "兔子");

        t1.start();
        t2.start();

    }
}
