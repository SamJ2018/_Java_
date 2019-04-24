package com.cys.algorithm.sort;

import java.util.Arrays;

public class Comparator {
    /**
     * 测试排序算法专用
     * 先定义一个绝对正确的方法
     * 拷贝到另一个数组，比较两个方法产生的数组的值是否一致
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
