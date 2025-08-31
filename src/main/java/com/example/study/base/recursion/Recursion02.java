package com.example.study.base.recursion;

public class Recursion02 {
    public static void main(String[] args) {
        Test02 test02 = new Test02();
        int count = test02.fibonacci(8);
        System.out.println(count);

    }
}

class Test02 {
    /**
     * 斐波那契数列
     * 1 1 2 3 5 8 13 21...
     * n = 1 , 1
     * n = 2, 1
     * n = 3, 2 = 1 + 1
     * n = 4, 3 = 2 + 1
     * n = 5, 5 = 3 + 2
     */
    public int fibonacci(int n) {
        if (n < 1) {
            throw new RuntimeException("n不能小于1");
        }
        if (n == 1 || n == 2) {
            return 1;
        }else {
            return fibonacci(n - 1) + fibonacci(n - 2);
        }
    }
}