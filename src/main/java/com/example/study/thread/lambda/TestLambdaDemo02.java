package com.example.study.thread.lambda;

public class TestLambdaDemo02 {
    // 3.静态内部类
    static class Love2 implements ILove {

        @Override
        public void love(String name) {
            System.out.println("i love you" + name);
            System.out.println(name + "do you love me？");
        }
    }

    public static void main(String[] args) {
        ILove love = new Love();
        love.love("张三");

        love = new Love2();
        love.love("李四");

        // 4.局部内部类
        class Love3 implements ILove {

            @Override
            public void love(String name) {
                System.out.println("i love you" + name);
                System.out.println(name + "do you love me？");
            }
        }
        love = new Love3();
        love.love("王五");


        //5.匿名内部类：没有类的名称，必须借助接口或者父类
        love = new ILove() {
            @Override
            public void love(String name) {
                System.out.println("i love you" + name);
                System.out.println(name + "do you love me？");
            }
        };
        love.love("赵六");

        // 6.用lambda简化
        love = name -> {
            System.out.println("i love you" + name);
            System.out.println(name + "do you love me？");
        };
        love.love("田七");

    }
}

//1.定义一个函数式接口
interface ILove {
    void love(String name);
}

//2.实现类
class Love implements ILove {

    @Override
    public void love(String name) {
        System.out.println("i love you" + name);
        System.out.println(name + "do you love me？");
    }
}
