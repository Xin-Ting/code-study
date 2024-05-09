package com.example.study.thread.staticproxy;

/**
 * 静态代理模式总结：
 * 真实对象和代理对象都要实现同一个接口
 * 代理对象要代理真实角色
 * <p>
 * 好处：
 * 代理对象可以做很多真实对象做不了的事情
 * 真实对象专注做自己的事情
 */
public class TestStaticProxy {
    public static void main(String[] args) {
        // 线程静态代理
        new Thread(() -> System.out.println("我是线程真实对象")).start();

//        You you = new You();
//        WeddingCompany weddingCompany = new WeddingCompany(you);
//        weddingCompany.marry();
        new WeddingCompany(new You()).marry();
    }

}

interface Marry {
    void marry();
}

class You implements Marry {

    // 真实角色，你要结婚
    @Override
    public void marry() {
        System.out.println("我们结婚啦！");
    }
}

/**
 * 婚庆公司，代理对象，帮助你结婚
 */
class WeddingCompany implements Marry {
    //代理谁--》真实目标对象
    private Marry target;

    public WeddingCompany(Marry target) {
        this.target = target;
    }

    @Override
    public void marry() {
        before();
        this.target.marry();
        after();
    }

    void before() {
        System.out.println("结婚前，布置现场");
    }

    void after() {
        System.out.println("结婚后，结算尾款");
    }
}