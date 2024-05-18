package com.bytesmart.webadmin.controller;

import java.util.Scanner;

public class Results {
        public static void main(String[] args) {
            Scanner input = new Scanner(System.in);
            double max = 0.0;//最高分
            double min = 100.0;//最低分
            double mean = 0.0;//平均值
            double sum = 0.0;//求和
            double[] score = {86.2, 53.9, 63, 98.2, 92.5, 89.6, 75, 69};
            //求和
            for (int i = 1; i <= score.length - 1 ; i++) {
                sum += score[i];//求数组元素值的和
            }
            for (int i = 1; i <= score.length - 1 ; i++) {
                if (score[i] > max) {//此时i下标大于此时的max，i下标的值就是此时的最高分
                    max = score[i];
                }
                if (score[i] < min) {//此时i下标小于此时的max，i下标的值就是此时的最低分
                    min = score[i];
                }
            }
            mean = sum / score.length;//计算平均数
            System.out.println(mean);
            System.out.println(min);
            System.out.println(max);

        }
    }

