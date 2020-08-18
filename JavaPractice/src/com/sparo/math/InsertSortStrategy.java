package com.sparo.math;

/**
 * description: 插入排序的逻辑
 *
 * 后一个元素在插入前，前面的元素要保持有序（即已经有序），
 * 如果后一个元素比要插入的前一元素小则同前一个元素交换位置，
 * 以此规律直到无法交换为止
 *
 * sum: 总结比较下面的两种method，后者比前者的优势（如果method1 处理了break）
 * 后者少了多余次数的 temp 设定，其余的次数基本一致，可从count/sumCount看出
 *
 * Created by sdh on 2019-03-27
 */
public class InsertSortStrategy implements IAlgorithmStrategy {

    int count = 0;

    @Override
    public int[] sort(int[] arr) {
        /**
         * 1 明确什么是插入排序 ✅
         * 2 遍历数组 完成插入排序
         * 3 优化？时间空间复杂度
          */
//        for(int item : array) {
//            不便于交换前后位置元素【位置涉及角标】
//        }
        int[] sortArr = null;
        // method 1
//        sortArr = insertSort(arr);
        // method 2
        sortArr = insertSortPlus(arr);
        return sortArr;
    }

    private int[] insertSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
           for(int j = i; j > 0; j--) {
               // 这里比较的是当前和前一个元素 不是当前和固定位置的元素
               // （因为元素已经发生了转变/大小比较后位置有变）
               if (arr[j-1]>arr[j]) {
                   int temp = arr[j-1];
                   arr[j-1] = arr[j];
                   arr[j] = temp;
               }
               else {
                   /**
                    * 相对于新插入的元素，前面的元素序列已经有序，
                    * 新插入的元素如果比前面的元素大就不需要再进行从后向前的比较置换了，
                    * 因为已经是有序排列了，如果比前面的元素小，则继续置换直到比前置有序
                    * 序列最后的某个元素大即停止内部的向前遍历，发起下一次外层循环
                    */
                   break;
               }
               // 用于比较 else break有无时的算法性能 46/91
               count ++;
               // 打印排序历史记录
//               printArray(arr);
           }
//           System.out.println("---------");
        }
        System.out.println("count-->>" + count);
        return arr;
    }

    /**
     * 快速排序的plus版
     * @param arr
     * @return
     */
    static int sumCount = 0;
    public static int[] insertSortPlus(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            // 存储当前需要插入的 元素 arr[i]
            int temp = arr[i];
            int j=i;
            for (; j>0 && arr[j-1]>temp ; j--) {
                // 前后元素交换
                arr[j] = arr[j-1];
                // 46次同 method1 优化后的步数
                sumCount ++;
            }
            /**
             *  不需要j-1，需要插入的元素在最前面的位置，即内层循环结束
             *  （这里注意结束最后是j-1后再判断条件不满足，所以这里不再需要-1）
              */
            arr[j] = temp;
        }
        System.out.println("sumCount-->>" + sumCount);
        return arr;
    }

    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (i!=arr.length-1) {
                System.out.print(arr[i] + "->");
            } else {
                System.out.print(arr[i]);
            }
        }
        System.out.print("--->>>");
    }

}
