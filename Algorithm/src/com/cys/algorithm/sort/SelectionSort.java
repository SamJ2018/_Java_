package com.cys.algorithm.sort;

import com.cys.algorithm.sort.Utils.Util;

/**
 * @author sam
 * @apiNote
 * @since 2019-10-20-12:12
 **/
public class SelectionSort {

    public static void sSort(int[] arr) {
        if (arr.length < 2 || arr == null) return;
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++)
                minIndex = arr[j] < arr[minIndex] ? j : minIndex;
            Util.Swap(arr, i, minIndex);
        }
    }

    public static void main(String[] args) {
        int[] arr = Util.getArr();
        Util.runBefore();
        sSort(arr);
        Util.runAfter();
        Util.print(arr);
    }
}
