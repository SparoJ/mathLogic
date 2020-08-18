package com.sparo.test;

import com.sparo.util.Utils;

/**
 * description:
 * Created by sdh on 2020-07-23
 */
public class TestSort {

    public static void main(String[] args) {
        // 手写快排测试
        int[] arr = {3, 2, 1, 7 , 4, 3, 9, 10, 2 , 3, 1, 5, 0, 8};
//        heapSort(arr);
//        quickSort(arr, 0, arr.length-1);
        dualPivotQuickSort(arr, 0, arr.length-1);
        Utils.printIntArray(arr);
    }

    public static void dualPivotQuickSort(int[] items, int start, int end) {
        if (start < end) {
            if (items[start] > items[end]) {
                swap(items, start, end);
            }
            int pivot1 = items[start], pivot2 = items[end];
            int i = start, j = end, k = start + 1;
            OUT_LOOP: while (k < j) {
                if (items[k] < pivot1) {
                    System.out.println("ii->" + i + "->kk->" + k);
                    swap(items, ++i, k++);
                    System.out.println("i->" + items[i] + "->k->" + items[k]);
                } else if (items[k] <= pivot2) {
                    k++;
                } else {
                    while (items[--j] > pivot2) {
                        if (j <= k) {
                            // 扫描终止
                            break OUT_LOOP;
                        }
                    }

                    if (items[j] < pivot1) {
                        swap(items, j, k);
                        swap(items, ++i, k);
                    } else {
                        swap(items, j, k);
                    }
                    k++;
                }
            }
            swap(items, start, i);
            swap(items, end, j);

            dualPivotQuickSort(items, start, i - 1);
            dualPivotQuickSort(items, i + 1, j - 1);
            dualPivotQuickSort(items, j + 1, end);
        }
    }


    public int[] partition(int[] array,int left,int right){
        int v = array[right]; //选择右边界为基准
        int less = left - 1; // < v 部分的最后一个数
        int more = right + 1; // > v 部分的第一个数
        int cur = left;
        while(cur < more){
            if(array[cur] < v){
                swap(array,++less,cur++);
            }else if(array[cur] > v){
                swap(array,--more,cur);
            }else{
                cur++;
            }
        }
        return new int[]{less + 1,more - 1};  //返回的是 = v 区域的左右下标
    }
//    private void swap(int[] arr, int i, int j) {
//        int temp = arr[i];
//        arr[i] = arr[j];
//        arr[j] = temp;
//    }

    private static void quickSort(int[] arr, int left, int right) {
        if(left>=right) return;
        int index = partion(arr, left, right);
        quickSort(arr, left, index-1);
        quickSort(arr, index+1, right);
    }

    private static int partion(int[] arr, int left, int right) {
        int pivotVal = arr[left];
        while(left<right) {
            while(left<right && arr[right]>=pivotVal) {
                right--;
            }
            if(left<right) {
                arr[left] = arr[right];
                left++;
            }
            while(left<right && arr[left]<=pivotVal) {
                left++;
            }
            if(left<right) {
                arr[right] = arr[left];
                right--;
            }
        }
        arr[left] = pivotVal;
        return left;
    }

    private static void heapSort(int[] arr) {
        //heapify
        int lastIndex = arr.length-1;
        for(int i = (lastIndex-1)/2; i>=0; i--) {
            shiftDown(arr, i, lastIndex);
        }
        //sort
        while(lastIndex>=0) {
            swap(arr, 0, lastIndex);
            lastIndex--;
            //shiftDown
            shiftDown(arr, 0, lastIndex);
        }
    }

    private static void shiftDown(int[] arr, int shiftIndex, int endIndex) {
        int leftChildIndex = 2*shiftIndex+1;
        while(leftChildIndex<=endIndex) {
            if(leftChildIndex+1<=endIndex && arr[leftChildIndex]<arr[leftChildIndex+1]) {
                leftChildIndex++;
            }
            if(arr[shiftIndex]>=arr[leftChildIndex]) break;
            swap(arr, shiftIndex, leftChildIndex);
            shiftIndex = leftChildIndex;
            leftChildIndex = 2*leftChildIndex+1;
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
