package com.bytesmart.webadmin.controller;

import java.util.ArrayList;

public class Test2 {
    public static void main(String[] args) {
        int[] num1 = {10,11,12,13};
        int[] num2 ={2,3,4,5};

//        int length1 = num1.length;
//        int length2 = num2.length;

        int arraylength =  num1.length + num2.length ;
        int array[] = new int[arraylength];

        for(int a=0 ; a<num1.length; a++){
            array[a] = num1[a];
        }

        for(int a=0 ; a<num2.length; a++){
            array[a+ num1.length] = num1[a];
        }

        for(int xin : array ){
            System.out.println(xin);
        }

        System.out.println("----------");;

        /*
        * 方法二：用arraylist
        * */

        ArrayList<Integer> xin2 = new ArrayList<>();
        for(int b: num1){
            xin2.add(b);
        }

        for(int b: num2){
            xin2.add(b);
        }
        System.out.println(xin2);

        System.out.println("------------");

        for( int d: num1){
            if(d % 2 != 0 ){
                System.out.println(d);
            }
        }

        System.out.println("---------");

        for( int d: num1){
            if(d % 2 == 0 ){
                System.out.println(d);
            }
        }

    }
}
