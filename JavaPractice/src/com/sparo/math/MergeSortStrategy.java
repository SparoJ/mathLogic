package com.sparo.math;

import com.sparo.util.Utils;

/**
 * description:
 * Created by sdh on 2020-01-20
 */
public class MergeSortStrategy implements IAlgorithmStrategy {

    @Override
    public int[] sort(int[] array) {

        mergeSort(array);
        return array;
    }

    private void mergeSort(int[] arr) {
        int length = arr.length;
        //临时存储数据数组，避免污染原数组数据
        int[] temp = new int[length];
        //递归归并0-length-1 的数据
        recursionMerge(arr, 0, length-1, temp);
        Utils.printIntArray(temp);
        //copy temp to arr
//        for(int i = 0; i<length; i++) {
//            arr[i] = temp[i];
//        }
        // 测试两者结果
//        System.arraycopy(temp, 0, arr, 0, length);
    }

    private void recursionMerge(int[] arr, int left, int right, int[] temp) {
        //当左右脚标不等时才进行recursion 和 merge 分治归并
        if(left < right) { // 相等则不需要进行merge 的分治处理 理解不同情况下的 运算符号
            int middle = left + (right-left)/2;
            recursionMerge(arr, left, middle, temp);
            recursionMerge(arr, middle+1, right, temp);
            // 归并操作
            System.out.println("merge--left>>" + left + "->middle->" + middle + "->right->" + right);
            merge(arr, left, middle, right, temp);
        }
    }

    private void merge(int[] arr, int left, int middle, int right, int[] temp) {
        int i = left; // 左sort后的头指针
        int j = middle+1; // 右sort后的头指针
        int t = 0; // 临时指针
        while(i<=middle && j<=right) {
            if(arr[i] <= arr[j]) {
                temp[t++] = arr[i++]; // 不能用i 😅
            } else {
                temp[t++] = arr[j++];
            }
        }
        // 一旦一边元素比较完成后，如果存在剩余左侧或者右侧子数组元素处理
        while(i<=middle) { //这里的while 就是循环遍历添加！
            temp[t++] = arr[i++];
        }
        while(j<=right) {
            temp[t++] = arr[j++];
        }
        t = 0;

        while(left <= right) {
            arr[left] = temp[t];
            System.out.println("--arr---" + "left-->>" + left + "->t"+ t + "->arr[left]->" + arr[left] + "->temp t->" + temp[t]);
            left++;
            t++;
        }
        System.out.println("arr->" + "down");
        Utils.printIntArray(temp);
        Utils.printIntArray(arr);
        System.out.println("arr->" + "above");
        char c = 's';
        if ('a' <= c) {
            
        }
    }
}
