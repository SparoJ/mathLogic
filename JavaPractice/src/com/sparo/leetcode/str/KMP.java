package com.sparo.leetcode.str;

/**
 * description:
 * Created by sdh on 2020-01-19

 */
public class KMP {

    public static void main(String[] args) {

        String mainStr = "abbcefgh";
        String subStr = "bce";
        KMP kmp = new KMP();
        int index = kmp.bruteMode(mainStr,  subStr);
        System.out.println("brute kmp result--index>>" + index);
    }

    /**
     * 暴力破解 返回子串在主串中的位置
     */
    public int bruteMode(String mainStr, String subStr) {
        int i = 0, j = 0;
       while(i<mainStr.length() && j<subStr.length()) {
           if(mainStr.charAt(i) == subStr.charAt(j)) {
                i++;
                j++;
           } else {
               i = i-j+1;
               j=0;
           }
       }
       if(j>=subStr.length()) {
          return i- subStr.length();
       } else {
           return -1;
       }
    }
}
