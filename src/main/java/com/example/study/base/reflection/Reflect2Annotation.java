package com.example.study.base.reflection;

import java.lang.annotation.*;
import java.lang.reflect.Field;

public class Reflect2Annotation {
    public static void main(String[] args) throws NoSuchFieldException {
        Class<Table> tableClass = Table.class;
        Annotation[] annotations = tableClass.getAnnotations();
        for (Annotation annotation : annotations) {
            System.out.println(annotation);
        }

        TableName annotation = tableClass.getAnnotation(TableName.class);
        System.out.println(annotation.value());

        Field name = tableClass.getDeclaredField("name");
        name.setAccessible(true);
        TableField tableField = name.getAnnotation(TableField.class);
        System.out.println(tableField);
    }


}

@TableName("test")
class Table {

    @TableField(value = "id",type = "int",id = "id")
    private int id;

    @TableField(value = "name",type = "String",id = "name")
    private String name;

}

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@interface TableName {
    String value();
}

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@interface TableField {
    String value();

    String type();

    String id();
}
