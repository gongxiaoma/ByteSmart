package com.bytesmart.webadmin.controller;

public class Star {
    public static void main(String[] args) {
        int rows = 7; // 长方形的行数
        int cols = 6; // 长方形的列数

        // 外层循环控制行数
        for (int i = 0; i < rows; i++) {
            // 内层循环控制列数
            for (int j = 0; j < cols; j++) {
                System.out.print("*"); // 打印星号
            }
            System.out.println(); // 每打印完一行后换行
        }
    }
}
