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
    // 被static修饰的成员变量,在JDK 1.7之前存放在方法区(HotSpot虚拟机永久代实现,永久代是独立于堆的内存区域),
    // 在JDK 1.7之后,字符串常量池和静态变量迁入堆中
    // 在JDK 1.8及之后,类的元数据移至元空间(本地内存),而static变量本身存储在堆中的Class对象相关区域。
    static int b = 20;

    public void method() {
        // 局部变量，存放在栈中
        int c = 30;
//        static int d = 40; // 编译错误，不能在方法中使用 static 修饰局部变量
    }

    Integer e = 50;


}
