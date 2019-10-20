package com.cys.algorithm.sort.Utils;

import java.util.Arrays;

public class Comparator {
    /**
     * 测试排序算法专用
     * 0，有一个你想要测的方法a，
     * 1，实现一个绝对正确但是复杂度不好的方法b，
     * 2，实现一个随机样本产生器
     * 3，实现比对的方法
     * 4，把方法a和方法b比对很多次来验证方法a是否正确。
     * 5，如果有一个样本使得比对出错，打印样本分析是哪个方法出 错
     * 6，当样本数量很多时比对测试依然正确，可以确定方法a已经 正确。
     */
    public static void Comparator(int[] arr) {
        Arrays.sort(arr);
    }

    public static int[] generateRandomArray(int maxSize, int maxValue) {
        //定义一个随机长度的数组
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            //随机赋值
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }

    //复制数组
    public static int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }

        int[] res = new int[arr.length];

        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }


    public static boolean isEqual(int[] arr1, int[] arr2) {
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
            return false;
        }

        if (arr1 == null && arr2 == null) {
            return true;
        }

        if (arr1.length != arr2.length) {
            return false;
        }

        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }//for

        return true;
    }

   public static void printArray(int[] arr){
        if(arr==null){
            return;
        }
       for (int i = 0; i < arr.length; i++) {
           System.out.println(arr[i]+" ");
       }
       System.out.println();
   }
}
