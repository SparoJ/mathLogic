package com.sparo.math;

/**
 * description:
 * Created by sdh on 2019-03-27
 */
public class SelectSortStrategy implements IAlgorithmStrategy {

    @Override
    public int[] sort(int[] array) {
        selectSort(array);
        return array;
    }

    // i<length-1/ j=i+1
    public void selectSort(int[] arr) {
        int length = arr.length;
        //获取当前次数对应的最小值 (n-1)
        for(int i = 0; i < length-1; i++) {
            // 选出如下index 位置的最小值同i位置交换
            /**
             * 并没有将最小值（比如第一次最小值为最左侧的item 这里每次for 都没有考虑该情况的处理）
             */
//            int minValue = arr[i];
//            int j = i+1;
//            int index = j;
//            for(; j < length; j++) {
//                if(arr[j]<minValue) {
////                    minValue = arr[j];
//                    index = j;
//                }
//            }
//            System.out.print( + "->");
//            //swap
//            if(index<length) {
//                swap(arr, i , index);
//            }

            //这里表达含义是 i 而不是j 虽然j 这里 = i
            int minIndex = i;
            //修正 并不是要获取最小值，要对应最小值的index 0-n-1/1-n-1/2-n-1/  因为minIndex 为 i，已经在for 比较中比较了首位置（j=i+1）
            int j = i+1;

            for(;j<length;j++) {
                minIndex = arr[minIndex]<arr[j]?minIndex:j;
            }
            swap(arr, minIndex, i);
        }
    }

    public void swap(int[] arr, int i, int j) {
        // swap 写错！！！
//        int temp = arr[i];
//        arr[j] = arr[i];
//        arr[i] = temp;

        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
