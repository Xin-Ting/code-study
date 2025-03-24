package com.example.study.base.datatype;

/**
 * 注意：基本数据类型存放在栈中是一个常见的误区！
 * 基本数据类型的存储位置取决于它们的作用域和声明方式。
 * 如果它们是局部变量，那么它们会存放在栈中；
 * 如果它们是成员变量，那么它们会存放在堆/方法区/元空间中
 */
public class BaseDataType {
    // 成员变量，存放在堆中
    int a = 10;
    // 被 static 修饰的成员变量，JDK 1.7 及之前位于方法区，1.8 后存放于元空间，均不存放于堆中。
    // 变量属于类，不属于对象。
    static int b = 20;

    public void method() {
        // 局部变量，存放在栈中
        int c = 30;
//        static int d = 40; // 编译错误，不能在方法中使用 static 修饰局部变量
    }

}
