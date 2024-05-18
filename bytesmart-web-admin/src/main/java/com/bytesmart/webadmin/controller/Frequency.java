package com.bytesmart.webadmin.controller;

public class Frequency {
    public static void main(String[] args) {
        int count = 0;
        for(int j = 1; j <= 100; j++){
            if(j % 10 == 9){
                count++;
            }else if(j /10 == 9){
                count++;
            }
        }
        System.out.println(count);
    }
}
