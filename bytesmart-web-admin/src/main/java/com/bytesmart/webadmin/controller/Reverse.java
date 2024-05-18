package com.bytesmart.webadmin.controller;

public class Reverse {
    public static void main(String[] args) {
        //字符串反转
        String a = "你好";
        StringBuilder fz = new StringBuilder(a);
        String b = fz.reverse().toString();
        System.out.println(b);
    }
}
