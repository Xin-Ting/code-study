package com.example.study.leetcode;

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
public class Shortcut2 {

    public static void main(String[] args) {
        // 生成数组
        int[][] arrays = generateArrays();

        //
        int result1 = calculateShortcut(arrays, 0, 0);
        int result2 = calculateShortcut2(arrays);

        System.out.println(result1);
        System.out.println(result2);

    }

    /**
     * 这种方式利用递归把每一步往右或者往下的路径都递归出来求和，最终取最小值
     * 但是往右或者往下取递归路径时
     * 后续的步骤会重复计算。比如往右或者往左到达中间的位置，后续往右或者往左会重复计算一遍。
     * 效率不是最高的，低阶的矩阵还行，一旦数字更大，就会计算很多遍。
     *
     * @param arrays
     * @param i
     * @param j
     * @return
     */
    private static int calculateShortcut(int[][] arrays, int i, int j) {
        int m = arrays.length;
        int n = arrays[i].length;
        // 到达终点，返回终点的值
        if (i == m - 1 && j == n - 1) {
            return arrays[i][j];
        }
        // 到达最右边，返回往下的值
        if (i == m - 1) {
            return arrays[i][j] + calculateShortcut(arrays, i, j + 1);
        }
        // 到达最下边，返回往右的值
        if (j == n - 1) {
            return arrays[i][j] + calculateShortcut(arrays, i + 1, j);
        }
        //右
        int right = arrays[i][j] + calculateShortcut(arrays, i + 1, j);
        //下
        int down = arrays[i][j] + calculateShortcut(arrays, i, j + 1);
        return Math.min(right, down);
    }

    /**
     * 建立一个新的二维数组，用于保存每一步之后的累计路径最小值
     * 比如，原二维数组为
     * 1  2  1
     * 3  5  3
     * 4  4  1
     * <p>
     * 因为每一步只能往右或者往下，终点值是固定的，
     * 所以到达终点前的一步，无论是往右还是往下，累计的值应该取最小的
     * 即上图，到达第三列的3或者第三行的第二个4，要去的值应该是最小的，加上最后的1，最终结果才是最小的
     * 以此类推，如果到达第三列的3值是最小的，那么到达第一列的1和第二行的5，也应该找到最小累计值的路径。
     * <p>
     * 其实，本质上，这个方法就是找出最优路径。每走到一个格子，只保留“到这个格子为止最优秀的那条路径”的累计值。
     * 而方法一是把所有完整路径全部算出来，再比较得到最小值。
     * 所以，我们建立一个新的数组，保存累计当前路径的最小值
     * 比如说，从起点开始先往右累计相加后得到如下
     * 1  3  4
     * 从起点开始往下累计相加得到
     * 1
     * 4
     * 8
     * 最后我们的数组为
     * 1  3  4
     * 4  ？ ？
     * 8  ？ ？
     * 即从起点开始往右或者往左的累计值。现在比如说，从起点开始，我们到达原数组5的位置，那么该处前一步只能是1——>2,或者1——>3
     * 那么这一步的累计最小值就是3+5和4+5的最小值，为8；
     * 然后再第二行3的位置，这一步的前一步，只能是2——>1或者2——>5这个位置,我们刚刚算出来到5的位置最小值为8，
     * 所以这一步的累计最小值只能是4+3和8+3的最小值，得到7
     * 以此类推，我们不断计算，得到
     * 1      3        4
     * 4  min(8,9)   min(8,7)
     * 8  min(12,12) min(8,13)
     * 最终得到结果是8
     *
     * 总结：
     * 1. dp[i][j] = 从左上角到当前位置的最小路径和
     * 2. 第一行只能从左边来
     * 3. 第一列只能从上面来
     * 4. 其他位置：
     *    dp[i][j] = grid[i][j] + min(上, 左)
     *
     * @param arrays
     * @return
     */
    private static int calculateShortcut2(int[][] arrays) {
        int m = arrays.length;
        int n = arrays[0].length;

        int[][] dp = new int[m][n];
        // 起点
        dp[0][0] = arrays[0][0];
        // 第一列
        for (int i = 1; i < m; i++) {
            dp[i][0] = dp[i - 1][0] + arrays[i][0];
        }
        // 第一行
        for (int j = 1; j < n; j++) {
            dp[0][j] = dp[0][j - 1] + arrays[0][j];
        }
        // 其余位置，重点是这一步，原数组当前位置的值加上新数组前一步最小值，矩阵里前一步的值就是前一步的累计值。
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = arrays[i][j] + Math.min(dp[i-1][j], dp[i][j-1]);
            }

        }
        return dp[m-1][n-1];
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