package com.example.study.base.reflection;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Reflect2Generic {
    public void test01(Map<String, Man> manMap, List<User> list) {
        System.out.println("test01");

    }

    public List<User> test02() {
        return new ArrayList<>();
    }

    public static void main(String[] args) throws NoSuchMethodException {
        Method test01 = Reflect2Generic.class.getMethod("test01", Map.class, List.class);
        Type[] genericParameterTypes = test01.getGenericParameterTypes();

        for (Type genericParameterType : genericParameterTypes) {
            System.out.println("#" + genericParameterType);
            if (genericParameterType instanceof ParameterizedType) {
                Type[] actualTypeArguments = ((ParameterizedType) genericParameterType).getActualTypeArguments();

                for (Type actualTypeArgument : actualTypeArguments) {
                    System.out.println("真实参数化类型" + actualTypeArgument);
                }
            }
        }

        System.out.println("————————————————————");

        Method test02 = Reflect2Generic.class.getMethod("test02", null);
        Type genericReturnType = test02.getGenericReturnType();

        if (genericReturnType instanceof ParameterizedType) {
            Type[] actualTypeArguments = ((ParameterizedType) genericReturnType).getActualTypeArguments();

            for (Type actualTypeArgument : actualTypeArguments) {
                System.out.println("真实参数化类型" + actualTypeArgument);
            }
        }

    }
}
