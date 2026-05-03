package com.example.study.base.extends_;

public class CollegeStudent extends Student {
    private String college;

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public static void main(String[] args) {
        CollegeStudent collegeStudent = new CollegeStudent();
        collegeStudent.name = "张三";
        collegeStudent.age = 18;
        collegeStudent.sex = "男";
        String description = collegeStudent.getDescription();
        System.out.println(description);
    }
}