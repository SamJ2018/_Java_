package com.cys.leetcode;

/**
 * @Author: sam
 * @create 2019-08-01-11:30 PM
 * @Description: 给定一个表示分数的非负整数数组。 玩家1从数组任意一端拿取一个分数，随后玩家2继续从剩余数组任意一端拿取分数，然后玩家1拿，……。
 * 每次一个玩家只能拿取一个分数，分数被拿取之后不再可取。直到没有剩余分数可取时游戏结束。最终获得分数总和最多的玩家获胜。
 * 给定一个表示分数的数组，预测玩家1是否会成为赢家。你可以假设每个玩家的玩法都会使他的分数最大化。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [1, 5, 2]
 * 输出: False
 * 解释: 一开始，玩家1可以从1和2中进行选择。
 * 如果他选择2（或者1），那么玩家2可以从1（或者2）和5中进行选择。
 * 如果玩家2选择了5，那么玩家1则只剩下1（或者2）可选。
 * 所以，玩家1的最终分数为 1 + 2 = 3，而玩家2为 5。
 * 因此，玩家1永远不会成为赢家，返回 False。
 * 示例 2:
 * <p>
 * 输入: [1, 5, 233, 7]
 * 输出: True
 * 解释: 玩家1一开始选择1。然后玩家2必须从5和7中进行选择。无论玩家2选择了哪个，玩家1都可以选择233。
 * 最终，玩家1（234分）比玩家2（12分）获得更多的分数，所以返回 True，表示玩家1可以成为赢家。
 * 注意:
 * <p>
 * 1 <= 给定的数组长度 <= 20.
 * 数组里所有分数都为非负数且不会大于10000000。
 * 如果最终两个玩家的分数相等，那么玩家1仍为赢家。
 **/
public class PredictTheWinner_486 {
    /**
     * 方法一：使用递归方式
     *
     * @param arr
     * @return
     */
    public static int win1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        /*int sum = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            sum += arr[i];
        }
        int fValue = f(arr, 0, arr.length - 1);
        return Math.max(fValue, sum - fValue);*/
        return Math.max(f(arr, 0, arr.length - 1), s(arr, 0, arr.length - 1));
    }

    private static int f(int[] arr, int i, int j) {//先发者
        if (i == j) {
            return arr[i];
        }
        return Math.max(arr[i] + s(arr, i + 1, j), arr[j] + s(arr, i, j - 1));//分别拿走左右两个数的决策
    }//f(1,5)计算了两次

    private static int s(int[] arr, int i, int j) {
        if (i == j) {
            return 0;
        }
        return Math.min(f(arr, i + 1, j), f(arr, i, j - 1)); //先发者拿完后，后发者只能拿到较差的那个
    }


    private static int win3(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }
        int scores = p(arr, 0, arr.length - 1);
        return Math.max(sum - scores, scores);
    }

    private static int p(int[] arr, int i, int j) {
        if (i == j) {
            return arr[i];
        }
        if (i + 1 == j) {
            return Math.max(arr[i], arr[j]);
        }
        return Math.max(arr[i] + Math.min(p(arr, i + 2, j), p(arr, i + 1, j - 1)), arr[j] + Math.min(p(arr, i + 1, j - 1), p(arr, i, j - 2)));
    }

    private static int[] generateRandomArray() {
        int[] res = new int[(int) (Math.random() * 20) + 1];
        for (int i = 0; i < res.length; i++) {
            res[i] = (int) (Math.random() * 20) + 1;
        }
        return res;
    }

    public static void main(String[] args) {
        int testTime = 5000;
        boolean err = false;
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray();
            int r1 = win1(arr);
            int r3 = win3(arr);
            if (r1 != r3) {
                err = true;
            }
        }
        if (err) {
            System.out.println("Wrong");
        } else {
            System.out.println("Good");
        }
    }
}
