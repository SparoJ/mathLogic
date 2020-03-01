package com.sparo.math;

import com.sparo.util.Utils;

/**
 * description:
 * Created by sdh on 2019-03-27
 */
public class ShellSortStrategy implements IAlgorithmStrategy{
    @Override
    public int[] sort(int[] array) {
        return new int[0];
    }

    /**
     * 优化思路：
     * @param array
     * @return
     */
    public int[] shellSort(int[] array) {
        int len = array.length;
        int d = len;
        /**
         * 每次循环 隔d 步长去比较，然后依次缩小步长
          */
        // 插入排序的 d 步长
        while(d>1) {
            d /=2;
            //  对步长所对应的每一个匹配对做子插入排序
            for(int x = 0; x< d; x++) {

            }
        }
        return null;
    }

    public void insertSort(int[] arr) {
        int length = arr.length;
        for(int i = 1; i < length; i++) {
            int j = i;
            int temp = arr[j];
            // 每次比较的插入的元素 ！！！ 回顾 前面的 insert 原始交换方案对比 本质比较元素都是 要插入的值
            for(; j >0 && temp<arr[j-1]; j--) {
                // 默认原始插入是比较即交换 ，优化版是 右移动，因为前面的序列肯定是有序的！
//                swap(arr, j, j-1);
                arr[j] = arr[j-1];
            }
            arr[j] = temp;
        }
        Utils.printIntArray(arr);
    }

    public void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public void swaps(int[] arr, int i, int j) {
        arr[i] = arr[i]^arr[j];
        arr[j] = arr[i]^arr[j];
//        arr[i] = arr[]^arr[j];
    }
}
