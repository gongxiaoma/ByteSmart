package com.bytesmart.webadmin.controller;

import com.bytesmart.webadmin.domain.Student;

import java.util.HashSet;
import java.util.Scanner;

public class Hasset {
    public static void main(String[] args) {
        HashSet<Student> has = new HashSet<Student>();
        Student s1 = new Student("zhangyi", 29);
        Student s2 = new Student("zhanger", 27);
        has.add(s1);
        has.add(s2);
        for(Student a : has){
            System.out.println(a.getName()+ a.getAge());
        }
    }
}
