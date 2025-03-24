package com.example.study.base.reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class CompareSpeed {
    public static void test001() {
        Man man = new Man();
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 10_0000_0000; i++) {
            man.getName();
        }
        long endTime = System.currentTimeMillis();
        System.out.println("普通方式执行10亿次"+(endTime-startTime)+"ms");
    }

    public static void test002() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Man man = new Man();
        Class c = man.getClass();
        Method getName = c.getDeclaredMethod("getName", null);
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 10_0000_0000; i++) {
            getName.invoke(man, null);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("反射方式执行10亿次"+(endTime-startTime)+"ms");
    }

    public static void test003() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Man man = new Man();
        Class c = man.getClass();
        Method getName = c.getDeclaredMethod("getName", null);
        getName.setAccessible(true);
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 10_0000_0000; i++) {
            getName.invoke(man, null);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("关闭安全检测执行10亿次"+(endTime-startTime)+"ms");
    }

    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        test001();
        test002();
        test003();
    }
}
