package com.cys.algorithm.basic;

/**
 * 工具包
 *
 * @author missb
 * @create 2020--03--24 12:56 AM
 */

public class Utils {

    public static void swap(int[] a, int lo, int hi) {
        int temp = a[lo];
        a[lo] = a[hi];
        a[hi] = temp;
    }

    public static void print(int[] a) {
        System.out.print("[");
        for (int i = 0; i < a.length; i++) {
            if (i == a.length - 1) {
                System.out.print(a[i] + "]");
            } else {
                System.out.print(a[i] + ",");
            }
        }
    }
}
