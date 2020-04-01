package com.cys.algorithm.basic.位运算;

/**
 * Basic bit operate
 *
 * @author missb
 * @create 2020--03--23 11:28 PM
 */

public class Basic {
    public static void main(String[] args) {
        b1();

    }

    private static void b1() {
        int i = 30;
        //true 数据范围
        System.out.println(i << 35 == i << 3);

        //判断奇偶数
        System.out.println((i & 1) == 0 ? "even" : "odd");

        //怎么不work?
        System.out.println((((i >> 4) & 1) == 0) ? "1" : "0");

        //交换两个数
        int x = -30, y = -40;
        x = x ^ y;
        y = x ^ y;
        x = x ^ y;
        System.out.println(x);
        System.out.println(y);

        //不用判断语句求A的绝对值
        //A>>>31   如果是负数，无符号右移则高位为0  -> 1
        //         如果是正数，-> 0
        // A ^ -1   -> 取反操作
        //
        int z = -40;
        System.out.println((z ^ (z >> 31)) + (z >>> 31));
    }
}
