package com.example.study.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * Copyright (c) 2026 丁鑫.
 * All rights reserved.
 * Unauthorized copying of this file via any medium is strictly prohibited.
 * This code is proprietary and confidential.
 *
 * @author 丁鑫
 * @date 2026/8/9 18:45
 * @description TODO
 */
public class SlideWindows01 {
    /**
     * 滑动窗口题
     * 最长无重复子串
     */

    public static void main(String[] args) {
        String maxNumStr = "pwwkew";
        /**
         * 这是一个滑动窗口题，左右两个指针进行移动，最后计算出最长的子串
         * 1.将字符串转换成字符数组，进行遍历
         * 2.定义两个指针，左右指针。其中右指针可以用遍历的i代替，因为右指针每次遍历都加一
         * 3.定义一个set集合，set集合中的元素是不重复的
         * 4.如果遍历的当前元素，也就是右指针所指的元素，不在set中，则添加进set
         * 5.如果遍历的当前元素，在set中，则当前最长子串就要在这里终止，取得当前的最大值
         * 6.同时左指针往右移一位，重新开始计算最大值
         * 7.需要注意的是如果左指针移位后，如果当前元素在set中还是重复，需要继续移位，直到不再重复为止
         * 如同pwwkew字符串一样，两个ww，如果只判断一次，将p移出窗口，窗口中实际还是w，如果只判断一次，就会把第二个w添加进set中
         * 虽然set中只会存在一个w，但是窗口的指针位置是错误的，虽然是一个w，窗口指针位置会表示两位，多计算了一次，最终导致结果多1
         * 所以需要重复第7步。
         */
        char[] chars = maxNumStr.toCharArray();
        int left = 0;
        int max = 0;
        Set<Character> set = new HashSet<>();
        for (int right = 0; right < chars.length; right++) {
            while (set.contains(chars[right])) {
                set.remove(chars[left]);
                left++;
            }
            set.add(chars[right]);
            max = Math.max(max, right - left + 1);
        }
        System.out.println(max);
    }
}