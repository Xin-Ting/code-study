package com.example.study.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

/**
 * Copyright (c) 2026 丁鑫.
 * All rights reserved.
 * Unauthorized copying of this file via any medium is strictly prohibited.
 * This code is proprietary and confidential.
 *
 * @author 丁鑫
 * @date 2026/8/9 17:56
 * @description TODO
 */
public class StackForTest01 {
    /**
     * 题目要求判断 `()[]{}` 是否正确闭合。
     * <p>
     * 关键点：
     * <p>
     * - 左括号遇到后入栈；
     * - 右括号遇到后，必须和**最近的左括号**匹配；
     * - 如果遇到右括号时栈为空，说明没有对应左括号；
     * - 最后栈必须为空，才代表所有括号都闭合。
     */

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入一个只包含()[]{}字符串：");
        String input = scanner.next();
        /**
         * 已知：这道题要用栈来实现，输入的字符串只包含()[]{}
         * 左括号必须有对应的右括号，右括号必须有对应左括号
         * 左右括号正确闭合才是正确
         * 所以：
         * 我们把左括号添加进栈里，如果下一个遇到的是右括号，则弹出栈里最上面的值
         * 如果与右括号匹配，则正确，如果不匹配，则不正确
         * 直到字符串遍历结束
         * 需要注意：栈中元素是否为空的场景
         */
        Deque<Character> deque = new ArrayDeque<>();
        char[] chars = input.toCharArray();
        boolean flag = true;
        for (char aChar : chars) {
            // 处理左括号
            if ('(' == aChar || '{' == aChar || '[' == aChar) {
                deque.push(aChar);
            } else {
                //处理右括号
                // 栈中没有元素，则代码没有左括号，直接错误
                if (deque.isEmpty()) {
                    flag = false;
                    break;
                }
                Character pop = deque.pop();
                // 类型不匹配
                if (')' == aChar && '(' != pop) {
                    flag = false;
                    break;
                }
                if ('}' == aChar && '{' != pop) {
                    flag = false;
                    break;
                }
                if (']' == aChar && '[' != pop) {
                    flag = false;
                    break;
                }
            }
        }
        // 判断栈中元素有没有全部弹出，不为空则不正确
        if (!deque.isEmpty()) {
            flag = false;
        }
        System.out.println(flag ? "TRUE" : "NG");

    }

}