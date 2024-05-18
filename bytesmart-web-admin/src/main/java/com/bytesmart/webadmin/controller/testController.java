package com.bytesmart.webadmin.controller;

import org.yaml.snakeyaml.util.ArrayUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class testController {
    public static void main(String[] args) {
//        定义一个数组，用变量numbers来接,初始化为{1,2,，3,4,5}
        int[] arr = {11,12,13,14,15};
        int[] numbers = {1, 2, 3, 4, 5};

//用for each循环遍历数组numbers，首先把数组numbers中的第一个元素1遍历给int类型的变量n,值为1，再执行判断条件，如果是奇数，判断为真true.则执行打印n的值；接着继续遍历第二个元素2，判断为false,不执行打印，依次循环
//        for (int n : numbers) {
//            if (n % 2 != 0) { // 判断是否为奇数（即余数为0）
//                System.out.println(n);
//            }
//        }
        System.out.println("-------------");
//初始化变量j为1，执行判断条件是j值小于numbers数组的长度5，为true时，执行方法体的语句，打印j值（0），执行j++，j赋值为1,执行判断条件，判断为真，执行方法体的语句，打印j值（1）,依次循环。
        for (int j = 0;j< numbers.length; j++) {
            System.out.println(j);;
            }
        System.out.println("-------------");

//        for (int i = 1;i< numbers.length; i++) {
//
//            System.out.println(numbers[i]);
//        }

        int xinLength = arr.length + numbers.length;
        int[] xin = new int [xinLength];

        for (int a= 0; a<arr.length;a++){
            xin[a] = arr[a];
        }
        for (int a= 0; a<numbers.length;a++){
            xin[arr.length + a] = numbers[a];
        }
        for (int num : xin){
            System.out.println(num);
        }

        System.out.println("----------");


        int[] xinArray = IntStream.concat(IntStream.of(numbers), IntStream.of(arr)).toArray();
        for(int x:xinArray){
            System.out.println(x);
        }

//        List<Integer> list = new ArrayList<Integer>();
//        list.add(10); list.add(20); list.add(30);
//        System.out.println(list);


    }

}
