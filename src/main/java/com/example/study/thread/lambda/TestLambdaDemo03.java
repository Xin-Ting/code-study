package com.example.study.thread.lambda;

/**
 * 总结：前提式函数式接口
 * 1.lambda表达式只有一行代码的情况下可以简化成一行，如果有多行，就需要用代码块包裹；
 * 2.多个参数也可以去掉参数类型，要去掉就都去掉，但必须加上括号
 */
public class TestLambdaDemo03 {
    public static void main(String[] args) {
        NoArgs lambda1 = () -> System.out.println("无参数lambda表达式");
        lambda1.noArgs();

        SingleArg lambda2 = a -> System.out.println("单个参数一行lambda表达式");
        lambda2.singleArg(2);

        SingleArg lambda3 = a -> {
            a += a;
            System.out.println("单个参数,多行代码lambda表达式" + a);
        };
        lambda3.singleArg(2);


        MultiArgs lambda4 = ((x, y) -> System.out.println("多参一行lambda表达式"));
        lambda4.multiArgs(1, 3);

        MultiArgs lambda5 = ((x, y) -> {
            int total = x + y;
            System.out.println("多参多行代码lambda表达式" + total);
        });
        lambda5.multiArgs(1, 3);

        MultiArgsDifferentType lambda6 = ((x, y) -> {
            String arg = y + x;
            System.out.println("多参多行代码lambda表达式" + arg);
        });
        lambda6.multiArgsDifferentType(1, "test");
    }
}

interface NoArgs {
    void noArgs();
}


interface SingleArg {
    void singleArg(int a);
}


interface MultiArgs {
    void multiArgs(int x, int y);
}

interface MultiArgsDifferentType {
    void multiArgsDifferentType(int x, String y);
}
