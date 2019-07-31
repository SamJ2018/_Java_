package com.cys.exercise;

/**
 * @Author: sam
 * @Description:
 * @create 2019-07-31-7:38 AM
 **/
public class StringExer {
    public static void main(String[] args) {
      /*  String[] strs = {"sjd", "abcas", "zdhs", "bcjsh", "dhjye"};
        sortArrayStr(strs);*/

        String parent = "sabcjhsjabcxnjcabcefsdabcesd";
        String sub = "asdcxnjcsdd";
        String needTrim = "         ad vd                ";

      /*  int count = countSub(parent, sub);
        System.out.println(count);*/


        /*String newStr = myTrim(needTrim);
        System.out.println(newStr);*/

        String str = longSamStr(parent, sub);
        System.out.println(str);
    }

    /**
     * 返回最大相同子串
     *
     * @param str1
     * @param str2
     * @return
     */
    private static String longSamStr(String str1, String str2) {
        String parent = str1.length() > str2.length() ? str1 : str2;
        String sub = parent.equals(str1) ? str2 : str1;
        String retStr = null;

        for (int i = 0; i < sub.length(); i++) {
            for (int x = 0, y = sub.length() - i; y != sub.length() + 1; ++y, ++x) {
                if (parent.contains(retStr = sub.substring(x, y)))
                    return retStr;
            }
        }
        return retStr;
    }

    /**
     * 模拟trim
     *
     * @param needTrim
     * @return
     */
    private static String myTrim(String needTrim) {
        int start = 0, end = needTrim.length() - 1;
        while (start < end && needTrim.charAt(start) == ' ') ++start;
        while (start < end && needTrim.charAt(end) == ' ') --end;

        return needTrim.substring(start, end + 1);
    }


    /**
     * 记录子串在主串中的位置次数
     *
     * @param parent
     * @param sub
     * @return
     */
    private static int countSub(String parent, String sub) {
        int count = 0, index;
        while ((index = parent.indexOf(sub)) != -1) {
            if (parent.contains(sub)) {
                ++count;
                parent = parent.substring(index + sub.length());
            }
        }
        return count;
    }

    /**
     * 按照字典顺序排序字符串数组
     *
     * @param strs 字符串数组
     */
    private static void sortArrayStr(String[] strs) {
        for (int i = 0; i < strs.length - 1; i++) {
            for (int j = i + 1; j < strs.length; j++) {
                if (strs[i].compareTo(strs[j]) > 0) {
                    String temp = strs[i];
                    strs[i] = strs[j];
                    strs[j] = temp;
                }
            }
        }
        System.out.print("[");
        for (int i = 0; i < strs.length; i++) {
            if (i == strs.length - 1)
                System.out.print(strs[i] + "]");
            else
                System.out.print(strs[i] + ",");
        }
    }


}
