package com.example.study.base.object_.clone;

// 两个类互相引用，形成循环：A ↔ B
class A {
    public String name;
    public B b; // A 持有 B


}