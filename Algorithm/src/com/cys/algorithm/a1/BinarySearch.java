package com.cys.algorithm.a1;

/**
 * @author sam
 * @apiNote
 * @since 2019-10-09-11:15 AM
 **/

public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {1, 3, 8, 10, 67, 100};
        System.out.println(binarySearch(arr, 10));
    }

    /**
     * 二分查找非递归实现
     */
    public static int binarySearch(int[] arr, int target) {
        int left = 0, right = arr.length - 1;
        while (left <= right) {
            //继续查找
            int mid = (left + right) >> 1;
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }
}
