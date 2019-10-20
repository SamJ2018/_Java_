package com.cys.algorithm.a1;

import com.cys.algorithm.sort.Utils.Util;

import java.util.Arrays;
import java.util.Stack;

/**
 * @author sam
 * @apiNote
 * @since 2019-10-20-14:23
 **/
public class course1 {
    public static int getMax(int arr[], int L, int R) {
        if (L == R) return arr[L];
        int mid = (L + R) >> 1;
        int maxLeft = getMax(arr, L, mid);
        int maxRight = getMax(arr, mid + 1, R);
        return Math.max(maxLeft, maxRight);
    }

    public static void main(String[] args) {
        int arr[] = {12, 11, 10, 9, 8, 7, 6, 5, 4};
        System.out.println(getMax(arr, 0, arr.length - 1));
    }
}

/**
 * 递归分析时间复杂度
 * master公式的使用
 * T(N) = a*T(N/b) + O(N^d)   如分治 2*T(N/2)+O(1)
 * b是子问题的样本量，a是划分的规模，
 * 1) log(b,a) > d -> 复杂度为O(N^log(b,a))
 * 2) log(b,a) = d -> 复杂度为O(N^d * logN)
 * 3) log(b,a) < d -> 复杂度为O(N^d)
 */

/*
  Question1
  小和问题和逆序对问题 小和问题
  在一个数组中，每一个数左边比当前数小的数累加起来，叫做这个数组的小和。
  求一个数组的小和。 例子： [1,3,4,2,5] 1左边比1小的数，没有；
  3左边比3小的数，1；
  4左边比4小的数，1、3；
  2左边比2小的数，1；
  5左边比5小的数，1、3、4、2； 所以小和为1+1+3+1+1+3+4+2=16

  逆序对问题 在一个数组中，左边的数如果比右边的数大，则折两个数构成一个逆序对，请打印所有逆序对。
 */
class Question1 {
    public static int smallSum(int[] arr) {
        if (arr == null || arr.length < 2) return 0;
        return mergeSort(arr, 0, arr.length - 1);
    }

    private static int mergeSort(int[] arr, int l, int r) {
        if (l == r) return 0;
        int mid = l + ((r - l) >> 1);
        return mergeSort(arr, l, mid) + mergeSort(arr, mid + 1, r) + merge(arr, l, mid, r);
    }

    private static int merge(int[] arr, int l, int mid, int r) {
        int[] help = new int[r - l + 1];
        int i = 0, res = 0, p1 = l, p2 = mid + 1;
        while (p1 <= mid && p2 <= r) {
            res += arr[p1] < arr[p2] ? (r - p2 + 1) * arr[p1] : 0;
            help[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= mid) help[i++] = arr[p1++];
        while (p2 <= r) help[i++] = arr[p2++];
        for (i = 0; i < help.length; i++) {
            arr[l + i] = help[i];
        }
        return res;
    }

    public static void main(String[] args) {
        int[] arr = {1, 3, 4, 2, 5};
        System.out.println(smallSum(arr));
    }
}

/*
给定一个数组arr，和一个数num，请把小于等于num的数放在数 组的左边，大于num的数放在数组的右边。
要求额外空间复杂度O(1)，时间复杂度O(N)

变形：
（荷兰国旗问题） 给定一个数组arr，和一个数num，请把小于num的数放在数组的左边，等于num的数放在数组的中间，大于num的数放在数组的右边。 要求额外空间复杂度O(1)，时间复杂度O(N)
 */
class Question2 {
    public static int[] NethelandsFlag(int[] arr, int l, int r, int num) {
        int less = l - 1; //最左边界
        int more = r + 1; //最右边界
        while (l < more) {
            if (arr[l] < num)
                Util.Swap(arr, ++less, l++);
            else if (arr[l] > num)
                Util.Swap(arr, --more, l);
            else ++l;
        }
        return new int[]{less + 1, more - 1};//把等于的边界返回回去
    }

    /**
     * 用荷兰国旗问题改进快排
     * 绕开原始序列   随机选取 or hash
     *
     * @param arr 待排序列
     * @param L   左边界
     * @param R   右边界
     */
    private static void quickSort(int[] arr, int L, int R) {
       /* if (L < R) {
            Util.Swap(arr, L + (int) (Math.random() * (R - L + 1)), R);
            int[] p = partition(arr, L, R);
            quickSort(arr, L, p[0] - 1);
            quickSort(arr, p[1] + 1, R);
        }*/
        if (L >= R) return;
        Stack<Integer> stack = new Stack<>();
        stack.push(L);
        stack.push(R);
        while (!stack.empty()) {
            R = stack.pop();
            L = stack.pop();
            int[] p = partition(arr, L, R);
            if (L < p[0] - 1) {
                stack.push(L);
                stack.push(p[0] - 1);
            }
            if (p[1] + 1 < R) {
                stack.push(p[1] + 1);
                stack.push(R);
            }
        }
    }//额外空间复杂度O(logn)  只有知道左部分划分点才知道右边的划分点

    private static int[] partition(int[] arr, int L, int R) {
        int less = L - 1;
        int more = R;
        while (L < more) {
            if (arr[L] < arr[R])
                Util.Swap(arr, ++less, L++);
            else if (arr[L] > arr[R])
                Util.Swap(arr, --more, L);
            else
                L++;
        }
        Util.Swap(arr, more, R);  //使用R来划分
        return new int[]{less + 1, more};
    }

    public static void main(String[] args) {
        int[] arr = Util.getArr();
        //System.out.println(Arrays.toString(NethelandsFlag(arr, 0, arr.length - 1, 6)));
        quickSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }
}