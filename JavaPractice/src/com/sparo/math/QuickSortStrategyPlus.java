package com.sparo.math;

/**
 * description: 挖洞 + 分治
 * Created by sdh on 2019-12-03
 */
public class QuickSortStrategyPlus implements IAlgorithmStrategy {


    @Override
    public int[] sort(int[] array) {
//        if(array == null || array.length==0) {
//            return array;
//        }
       quickSortPlus(array, 0, array.length-1);
        return array;
    }

    public void quickSortPlus(int[] arr, int left, int right) {
        // 递归终止条件不能换到前面去！！！
        if(arr == null || (left >right) || arr.length == 0 ) {
            return;
        }

       int partition =  partition(arr, left, right);
       System.out.println("partition-->>" + partition);
        quickSortPlus(arr, left, partition);
        quickSortPlus(arr, partition+1, right);
    }

    public int partition(int[] arr, int left, int right) {
        int pivot = arr[left];
        while(left < right) {
            while(pivot <= arr[right] && left < right) {
                right --;
            }
            // 填锚点位置的坑 没必要还去比较两个数大小，但左右的index 需要比较
//            if(pivot > arr[right] && left < right) {
//                arr[left] = arr[right];
//                left ++;
//            }
            if(left < right) {
                arr[left] = arr[right];
                left ++;
            }
            while(pivot >= arr[left] && left < right) {
                left ++;
            }
//            if(pivot < arr[left] && left < right) {
//                arr[right] = arr[left];
//                right --;
//            }
            if(left < right) {
                arr[right] = arr[left];
                right --;
            }
        }
        // left == right时
        arr[left] = pivot;
        return left;
    }

}
