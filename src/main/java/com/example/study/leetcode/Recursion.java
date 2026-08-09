package com.example.study.leetcode;

/**
 * Copyright (c) 2026 丁鑫.
 * All rights reserved.
 * Unauthorized copying of this file via any medium is strictly prohibited.
 * This code is proprietary and confidential.
 *
 * @author 丁鑫
 * @date 2026/8/9 11:47
 * @description TODO
 */
public class Recursion {
    // 递归练习
    public static void main(String[] args) {
        // 打印1～5
        printNumAdd(0, 5);
        System.out.println("=================");
        // 求和
        int sumNum = sumNum(1);
        System.out.println(sumNum);

    }

    public static void printNumAdd(int num, int max) {
        if (num > max) {
            return;
        }
        System.out.println(num);
        printNumAdd(num + 1, max);
    }

    public static int sumNum(int num) {
        if (num == 100) {
            return num;
        }
        return num +sumNum(num + 1);
    }

}