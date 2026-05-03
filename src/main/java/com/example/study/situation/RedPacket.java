package com.example.study.situation;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 手气红包实现（线程安全 + 二倍均值算法）
 */
public class RedPacket {

    public static void main(String[] args) throws InterruptedException {
        // 1. 红包参数
        BigDecimal totalAmount = new BigDecimal("100.00"); // 总金额 10 元
        int totalPeople = 5; // 5 个人抢

        // 2. 生成红包金额（核心算法）
        Queue<BigDecimal> redPackets = generateRedPackets(totalAmount, totalPeople);
        System.out.println("红包已生成，共 " + totalPeople + " 个，总金额：" + totalAmount + " 元");
        System.out.println("红包列表：" + redPackets + "\n");

        // 3. 多线程抢红包
        CountDownLatch latch = new CountDownLatch(totalPeople); // 等待所有线程抢完
        for (int i = 1; i <= totalPeople; i++) {
            int userId = i;
            new Thread(() -> {
                BigDecimal money = redPackets.poll(); // 原子获取
                if (money != null) {
                    System.out.println("用户" + userId + "抢到：" + money + " 元");
                } else {
                    System.out.println("用户" + userId + "手慢了，红包抢完了！");
                }
                latch.countDown();
            }).start();
        }

        latch.await(); // 等待所有抢完
        System.out.println("\n红包抢完，剩余：" + redPackets.size() + " 个");
    }

    /**
     * 二倍均值算法生成手气红包
     */
    private static Queue<BigDecimal> generateRedPackets(BigDecimal totalAmount, int totalPeople) {
        Queue<BigDecimal> queue = new ConcurrentLinkedQueue<>();
        BigDecimal remainAmount = totalAmount;
        int remainPeople = totalPeople;

        ThreadLocalRandom random = ThreadLocalRandom.current();

        for (int i = 0; i < totalPeople - 1; i++) {
            // 随机范围：0.01 ~ 剩余金额 / 剩余人数 * 2
            BigDecimal max = remainAmount.divide(new BigDecimal(remainPeople), 2, RoundingMode.DOWN).multiply(new BigDecimal(2));
            // 生成随机金额：0.01 ~ max
            BigDecimal randomMoney = BigDecimal.valueOf(random.nextDouble()).multiply(max.subtract(new BigDecimal("0.01"))).add(new BigDecimal("0.01"));
            // 保留两位小数
            randomMoney = randomMoney.setScale(2, RoundingMode.DOWN);

            queue.add(randomMoney);
            remainAmount = remainAmount.subtract(randomMoney);
            remainPeople--;
        }

        // 最后一个人拿走剩余所有
        queue.add(remainAmount.setScale(2, RoundingMode.DOWN));
        return queue;
    }
}