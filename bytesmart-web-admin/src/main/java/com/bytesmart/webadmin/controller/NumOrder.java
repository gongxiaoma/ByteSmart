package com.bytesmart.webadmin.controller;

import java.util.Arrays;

import static org.apache.commons.lang3.ArrayUtils.reverse;

public class NumOrder {
    public static void main(String[] args) {
        int [] nums = {8,9,57,36,15};
        //方法1：Arrays.sort() 方法对于数值类型就是升序进行排序。
        Arrays.sort(nums);
        // 使用 Arrays.toString() 将数组转换为字符串并打印
        System.out.println((Arrays.toString(nums)));
        //反转降序
        reverse(nums);
        System.out.println((Arrays.toString(nums)));

        /*
         * 方法二：冒泡排序
         * 步骤：1.排列顺序 2.遍历排序后的数组，打印出来
         * */
        int[] array = {5, 2, 8, 1, 9};
        for(int i = 0; i< array.length - 1; i++){
            for(int j =0; j<array.length - i -1; j++){
                if(array[j] > array[j+1]){
                    int temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                }
            }
        }
        //打印出数组格式
        System.out.println((Arrays.toString(array)));
       //遍历出重新排列的数字
        for(int d : array){
            System.out.println(d);
        }

//        for(int d = 0 ; d< array.length; d++){
//            System.out.println(array[d]);
//        }

        int[] xin = {12,6,25,36};
        for(int a = 0; a<xin.length-1; a++){
            for(int b = 0; b<xin.length-b-1;b++){
                if(array[b] < xin[b+1]){
                    int max = xin[b];
                    xin[b] = xin[b+1];
                    xin[b+1] = max;
                }
            }
        }
        System.out.println(Arrays.toString(xin));

    }
}
