package com.example.study.thread.syn;

// 死锁问题
public class DeadLock {
    public static void main(String[] args) {

        new MakeUp(0, "白雪公主").start();
        new MakeUp(1, "女王").start();

    }
}

class Lipstick {

}

class Mirror {

}

class MakeUp extends Thread {

    static Lipstick lipstick = new Lipstick();

    static Mirror mirror = new Mirror();

    int choice;

    String girlName;

    MakeUp(int choice, String girlName) {
        this.choice = choice;
        this.girlName = girlName;
    }

    @Override

    public void run() {
        try {
//            makeUp();
            makeUp2();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void makeUp() throws InterruptedException {
        /**
         * 开启两个线程：选项1的线程先持有口红的锁，睡眠1秒钟；选项非0的线程持有了镜子的锁，休眠2秒钟；
         * 1秒后线程1唤醒后想要持有镜子的锁，此时镜子的锁被线程2持有
         * 且此时线程1持有口红锁还在同步代码块中无法释放，线程1就一直等待线程2释放镜子的锁
         * 线程2在2秒钟后唤醒，想要持有线程1的口红的锁，因为还在同步代码块中，线程2无法释放镜子的锁，且无法获取口红锁
         * 线程1和线程2就一直处于僵持状态，这就是死锁
         */
        if (choice == 0) {
            synchronized (lipstick) {
                System.out.println(girlName + "获得了口红的锁");
                Thread.sleep(1000);

                synchronized (mirror) {
                    System.out.println(girlName + "获得了镜子的锁");
                }
            }
        } else {
            synchronized (mirror) {
                System.out.println(girlName + "获得了镜子的锁");
                Thread.sleep(2000);

                synchronized (lipstick) {
                    System.out.println(girlName + "获得了口红的锁");
                }
            }
        }
    }

    // 死锁问题解决
    private void makeUp2() throws InterruptedException {
        /**
         * 开启两个线程：选项1的线程先持有口红的锁，睡眠1秒钟；选项非0的线程持有了镜子的锁，休眠2秒钟；
         * 1秒后线程1唤醒后同步代码块执行结束，释放口红的锁，并想要持有镜子的锁，此时镜子的锁被线程2持有，线程1等待；
         * 线程2在2秒钟后唤醒同步代码块执行结束，释放镜子的锁，线程1拿到镜子的锁，执行完代码并释放镜子的锁；
         * 与此同时线程2拿到口红的锁，执行完代码并释放口红的锁；执行结束
         *
         */
        if (choice == 0) {
            synchronized (lipstick) {
                System.out.println(girlName + "获得了口红的锁");
                Thread.sleep(1000);
            }

            synchronized (mirror) {
                System.out.println(girlName + "获得了镜子的锁");
            }
        } else {
            synchronized (mirror) {
                System.out.println(girlName + "获得了镜子的锁");
                Thread.sleep(2000);
            }

            synchronized (lipstick) {
                System.out.println(girlName + "获得了口红的锁");
            }
        }
    }
}
