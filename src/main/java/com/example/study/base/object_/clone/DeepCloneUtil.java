package com.example.study.base.object_.clone;

import java.lang.reflect.Field;
import java.util.IdentityHashMap;
import java.util.Map;

public class DeepCloneUtil {

    // 缓存： key=原对象，value=克隆后的对象
    // 用 IdentityHashMap（按对象内存地址判断，不是 equals）
    private final Map<Object, Object> cache = new IdentityHashMap<>();

    // 入口
    public Object deepClone(Object obj) {
        if (obj == null) return null;

        // 1. 已经克隆过 → 直接返回，不再递归
        if (cache.containsKey(obj)) {
            return cache.get(obj);
        }

        try {
            // 2. 先创建空克隆对象，立刻放进缓存（关键！防递归）
            Class<?> clazz = obj.getClass();
            Object clone = createInstance(clazz); // 提取为独立方法
            cache.put(obj, clone); // 先存！

            // 3. 递归克隆所有字段
            for (Field field : clazz.getDeclaredFields()) {
                // 跳过静态字段和瞬态字段
                if (isIgnoredField(field)) continue;

                field.setAccessible(true);
                Object fieldValue = field.get(obj);

                // 对不可变对象直接赋值，避免不必要的递归
                Object clonedFieldValue = isImmutable(fieldValue) ? fieldValue : deepClone(fieldValue);
                field.set(clone, clonedFieldValue);
            }

            return clone;

        } catch (Exception e) {
            throw new RuntimeException("克隆失败", e);
        }
    }

    // 创建实例的方法，处理无参构造函数缺失的情况
    private Object createInstance(Class<?> clazz) throws Exception {
        try {
            return clazz.getDeclaredConstructor().newInstance();
        } catch (NoSuchMethodException e) {
            throw new IllegalArgumentException("类 " + clazz.getName() + " 没有无参构造函数，无法克隆");
        }
    }

    // 判断是否为需要忽略的字段（静态或瞬态）
    private boolean isIgnoredField(Field field) {
        return java.lang.reflect.Modifier.isStatic(field.getModifiers()) ||
               java.lang.reflect.Modifier.isTransient(field.getModifiers());
    }

    // 判断是否为不可变对象（如 String、Integer 等）
    private boolean isImmutable(Object obj) {
        if (obj == null) return true;
        Class<?> clazz = obj.getClass();
        return clazz == String.class || clazz == Integer.class || clazz == Long.class ||
               clazz == Double.class || clazz == Float.class || clazz == Boolean.class ||
               clazz == Character.class || clazz == Byte.class || clazz == Short.class;
    }

    // 测试
    public static void main(String[] args) {
        // 构造循环引用
        A a = new A();
        B b = new B();
        a.name = "我是A";
        b.value = "我是B";
        a.b = b;
        b.a = a; // 循环：A→B→A

        // 深克隆
        DeepCloneUtil util = new DeepCloneUtil();
        A aClone = (A) util.deepClone(a);

        // 验证：不是同一个对象
        System.out.println(a == aClone); // false
        System.out.println(a.b == aClone.b); // false
        System.out.println(a.b.a == a); // true
        System.out.println(aClone.b.a == aClone); // true（克隆后依然正确循环）
    }
}
