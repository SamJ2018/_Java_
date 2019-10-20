package com.cys.algorithm.sort;

import com.cys.algorithm.sort.Utils.Util;

/**
 * @author sam
 * @apiNote
 * @since 2019-10-20-16:59
 * 堆：任何一颗子树的根都小于(大于)其子结点
 **/
public class HeapSort {

    /**
     * 下沉操作
     *
     * @param k 需要下沉结点的下标
     * @param n 数据规模
     */
    public static void sink(int[] arr, int k, int n) {
        while (2 * k + 1 < n) {
            int j = 2 * k + 1;
            if (j < n - 1 && arr[j] < arr[j + 1]) ++j;
            if (arr[k] >= arr[j]) break;
            else Util.Swap(arr, k, j);
            k = j;
        }
    }

    private static void createHeap(int[] arr, int n) {
        for (int i = (n - 1) >> 1; i > 0; --i)
            sink(arr, i, n);
    }

    private static void heapSort(int[] arr, int n) {
        createHeap(arr, n);
        while (n > 1) {
            Util.Swap(arr, 0, --n);
            sink(arr, 0, n);
        }
    }

    public static void main(String[] args) {
        int[] arr = Util.getArr();
        heapSort(arr, arr.length);
        Util.print(arr);
    }
}
