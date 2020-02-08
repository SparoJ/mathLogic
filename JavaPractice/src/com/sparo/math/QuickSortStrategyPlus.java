package com.sparo.math;

import com.sparo.util.Utils;

/**
 * description: 挖洞 + 分治 参见@ReTestSerial.class
 * 注意⚠️点：在递归终止条件处改为>= 而非 left>right 符合后面while循环的判断截止条件
 *   另外可换个角度思考 当一次partition完成后，所return的index 为对应pivot 元素最终排序所处的位置（左侧比pivot小，右侧比pivot大 满足最终的位置定位要求）
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
        System.out.println("quick->" + "left->" + left + "right->" + right);
        if(arr == null || (left >=right) || arr.length == 0 ) {
            return;
        }

       int partition =  partition(arr, left, right);
       System.out.println("partition-->>" + partition);
        quickSortPlus(arr, left, partition); // 不带最后的节点  即 not quickSortPlus(arr, left, partition); 或者在前面递归终止条件处改为>=
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
        System.out.println("left->" + left + "->right->" + right);
        Utils.printIntArray(arr);
        return left;
    }

//    int[] arr = {3, 3, 6, 7 , 4, 3, 9, 10, 2 , 3, 1, 5, 0, 8};

//    quickSortPlus(arr, left, partition-1)
//    left->4->right->4
//            0->3->1->2->3->3->9->10->4->3->7->5->6->8------------------------
//    partition-->>4
//    left->0->right->0
//            0->3->1->2->3->3->9->10->4->3->7->5->6->8------------------------
//    partition-->>0
//    left->3->right->3
//            0->2->1->3->3->3->9->10->4->3->7->5->6->8------------------------
//    partition-->>3
//    left->2->right->2
//            0->1->2->3->3->3->9->10->4->3->7->5->6->8------------------------


//   stackOverFlow  -->> 带最后的节点  即 not quickSortPlus(arr, left, partition);
//    left->4->right->4
//            0->3->1->2->3->3->9->10->4->3->7->5->6->8------------------------
//    partition-->>4
//    left->0->right->0
//            0->3->1->2->3->3->9->10->4->3->7->5->6->8------------------------
//    partition-->>0 // 因为return left ， left 和right 一直为0，则while 递归一直执行不会走return
//    left->0->right->0
//            0->3->1->2->3->3->9->10->4->3->7->5->6->8------------------------
//    partition-->>0
//    left->0->right->0
//            0->3->1->2->3->3->9->10->4->3->7->5->6->8------------------------
}
