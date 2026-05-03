package com.example.study.base.object_.clone;

class B {
    public String value;
    public A a; // B 持有 A → 循环引用
}