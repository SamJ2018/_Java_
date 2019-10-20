package com.cys.algorithm.sort;

import com.cys.algorithm.sort.Utils.Util;

/**
 * @author sam
 * @apiNote
 * @since 2019-10-20-13:36
 **/
public class InsertSort {

    public static void iSort(int arr[]) {
        if (arr == null || arr.length < 2) return;
        for (int i = 1; i < arr.length; i++) {
            for (int j = i - 1; j >= 0 && arr[j] > arr[j + 1]; --j)
                Util.Swap(arr, j, j + 1);
        }
    }

    public static void iSort2(int arr[]) {
        if (arr == null || arr.length < 2) return;
        int i, j;
        for (i = 1; i < arr.length; i++) {
            int temp = arr[i];
            for (j = i; j > 0 && arr[j - 1] >= temp; j--)
                arr[j] = arr[j - 1];
            arr[j] = temp;
        }
    }

    public static void main(String[] args) {
        int[] arr = Util.getArr();
        Util.print(arr);
        iSort2(arr);
        Util.print(arr);
    }
}
