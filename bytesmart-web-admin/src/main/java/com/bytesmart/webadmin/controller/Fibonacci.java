package com.bytesmart.webadmin.controller;

public class Fibonacci {
    public static  int Fibonacci(int n) {
        if (n <= 1)
            return n;
        int pre = 0;
        int curr = 1;
        for(int i = 1; i< n; i++){
            int sum = pre + curr;
            pre = curr;
            curr = sum;
        }
        return curr;
    }
    public static void main(String[] args) {
        System.out.println(Fibonacci(3));
    }

}

