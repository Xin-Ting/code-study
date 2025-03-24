package com.example.study.base.reflection;

public class CreateClass {
    public static void main(String[] args) throws ClassNotFoundException {
        Person person = new Student();

        //方式1：通过对象获得
        Class c1 = person.getClass();
        System.out.println(c1.hashCode());
        //方式2：forName
        Class c2 = Class.forName("com.example.study.base.reflection.Student");
        System.out.println(c2.hashCode());
        // 方式3：类名.class
        Class<Student> c3 = Student.class;
        System.out.println(c3.hashCode());
        // 基本内置类型的包装类都有一个Type类型
        Class<Integer> c4 = Integer.TYPE;
    }
}

class Person {

}

class Student extends Person {

}
