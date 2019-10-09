package com.cys.algorithm.sort;

import com.cys.algorithm.sort.Utils.Util;

/**
 * @author sam
 * @apiNote
 * @since 2019-10-06-7:19 AM
 **/
public class CountSort {

    public static void cSort(int[] arr) {
        int[] temp = new int[arr.length];
        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j < i; j++)
                if (arr[j] <= arr[i]) temp[i]++;
                else temp[j]++;
        }
        for (int i = 0; i < arr.length; i++) {
            while (temp[i] != i) {
                Util.Swap(arr, i, temp[i]);
                Util.Swap(temp, i, temp[i]);
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = Util.getArr();
        Util.print(arr);
        cSort(arr);
        Util.print(arr);
    }
}
