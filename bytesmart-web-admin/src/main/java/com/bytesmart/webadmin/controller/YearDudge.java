package com.bytesmart.webadmin.controller;

import jdk.internal.util.xml.impl.Input;

import java.time.Year;
import java.util.Scanner;

public class YearDudge {
    public static void main(String[] args) {
        //打印闰年(i 能被 4 整除且不能被100整除，或者i 能被 400 整除)
        for(int i = 1999; i<= 2055; i++){
            if((i % 4 ==0 && i % 100 != 0) || i % 400 ==0){
                System.out.println(i);
            }
        }
        Scanner a = new Scanner(System.in);
        System.out.println("请输入年份");
        int year = a.nextInt();
        if(year % 4 == 0 && year % 100 != 0 || year % 400 ==0){
            System.out.println( year +"是闰年");}
            else{
            System.out.println(year + "不是闰年");

            }
        }
}
