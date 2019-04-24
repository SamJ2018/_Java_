package com.cys.algorithm.sort.bubblesort;


import com.cys.algorithm.sort.Utils.Comparator;
import com.cys.algorithm.sort.Utils.Util;

public class BubbleSort {

    public static void bubbleSort(int[] arr) {
        if (arr == null || arr.length < 2) return;

        boolean flag = true;
        for (int i = 0; flag && i < arr.length; i++) {
            flag = false;
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    flag = true;
                    Util.Swap(arr, j, j + 1);
                }
            }
        }
    }//for


    public static void main(String[] args) {

        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;


        boolean succeed = true;

        for (int i = 0; i < testTime; i++) {
            int[] arr1 = Comparator.generateRandomArray(maxSize, maxValue);
            int[] arr2 = Comparator.copyArray(arr1);
            //bubbleSort(arr1);
            BubbleSort_2.bubbleSort2(arr1, arr1.length);
            Comparator.Comparator(arr2);

            if (!Comparator.isEqual(arr1, arr2)) {
                succeed = false;
                break;
            }
        }//for
        System.out.println(succeed ? "Nice!" : "fucking fucked!");
        int[] arr = Comparator.generateRandomArray(maxSize, maxValue);

        Comparator.printArray(arr);
//        bubbleSort(arr);
        BubbleSort_2.bubbleSort2(arr,arr.length);
        Comparator.printArray(arr);
    }


}
