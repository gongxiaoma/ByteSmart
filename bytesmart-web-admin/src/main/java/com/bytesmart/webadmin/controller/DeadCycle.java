package com.bytesmart.webadmin.controller;

public class DeadCycle {
    public static void main(String[] args) {
        //一般用这个方法写死循环
        while (true){
            System.out.println("这是个死循环");
        }

        //用for也可以写死循环，但是第二个判断条件为空
//        for(int i = 0; ; i++){
//            System.out.println(i);
//        }
    }
}
