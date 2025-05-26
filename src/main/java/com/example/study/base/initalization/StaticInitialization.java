package com.example.study.base.initalization;

/**
 * 类的加载顺序：
 * 静态初始化
 * 1.父类的静态字段、静态代码块
 * 2.子类的静态字段、静态代码块
 * 实例初始化
 * 3.父类的非静态字段、非静态代码块
 * 4.父类的构造方法
 * 5.子类的非静态字段、非静态代码块
 * 6.子类的构造方法
 *
 * 注：
 * 静态初始化只加载一次，父类优先于子类，同类按顺序加载；依赖其他类时，被依赖的类优先初始化；
 * 静态初始化调用时才加载，不调用不加载：（new对象、访问静态变量或者静态方法、反射等）
 * 实例初始化每创建一个对象就会执行一次，父类实例变量和块->-父类构造方法->子类实例变量和块->子类构造方法
 */
public class StaticInitialization {

    public static void main(String[] args) {
        System.out.println("Creating new Cupboard() in main");
        new Cupboard();
        System.out.println("Creating new Cupboard() in main");
        new Cupboard();
        table.f2(1);
        cupboard.f3(1);
    }
    static Table table = new Table();
    static Cupboard cupboard = new Cupboard();

    /**
     * 输出结果：
     * Bowl(1)
     * Bowl(2)
     * Table()
     * f1(1)
     * Bowl(4)
     * Bowl(5)
     * Bowl(3)
     * Cupboard()
     * f1(2)
     * Creating new Cupboard() in main
     * Bowl(3)
     * Cupboard()
     * f1(2)
     * Creating new Cupboard() in main
     * Bowl(3)
     * Cupboard()
     * f1(2)
     * f2(1)
     * f3(1)
     */


}
