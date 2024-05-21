package com.example.study.Reflection;

import java.lang.reflect.Constructor;

public class TestReflection01 {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException {
        // 通过反射获取类的Class对象
        Class user = Class.forName("com.example.study.Reflection.User");
        System.out.println(user);

        // 一个类在内存中只有一个Class对象
        // 一个类被加载后，类的整个结构都会被封装在Class对象中
        Class user1 = Class.forName("com.example.study.Reflection.User");
        Class user2 = Class.forName("com.example.study.Reflection.User");
        Class user3 = Class.forName("com.example.study.Reflection.User");
        Class user4 = Class.forName("com.example.study.Reflection.User");

        System.out.println(user1.hashCode());
        System.out.println(user2.hashCode());
        System.out.println(user3.hashCode());
        System.out.println(user4.hashCode());


    }
}

class User {
    int id;

    String name;

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public User() {
    }

    void register() {

    }
}
