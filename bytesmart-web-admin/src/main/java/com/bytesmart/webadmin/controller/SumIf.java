package com.bytesmart.webadmin.controller;

public class SumIf {
    public static void main(String[] args) {
        int sum = 0;
        for(int i = 1; i<= 99; i++){
            if(i % 2 == 0){
                sum += i;
            }
        }
        System.out.println("1-99之间偶数和是："+ sum);

    }
}
