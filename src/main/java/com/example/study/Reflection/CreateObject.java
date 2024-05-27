package com.example.study.Reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 动态创建对象，通过反射
 */
public class CreateObject {
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, NoSuchFieldException {
        // 获得Class对象
        Class c1 = Class.forName("com.example.study.Reflection.Man");

        // 构造一个对象，本质上是调用了类的无参构造器
        Man man1 = (Man) c1.newInstance();
        System.out.println(man1);

        // 通过有参构造创建对象
        Constructor declaredConstructor = c1.getDeclaredConstructor(String.class);
        Man man2 = (Man) declaredConstructor.newInstance("ceshi");
        System.out.println(man2);

        // 通过反射调用普通方法
        Man man3 = (Man) c1.newInstance();
        Method setName = c1.getDeclaredMethod("setName", String.class);
        // invoke：激活（对象，方法的值）
        setName.invoke(man3, "测试");
        System.out.println(man3.getName());

        System.out.println("-------------------");

        // 通过反射操作属性
        Man man4 = (Man) c1.newInstance();
        Field name = c1.getDeclaredField("name");
        // 因为不能直接操作私有属性，所以需要关闭安全检测，属性或者方法的setAccessible(true)
        name.setAccessible(true);
        name.set(man4, "xiaoceshi");
        System.out.println(man4.getName());

    }
}

class Man {
    private String name;

    public Man(String name) {
        this.name = name;
    }

    public Man() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
