package com.bytesmart.webadmin.controller;

import java.util.Scanner;

public class Score {
    public static void main(String[] args) {
        Scanner a = new Scanner(System .in);
        System.out.println("请输入一个成绩");
        int score = a.nextInt();
        if (score >= 90) {
            System.out.println("A");
        }else if(score >=65 && score<90){
            System.out.println("B");
        }else if(score<65 && score>0){
            System.out.println("C");
        }else{
            System.out.println("输入错误/没来考试");
        }
    }
}
