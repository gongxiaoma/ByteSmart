package com.bytesmart.webadmin.controller;

public class MaxMin {
    public static void main(String[] args) {
        int [] arr = {5,8,15,6};
        /*
         * 求数组中最大值*/
        int max = arr[0];
        for(int i =1 ; i<arr.length;i++){
            if(arr[i] > max){
                max = arr[i];
            }
        }
        System.out.println("最大值：" + max);

        /*
        * 求数组中最小值*/
//        int min = arr[0];
//        for(int i = 1; i<arr.length;i++){
//            if(arr[i]< min){
//                min =arr[i];
//            }
//        }
//        System.out.println("最小值："+ min);
    }
}
