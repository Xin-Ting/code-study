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
 * @date 2026/7/28 22:23
 * @description TODO
 */
public class CheckPassword {
    /**
     * 密码要求：
     * 1.长度超过8位
     * 2.包括大小写字母.数字.其它符号，以上四种至少三种
     * 3.不能有长度大于2的包含公共元素的子串重复（注：其他符号不含空格或换行）
     * 数据范围：输入的字符串长度满足1≤n≤100
     * 输入描述：
     * 一组字符串。
     * 输出描述：
     * 如果符合要求输出：OK，否则输出NG
     */
    public static void main(String[] args) {
        String password = "abcdefg768$%abc";
        if (isValidPassword(password)) {
            System.out.println("ok");
        } else {
            System.out.println("NG");
        }
    }

    private static boolean isValidPassword(String password) {
        // 1:长度超过8位,必须大于8
        if (password == null || password.length() <= 8) {
            return false;
        }
        // 2.包括大小写字母.数字.其它符号，以上四种至少三种
        int countType = countType(password);
        if (countType < 3) {
            return false;
        }
        // 3.不能有长度大于2的包含公共元素的子串重复（注：其他符号不含空格或换行）
        // abcabc 分析：长度大于2，那就是最少为3，如果有3个连续的子串重复则不通过
        // abc，bca，cab，abc 不符合
        // 不能重复，数据结构里不能重复的可以用Set，连续截取长度为3的子串添加进Set，如果有重复的则不符合要求
        boolean result = checkSubStr(password);
        return result;
    }

    private static boolean checkSubStr(String password) {
        Set<String> set = new HashSet<>();
        for (int i = 0; i <= password.length() - 3; i++) {
            // i = 0 ,abc
            // i = 1 ,bca
            // i = 2 ,cab
            // i = 3 ,abc
            String substring = password.substring(i, i + 3);
            if (set.contains(substring)) {
                return false;
            }
            set.add(substring);
        }
        return true;
    }

    private static int countType(String password) {
        int count = 0;
        char[] charArray = password.toCharArray();
        boolean hasLower = false;
        boolean hasUpper = false;
        boolean hasDigit = false;
        boolean hasOther = false;
        for (char c : charArray) {
            if (Character.isLowerCase(c)) {
                // 是否小写字母
                hasLower = true;
            } else if (Character.isUpperCase(c)) {
                // 是否大写字母
                hasUpper = true;
            } else if (Character.isDigit(c)) {
                // 是否数字字符
                hasDigit = true;
            } else {
                hasOther = true;
            }
        }
        if (hasLower) {
            count++;
        }
        if (hasUpper) {
            count++;
        }
        if (hasDigit) {
            count++;
        }
        if (hasOther) {
            count++;
        }
        return count;
    }
}