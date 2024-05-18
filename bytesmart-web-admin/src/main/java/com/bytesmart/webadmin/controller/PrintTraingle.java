package com.bytesmart.webadmin.controller;


public class PrintTraingle {
    public static void main(String[] args) {
        // 定义等腰三角形的行数
        int rows = 6;
        // 外层循环控制行数
        for (int i = 0; i < rows; i++) {
            // 打印空格，用于形成等腰三角形的左边空白部分
            for (int j = rows - i - 1; j > 0; j--) {
                System.out.print(" ");
            }
            // 打印星号，用于形成等腰三角形的右边部分
            for (int k = 0; k <= 2 * i; k++) {
                System.out.print("*");
            }
            // 打印完一行后换行
            System.out.println();

//        for(int i =1; i<5;i++){
//            for(int j=0; j<(2*i-1);j++){
//                System.out.println("*");
//            }
//            System.out.println();

        }
    }

}



