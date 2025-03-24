package com.example.study.base.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public class TestAnno {
    @MyAnnotation(name = "", age = 11, schools = "")
    public void test() {

    }
}

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@interface MyAnnotation {
    // 注解的参数：参数类型+参数名()
    String name() default "";

    int age();

    String[] schools() default "";

}
