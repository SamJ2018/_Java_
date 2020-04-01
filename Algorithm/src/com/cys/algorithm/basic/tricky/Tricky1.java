package com.cys.algorithm.basic.tricky;

import com.cys.algorithm.basic.Utils;
import org.junit.Test;

import java.util.*;

/**
 * 奇淫技巧
 *
 * @author missb
 */

public class Tricky1 {
    public static void main(String[] args) {
        //t1();

        //如何求出数组中唯一成对的那个树？
        // 有个1001的数组，存放1000个数，只有2个重复，找出来

    }

    private static void t1() {
        //求系统时间
        long curTime = System.currentTimeMillis();
        long seconds = curTime / 1000 % 60;
        long minutes = seconds / 60 % 60;
        long hours = seconds / 60 / 60 % 24;
        System.out.println("当前系统时间是:" + (hours) + ":" + minutes + ":" + seconds);

        //求随机数
        // [a , a + b)
        int a = 1, b = 100;
        System.out.println(a + (int) (Math.random() * (b - a + 1)));
    }

    /**
     * 如何求出数组中唯一成对的那个树？
     * 有个1001的数组，存放1000个数，只有2个重复，找出来
     */
    @Test
    public void t2() {
        //利用异或，先将重复的干掉
        //怎么得到重复值？ 跟整个序列异或，这样会消掉重复的，(1..k..k..100)^(1..k..100)
        int N = 11;
        int[] arrList = new int[N];
        for (int i = 0; i < arrList.length - 1; i++) {
            arrList[i] = i + 1;
        }

        //假设最后一个是随机数
        arrList[arrList.length - 1] = new Random().nextInt(N - 1) + 1;

        //随机下标
        int index = new Random().nextInt(N);
        Utils.swap(arrList, index, arrList.length - 1);
        Utils.print(arrList);

        //---------------sol----------------
        int x1 = 0;
        for (int i = 1; i < N; i++) {
            x1 = (x1 ^ i);
        }

        for (int i = 0; i < arrList.length - 1; i++) {
            x1 = (x1 ^ arrList[i]);
        }
        //---------------sol2----------------

    }
}

class Generic{
    public static <T> void add(T a, T b){
       T temp = a;
       a = b;
       b = temp;
    }
    public static void copy(Collection<? super Integer> src, Collection<Object> desc){
        desc.addAll(src);
    }

    public static void main(String[] args) {
        Collection<String> src = new ArrayList<>();
        src.add("hello");
        src.add("world");
        Collection<Integer> desc = new ArrayList<>();
        desc.add(1);
        desc.add(2);


    }
}

class Main{
    public static void main(String[] args) {
        int a = 4, b = 5;
        Generic.add(a,b);
        System.out.println(a);
        System.out.println(b);
    }
}
