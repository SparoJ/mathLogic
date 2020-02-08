package com.sparo.math;

import java.util.Arrays;

/**
 * description: 基数排序，适用于字符串排序，本质是按照位去比较排序，根据长度完成字符串长度次数的排序
 * 每一次都以该位置元素为基准做一次"计数排序"，这里字符以ascii码为参考
 * 时间复杂度 k(n+m) k为字符串长度，计数排序时间复杂度为 o(n+m) -> 原始数列长度为n, 计数需要排序的数列最大最小gap为m
 * Created by sdh on 2020-02-06
 */
public class BaseNumStrategy {

    // ascii 码的取值范围
    public static final int ASCII_RANGE = 128;

    public static String[] radixSort(String[] array, int maxLength) {
        //排序结果数组，用于存储每次一按位排序的临时结果
        String[] sortedArray = new String[array.length];
        //从个位开始比较，直到高位
        for(int k = maxLength -1; k>=0; k--) {
            //计数排序过程，分三步
            //1. 创建辅助排序的统计数组，并把带排序的字符对号入座
            //直接使用ascii 码范围作为数组长度
            int[] count = new int[ASCII_RANGE];
            for (int i = 0; i < array.length; i++) {
                //获取指定位置string 对应在 k 位置处的 char 在ascii的index
                int index = getCharIndex(array[i], k);
                count[index] ++;
            }
            //2.统计数组变形，后面元素等于前面元素之和
            for (int i = 1; i < count.length; i++) {
                //变形数组指定位置的count的值是当前值+前序位置的值 -> 该值含义：指明该位置对应映射的元素应该在排序后数组中的位置(第x位置)
                count[i] = count[i] + count[i-1];
            }
            //3. 倒序遍历原始数列，从统计数组找到正确位置，输出到结果数组
            /**
             * 参考计数排序中单个元素的处理方式，即count array 中 脚标和数组中存储的值
             * 这里的单个元素是单个值，而在这里是字符串，字符串中指定位置的元素ascii 作为 count array的下标
             * 按照该位置 index 的 char ascii 来排完一次序
             */
            for(int i = array.length-1; i>=0; i--) {
                int index = getCharIndex(array[i], k);
                int sortedIndex = count[index]-1;
                sortedArray[sortedIndex] = array[i];
                count[index]--;
            }
            //下一轮排序的字符串数组以上一轮排序后的数组为基础 so clone
            array = sortedArray.clone();
        }
        return array;
    }

    // 获取 str 字符串在
    private static int getCharIndex(String str, int k) {
        //比较的位置没有char 则返回0
        if (str.length() < k+1) {
            return 0;
        }
        //否则返回k位置对应char（char这里会自动转换成char对应的 ascii值）
        return str.charAt(k);
    }

    public static void main(String[] args) {
        String[] array = {"qd", "abc", "qwe", "hhh", "a", "cws", "ope"};
        String s = Arrays.toString(radixSort(array, 3));
        System.out.println(s);
    }
}
