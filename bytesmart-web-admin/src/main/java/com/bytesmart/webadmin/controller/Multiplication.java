package com.bytesmart.webadmin.controller;

public class Multiplication {
    public static void main(String[] args) {
        /*
        * 九九乘法表
        *  "\t" 分隔
        * */
        for(int a = 1; a<= 9; a++){
            for(int b = 1; b<= a; b++){
                System.out.print(b + "*" + a + "="  + (a * b) + "\t");
            }
            System.out.println();
        }
    }
}
