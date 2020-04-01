package com.cys.exercise;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author missb
 */
public class Hex extends Exception {

    /**
     * 如何求一个0~255范围内的整数的十六进制值，例如60的十六进制表示形式3C
     *
     * @param n 十进制的整数
     * @return 字符串表示的16进制数
     */
    static String toHex(int n) {
        StringBuilder res = new StringBuilder();

        int bit = 15;
        int shift = 4;
        for (int i; (i = (n & bit)) != 0; n >>>= shift) {
            res.append((i > 9) ? (char) (i - 10 + 'A') + "" : i + "");
        }
        return res.reverse().toString();
    }

    public static void main(String[] args) {
        new Thread(new Thread1()).start();
        while (true) {
            System.out.println("main function");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
        private static void getProperties () {
            // 1000101
            Properties prop = new Properties();
            try {
                InputStream in = Hex.class.getResourceAsStream("t1.properties");
                prop.load(in);
                String name = prop.getProperty("name");
                System.out.println(name);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    class Thread1 implements Runnable {

        @Override
        public void run() {
            while (true) {
                System.out.println("Thread1");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
