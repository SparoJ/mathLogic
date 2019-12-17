package com.sparo.util;

/**
 * description:
 * Created by sdh on 2019-07-19
 */
public class Utils {

    public static void println(Object o) {
        System.out.println(o);
    }

    @SuppressWarnings("unchecked")
    public static void printIntArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (i!=arr.length-1) {
                System.out.print(arr[i]+"->");
            } else {
                System.out.print(arr[i]);
            }
        }
        System.out.println("------------------------");
    }

    public static void print(Object o) {
        System.out.print(o);
    }
}
