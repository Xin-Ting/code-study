package com.example.study.leetcode;

import java.util.Arrays;
import java.util.Random;

/**
 * Copyright (c) 2026 丁鑫.
 * All rights reserved.
 * Unauthorized copying of this file via any medium is strictly prohibited.
 * This code is proprietary and confidential.
 *
 * @author 丁鑫
 * @date 2026/8/9 10:49
 * @description TODO
 */
public class Shortcut {

    public static void main(String[] args) {
        // 生成数组
        int[][] arrays = generateArrays();
        // 计算最短路径值
        int result = dfs(arrays, 0, 0);
        System.out.println(result);

    }



    public static int dfs(int[][] arrays, int i, int j) {
        int m = arrays.length;
        int n = arrays[0].length;
        // 到达终点
        if (i == m - 1 && j == n - 1) {
            return arrays[i][j];
        }
        // 到达最右边
        if (i == m-1) {
            return arrays[i][j] + dfs(arrays, i, j + 1);
        }
        // 到达最下边
        if (j == n-1) {
            return arrays[i][j] + dfs(arrays, i+1, j);
        }
        int right = dfs(arrays, i + 1, j);
        int down = dfs(arrays, i, j + 1);

        return arrays[i][j] + Math.min(right, down);
    }

    private static int[][] generateArrays() {
        // 1.生成一个m x n的矩阵
        System.out.println("============生成二维数组==========");
        int[][] arr = new int[3][3];
        Random random = new Random();
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                arr[i][j] = random.nextInt(10);
            }
        }

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j]);
                System.out.print(" ");
                if (j == arr[i].length - 1) {
                    System.out.println("");
                }
            }
        }
        System.out.println("============分割线==========");
        return arr;
    }

}