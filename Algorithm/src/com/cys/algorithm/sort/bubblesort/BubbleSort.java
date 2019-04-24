package com.cys.algorithm.sort.bubblesort;


import com.cys.algorithm.sort.Comparator;

public class BubbleSort {

    public static void bubbleSort(int[] arr) {
        if (arr == null || arr.length < 2) return;

        boolean flag = true;
        for (int i = 0; flag && i < arr.length; i++) {
            flag = false;
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    flag = true;
                    Swap(arr, j, j + 1);
                }
            }
        }
    }//for


    public static void Swap(int[] arr, int i, int j) {
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }

    public static void main(String[] args) {

        int testTime = 500000;
        int maxSize=100;
        int maxValue=100;

        boolean succeed=true;

        for (int i = 0; i < testTime; i++) {
            int[] arr1= Comparator.generateRandomArray(maxSize,maxValue);
            int[] arr2=Comparator.copyArray(arr1);
            bubbleSort(arr1);
            Comparator.Comparator(arr2);

            if(!Comparator.isEqual(arr1,arr2)){
                succeed=false;
                break;
            }
        }//for
        System.out.println(succeed ? "Nice!" : "fucking fucked!");
        int[]arr=Comparator.generateRandomArray(maxSize,maxValue);

        Comparator.printArray(arr);
        bubbleSort(arr);
        Comparator.printArray(arr);
    }


}
