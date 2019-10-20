package com.cys.algorithm.sort.Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Properties;

public class Util {

    public static void Swap(int[] arr, int i, int j) {
        if(arr[i]==arr[j]) return;
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }

    private static int rand() throws IOException {
        Properties properties = new Properties();
        properties.load(new InputStreamReader(new FileInputStream(new File("C:\\Users\\missb\\Desktop\\_Java_\\Algorithm\\src\\com\\cys\\algorithm\\sort\\Utils\\data.properties"))));
        String scale = properties.getProperty("scale");
        int scaleNumber = Integer.parseInt(scale);
        return scaleNumber;
    }

    public static int[] getArr() {
        int[] arr;
        arr = new int[8];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 100);
        }
        return arr;
    }


    public static void runBefore() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = simpleDateFormat.format(new Date());
        System.out.println("排序前的时间：" + format);
    }

    public static void runAfter() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = simpleDateFormat.format(new Date());
        System.out.println("排序后的时间：" + format);
    }

    public static void print(int[] arr) {
        System.out.println(Arrays.toString(arr));
    }
}