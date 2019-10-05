package com.cys.algorithm.sort;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sam
 * @apiNote
 * @since 2019-10-06-12:04 AM
 **/
public class MergeSort {

    static void mSort(int[] arr, int l, int r) {
        if (l >= r) return;
        int mid = (l + r) >> 1;
        mSort(arr, l, mid);
        mSort(arr, mid + 1, r);

        List<Integer> list = new ArrayList<>();

        int i = l, j = mid + 1;
        while (i <= mid && j <= r)
            if (arr[i] <= arr[j]) list.add(arr[i++]);
            else list.add(arr[j++]);
        while (i <= mid) list.add(arr[i++]);
        while (j <= r) list.add(arr[j++]);
        for (i = l, j = 0; j < list.size(); j++, i++) arr[i] = list.get(j);
    }


    public static void main(String[] args) {
        int[] arr = new int[10];
        for (int i = 0; i < 10; i++) {
            arr[i] = (int) (Math.random() * 1000);
        }
        mSort(arr, 0, arr.length - 1);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}
