package com.example.study.base.recursion;

/**
 * 递归
 */
public class Recursion01 {

    public static void main(String[] args) {
        Test test = new Test();
        /**
         * 递归调用自身
         * 栈：main方法
         *    test(4)-->test(4-1)
         *    test(3)-->test(3-1)
         *    test(2)-->输出n=2，然后返回test(3)的方法栈，test(2)方法弹出栈
         *    test(3)分支结束，该方法中n=3，打印输出结果，返回test(4)方法栈，test(3)方法弹出栈
         *    test(4)方法结束，该方法中n=4，打印输出结果，返回main方法栈，test(4)方法弹出栈
         */
        test.test(4);

        /**
         * 阶乘
         * 栈：main方法
         *    factorial(5)-->factorial(4)*5
         *    factorial(4)-->factorial(3)*4
         *    factorial(3)-->factorial(2)*3
         *    factorial(2)-->factorial(1)*2
         *    factorial(1)-->返回1
         *    factorial(2)-->1*2 返回2
         *    factorial(3)-->2*3 返回6
         *    factorial(4)-->6*4 返回24
         *    factorial(5)-->24*5 返回120
         *    main方法结束，打印输出结果，返回main方法栈，factorial(5)方法弹出栈
         */
         int total = test.factorial(5);
         System.out.println("total=" + total);

         }


         }

         class Test {
         public void test(int n) {
         if (n > 2) {
         test(n - 1);
         }
         System.out.println("n=" + n);
         }

         public int factorial(int n) {
         if (n == 1) {
         return 1;
         }
         return n * factorial(n - 1);
         }
         }
