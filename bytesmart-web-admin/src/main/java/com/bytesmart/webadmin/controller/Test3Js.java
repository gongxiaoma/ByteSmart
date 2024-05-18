package com.bytesmart.webadmin.controller;


public class Test3Js {
    public static void main(String[] args) {
        int a = 3 ;
        int b = 5;
        System.out.println(a*b);
        System.out.println(a+b);
        System.out.println(a-b);
        System.out.println(a/b);

        /*
         * 预测身高*/
        double mh = 160;
        double fh = 175;
        double sh = (mh + fh) * 1.08 /2 ;
        double dh = (fh*0.923 + mh) /2 ;
        System.out.println(sh + "cm");
        System.out.println(dh+ "cm");

        /*
        * 求出最大值*/
        int c1 = 8;
        int c2 = 26;
        int c3 = 6;
       //三元计算，如果c1大于c2,max取c1值，否则c2
        int max = ( c1>c2)? c1:c2;
        max = (max>c3)? max:c3;
        System.out.println("最大值："+max);

        //打印出数组中数字最大的值
        int[] nums = {6,52,29,9};
        int zuida = nums[0];
        //遍历找出最大值
        for(int i=1; i <nums.length; i++){
            if(nums[i]> zuida){
                zuida = nums[i];
                System.out.println("数组中最大值"+ zuida);
            }



        }

    }
}
