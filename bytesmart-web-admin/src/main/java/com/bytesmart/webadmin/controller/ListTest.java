package com.bytesmart.webadmin.controller;

import com.bytesmart.webadmin.domain.Student;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ListTest {
    public static void main(String[] args) {
        /*
        * 存储学生对象并排序
        * 步骤
        * 1.定义学生对象
        * 2.创建ArrayList集合对象和学生对象
        * 3.把学生添加到集合
        * 4.ArrayList集合排序
        * 5.遍历集合
        */
        ArrayList<Student> array = new ArrayList<Student>();
        Student s1 = new Student("zhangyi", 29);
        Student s2 = new Student("zhanger", 27);
        array.add(s1);
        array.add(s2);

        for(Student i: array){
            System.out.println(i.getName()+","+i.getAge());
        }
    }
}
