package com.example.study.base.list;

import java.util.ArrayList;

public class ArrayListTest {

    private static final Object[] EMPTY = {};

    private transient Object[] elementData;

    public ArrayListTest() {
        elementData = EMPTY;
    }

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        ArrayListTest arrayListTest = new ArrayListTest();
        boolean b = EMPTY == arrayListTest.elementData;
        System.out.println(b);

        ArrayList<String> list = new ArrayList<>();
        list.add("1");
    }
}
