package com.bytesmart.webadmin.controller;

public class Tes1 {
    public static void main(String[] args) {
        /*
        * 布尔值判断
        * */

       boolean a = true;
        String b = "粥";
        System.out.println("今天吃饭了吗?"+ ( a? "吃了":"还没吃"));
        if(a){
            System.out.println("吃了"+b);
        }else {
            System.out.println("还没吃东西");
        }

        // 声明并初始化一个布尔变量
        boolean isTrue = true;
        boolean isFalse = false;

        // 打印布尔变量的值
        System.out.println("isTrue 的值是: " + isTrue);
        System.out.println("isFalse 的值是: " + isFalse);

        int i = 8;
        int j = 9;
        boolean jieguo = i > j ;
        System.out.println("i是否大于j," + jieguo);

    }
}
