package com.cys.algorithm.sort;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author sam
 * @apiNote
 * @since 2019-10-05-11:23 PM
 **/
public class QuickSort{

    public static void qSort(int[] arr, int lo, int hi) {
        if (lo >= hi) return;
        swap(arr, lo, (int) (lo + Math.random() * (hi - lo) + 1));
        int pivot = arr[lo], i = lo - 1, j = hi + 1;
        while (i < j) {
            do i++; while (arr[i] < pivot);
            do j--; while (arr[j] > pivot);
            if (i < j) swap(arr, i, j);
            else {
                qSort(arr, lo, j);
                qSort(arr, j + 1, hi);
            }
        }
    }

    private static void swap(int[] arr, int i, int j) {
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
    }


    public static void main(String[] args) {
        int[] arr = new int[800000];
        for (int i = 0; i < 800000; i++) {
            arr[i] = (int) (Math.random() * 10000);
        }

        System.out.println("排序前");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date1 = new Date();
        String dateBefore = simpleDateFormat.format(date1);
        System.out.println("排序前的时间：" + dateBefore);

        qSort(arr, 0, arr.length - 1);

        Date date2 = new Date();
        String dateAfter = simpleDateFormat.format(date2);
        System.out.println("排序后的时间：" + dateAfter);

//        System.out.println(Arrays.toString(arr));
    }

}
