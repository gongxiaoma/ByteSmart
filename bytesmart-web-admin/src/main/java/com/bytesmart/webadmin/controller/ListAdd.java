package com.bytesmart.webadmin.controller;

import com.bytesmart.webadmin.domain.Student;

import java.util.ArrayList;
import java.util.List;

public class ListAdd {
    public static void main(String[] args) {

        //创建集合对象
        List<String> list = new ArrayList<String>();
        //添加元素
        list.add("hello1");
        list.add("hello2");
        list.add("hello3");
        System.out.println(list);
        //获取集合中的第一个元素
        System.out.println(list.get(0));
        System.out.println("----------");
        //修改指定索引处的元素，返回被修改的元素
        System.out.println(list.set(1,"word"));

        //用for循环遍历元素
        for(String i : list){
            System.out.println(i);
        }

        List<Student> arr = new ArrayList<Student>();
        Student s1 = new Student("林一",29);
        Student s2 = new Student("林二",25);
        Student s3 = new Student("林三",31);

        arr.add(s1);
        arr.add(s2);
        arr.add(s3);

        for(Student d: arr){
            System.out.println(d);
        }

    }
}
