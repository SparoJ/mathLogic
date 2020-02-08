package com.sparo.math;

import java.util.Arrays;

/**
 * description:
 * Created by sdh on 2019-03-27
 */
public class BubbleSortStrategy implements IAlgorithmStrategy {

    @Override
    public int[] sort(int[] array) {
        int[] copyArr = Arrays.copyOf(array, array.length);
//        bubbleSort(array);
//        bubbleSortPlusA(array);
        bubbleSortPlusB(array);
        return array;
    }

    public void bubbleSort(int[] arr) {
        int length = arr.length;
        // 冒泡的次数
       for(int i = 0; i < length; i++) {
           // 冒泡一次即有序一次，则比较前0-length-i即可（因为这个序列段才是无序需要冒泡来保证有序）-1防止过界
           for(int j = 0; j < length-i-1; j++) {
               if(arr[j]> arr[j+1]) {
                   swap(arr, j, j+1);
               }
           }
       }
    }

    //有序后（如果数组中不存在交换时）不必继续比较优化
    public void bubbleSortPlusA(int[] arr) {
        int length = arr.length;
        boolean isSorted = true;
        for(int i = 0; i < length; i++) {
            for(int j = 0; j < length -i -1; j++) {
                if(arr[j] > arr[j+1]) {
                    swap(arr, j, j+1);
                    isSorted = false;
                }
            }
            //表明已经有序不需要后续次数的比较
            if (isSorted) {
                return;
            }
        }
    }
    //
    public void bubbleSortPlusB(int[] arr) {
        int length = arr.length;
        boolean isSorted = true;
        int lastSwapIndex = 0;
        int border = length-1;
        for(int i = 0; i< length; i++) {
            for(int j = 0; j < border; j++) {
                if(arr[j]>arr[j+1]) {
                    swap(arr, j, j+1);
                    isSorted = false;
                    lastSwapIndex = j;
                }
            }
            border = lastSwapIndex;
            if(isSorted) {
                return;
            }
        }
    }

    public void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
