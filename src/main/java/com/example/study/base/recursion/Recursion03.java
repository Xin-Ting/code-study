package com.example.study.base.recursion;

import java.util.Arrays;

public class Recursion03 {
    public static void main(String[] args) {

        int[][] map = new int[8][7];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                map[0][j] = 1;
                map[7][j] = 1;
                map[i][0] = 1;
                map[i][6] = 1;
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        map[2][1] = 1;
        map[2][2] = 1;
        new MiGong().findWay(map, 1, 1);
        System.out.println("最终结果为：");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }

    }

}

class MiGong {
    /**
     * 模拟迷宫
     * 1表示墙，0表示空地
     * 1. 创建二维数组模拟迷宫 int[][] map = new int[8][7];最上最下为墙，最左最右为墙
     * 2. 模拟迷宫的入口，起点（1，1）
     * 3. 模拟迷宫的出口，终点（6，5）
     * 4. 1表示墙不可以走，0表示空地可以走，2表示可以走，3表示已经走过不能走
     * 5. 上下左右移动
     */
    public boolean findWay(int[][] map, int i, int j) {
        if (map[6][5] == 2) {
            // 走到终点返回true
            return true;
        } else if (map[i][j] == 0) {
            // 0表示可以走
            map[i][j] = 2;
            if (findWay(map, i + 1, j)) {
                //下
                return true;
            } else if (findWay(map, i, j + 1)) {
                //右
                return true;
            } else if (findWay(map, i - 1, j)) {
                //上
                return true;
            } else if (findWay(map, i, j - 1)) {
                // 左
                return true;
            } else {
                // 表示该点无法走
                map[i][j] = 3;
                return false;
            }

        } else {
            // 1,2,3表示不可以走，直接返回false
            return false;
        }

    }
}

