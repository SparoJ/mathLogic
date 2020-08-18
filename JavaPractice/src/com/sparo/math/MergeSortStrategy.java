package com.sparo.math;

import com.sparo.util.Utils;

/**
 * description:
 * Created by sdh on 2020-01-20
 */
public class MergeSortStrategy implements IAlgorithmStrategy {

    @Override
    public int[] sort(int[] array) {

//        mergeSort(array);
        mergeSortII(array);
        Utils.printIntArray(array);
        return array;
    }

    private void mergeSort(int[] arr) {
        int length = arr.length;
        //临时存储数据数组，避免污染原数组数据
        int[] temp = new int[length];
        //递归归并0-length-1 的数据
        recursionMerge(arr, 0, length-1, temp);
        Utils.print("temp->Arr");
        Utils.printIntArray(temp);
        Utils.print("src->Arr");
        Utils.printIntArray(arr);
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
//          System.out.println("merge--left>>" + left + "->middle->" + middle + "->right->" + right);
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
//        System.out.println("arr->" + "down");
        Utils.printIntArray(temp);
//        Utils.printIntArray(arr);
//        System.out.println("arr->" + "above");
//        char c = 's';
//        if ('a' <= c) {
//
//        }
    }

    //归并排序
    public void mergeSortII(int[] arr) {
        if(arr == null || arr.length<=1) return;
        int left = 0; int right = arr.length-1;
        int[] ans = new int[arr.length];
        mergeSortIIRecurHelper(arr, left, right, ans);
        Utils.printIntArray(arr);
        Utils.printIntArray(ans);
    }

    //递归帮助类
    private void mergeSortIIRecurHelper(int[] arr, int left, int right, int[] ans) {
        if(left<right) {
            int mid = left + (right-left)/2;
            mergeSortIIRecurHelper(arr, left, mid, ans);
            mergeSortIIRecurHelper(arr, mid+1, right, ans);
            mergeSortIICore(arr, left, mid, right, ans);
        }
    }

    /*递归到不能再分解为止时，两两合并，ans是用于缓存当前两两合并的临时记录数组，
     当前递归所在层合并完成后返回上一层当前结果，作为下一层合并的一半数据源，当另一半数据返回后再次执行本函数时即合并上一层数组结构
     底层 { 7 ,5, 3, 3, 7, 3, 9, 10, 2 , 3, 1, 5, 0, 8};
     递归到最底层时，两两合并排序， 75->57&&33->33 -->> 左半为57&右半为33 合并排序后为 3357
     （ans 分别临时记录 57，33，以及合并后的结果，均从0开始记录直至所有数据比对完成）
     */
    private void mergeSortIICore(int[] arr, int left, int mid, int right, int[] ans) {
        int t = 0;
        //两辆合并左侧数组的起点index
        int ls = left;
        //两两合并右侧数组的起点index
        int rs = mid+1;

        while(ls<=mid && rs<=right) {
            if(arr[ls]<=arr[rs]) {
                ans[t++]=arr[ls++];
            } else {
                ans[t++] = arr[rs++];
            }
        }
        while(ls<=mid) {
            ans[t++] = arr[ls++];
        }
        while(rs<=right) {
            ans[t++] = arr[rs++];
        }
        //将当前比对的temp 结果 （left-right之间的）copy到 ans中去
         t = 0;
        while(left<=right) {
            arr[left++] = ans[t++];
        }
    }


}
