package com.example.study.base.thread.state;

import java.time.LocalDateTime;
import java.util.Date;

//模拟倒计时
public class TestSleep2 {
    public static void main(String[] args) throws InterruptedException {
        // 模拟倒计时
        turnDown();
    }

    public static void turnDown() throws InterruptedException {
        int num = 10;
        while (true) {
            Thread.sleep(1000);
            System.out.println(num--);
            if (num <= 0) {
                break;
            }
        }
    }
}
