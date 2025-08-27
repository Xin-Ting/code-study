package com.example.study.base.sort;

import java.util.Arrays;

/**
 * 冒泡排序
 */
public class BubbleSort {
    public static void main(String[] args) {
        // 思路：化繁为简，先死后活
        int[] arr = {13, 9, -1, 10, 20, 2, -5, 11, 220, 45, 34, 89, -10};
        /**
         * 第一轮循环：找出最大的那个数并放在最后
         * 第1次比较：{9，13，-1，10，20}
         * 第2次比较：{9，-1，13，10，20}
         * 第3次比较：{9，-1，10，13，20}
         * 第4次比较：{9，-1，10，13，20}
         *
         * 第二轮循环：找出第二大的那个数并放在倒数第二个位置
         * 第1次比较：{-1，9，10，13，20}
         * 第2次比较：{-1，9，10，13，20}
         * 第3次比较：{-1，9，10，13，20}
         *
         * 第三轮循环：找出最三大的那个数并放在倒数第三个位置
         * 第1次比较：{-1，9，10，13，20}
         * 第2次比较：{-1，9，10，13，20}
         *
         * 第四轮循环：找出最四大的那个数并放在倒数第四个位置
         * 第1次比较：{-1，9，10，13，20}
         *
         * 先从内部开始写逻辑，再从外向内写逻辑
         */
//        int tmp = 0 ;
//        for (int j = 0; j < 4; j++ ) {
//            if (arr[j] > arr[j + 1]) {
//                tmp = arr[j];
//                arr[j] = arr[j + 1];
//                arr[j + 1] = tmp;
//            }
//        }
//        System.out.println("第一轮循环后的数组为："+Arrays.toString(arr));
//
//        for (int j = 0; j < 3; j++ ) {
//            if (arr[j] > arr[j + 1]) {
//                tmp = arr[j];
//                arr[j] = arr[j + 1];
//                arr[j + 1] = tmp;
//            }
//        }
//        System.out.println("第二轮循环后的数组为："+Arrays.toString(arr));
//
//        for (int j = 0; j < 2; j++ ) {
//            if (arr[j] > arr[j + 1]) {
//                tmp = arr[j];
//                arr[j] = arr[j + 1];
//                arr[j + 1] = tmp;
//            }
//        }
//        System.out.println("第三轮循环后的数组为："+Arrays.toString(arr));
//
//        for (int j = 0; j < 1; j++ ) {
//            if (arr[j] > arr[j + 1]) {
//                tmp = arr[j];
//                arr[j] = arr[j + 1];
//                arr[j + 1] = tmp;
//            }
//        }
//        System.out.println("第四轮循环后的数组为："+Arrays.toString(arr));


//        for (int i = 0; i < 4; i++) {
//            int tmp = 0 ;
//            for (int j = 0; j < 4-i; j++ ) {
//                if (arr[j] > arr[j + 1]) {
//                    tmp = arr[j];
//                    arr[j] = arr[j + 1];
//                    arr[j + 1] = tmp;
//                }
//            }
//            System.out.println("第"+(i+1)+"轮循环后的数组为："+Arrays.toString(arr));
//        }

        for (int i = 0; i < arr.length - 1; i++) {
            int tmp = 0;
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                }
            }
            System.out.println("第" + (i + 1) + "轮循环后的数组为：" + Arrays.toString(arr));
        }


    }


}
