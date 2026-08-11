package com.example.study.leetcode;

import java.util.Scanner;

/**
 * Copyright (c) 2026 丁鑫.
 * All rights reserved.
 * Unauthorized copying of this file via any medium is strictly prohibited.
 * This code is proprietary and confidential.
 *
 * @author 丁鑫
 * @date 2026/8/11 22:05
 * @description TODO
 */
public class ClimbDownstairs {
    /**
     * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
     * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
     * 示例 1：
     * 输入：n = 2
     * 输出：2
     * 解释：有两种方法可以爬到楼顶。
     * 1. 1 阶 + 1 阶
     * 2. 2 阶
     * 示例 2：
     * 输入：n = 3
     * 输出：3
     * 解释：有三种方法可以爬到楼顶。
     * 1. 1 阶 + 1 阶 + 1 阶
     * 2. 1 阶 + 2 阶
     * 3. 2 阶 + 1 阶
     * 提示：
     * 1 <= n <= 45
     */
    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("请输入1到45的正整数：");
//        int num = scanner.nextI nt();
        // 第一种：全是1
        int num = 45;
        int function = function(num);
        System.out.println(function);
        System.out.println("============");
        int[] memo = new int[num + 1];
        int result = function2(num, memo);
        System.out.println(result);
        System.out.println("============");
        int re = function3(num);
        System.out.println(re);


    }

    private static int function(int num) {
        if (num == 1) {
            return 1;
        }
        if (num == 2) {
            return 2;
        }
        return function(num - 1) + function(num - 2);
    }


    private static int function2(int num, int[] memo) {
        if (num == 1) {
            return 1;
        }
        if (num == 2) {
            return 2;
        }
        if (memo[num] != 0) {
            return memo[num];
        }
        memo[num] = function2(num - 1, memo) + function2(num - 2, memo);
        return memo[num];
    }

    private static int function3(int num) {
        if (num == 1) {
            return 1;
        }
        if (num == 2) {
            return 2;
        }
        int first = 1;
        int second = 2;
        for (int i = 3; i <= num; i++) {
            int current = second + first;
            first = second;
            second = current;
        }
        return second;
    }
}