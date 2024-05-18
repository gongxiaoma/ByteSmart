package com.bytesmart.webadmin.controller;

import java.util.Scanner;

public class Singledigit {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入一个数字");
        int num = scanner.nextInt();
        int single = num % 10;
        int ten = (num / 10) % 10;
        System.out.println("数字的个位数是："+ single );
        System.out.println("数字的十位数是："+ ten );
    }
}
