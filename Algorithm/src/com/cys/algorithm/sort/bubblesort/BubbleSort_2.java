package com.cys.algorithm.sort.bubblesort;

import com.cys.algorithm.sort.Utils.Util;

public class BubbleSort_2 {

    public static void bubbleSort2(int[] A, int n) {
        //统计比较次数和交换次数
        int cmp = 0, swp = 0, m = 0;

        for (; 1 < n; n = m) {
            for (int i = m = 1; i < n; i++) {
                if (A[i - 1] > A[i]) {
                    Util.Swap(A, i-1, i);
                    m = i;  swp++;
                }
                cmp++;
            }
        }
    }

}
