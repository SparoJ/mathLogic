package com.sparo.math;

import com.sparo.util.Utils;

/**
 * description:
 * Created by sdh on 2020-02-04
 */
public class HeapSortStrategy {

    public static void main(String[] args) {
        HeapSortStrategy hss = new HeapSortStrategy();
        int[] arr = {3, 3, 6, 7 , 4, 3, 9, 10, 8, 2 , 3, 1, 5, 0};
        hss.sort(arr);
        Utils.printIntArray(arr);
    }

    public void sort(int[] arr) {

//1.创建最小堆
        createMinHeap(arr);
        int size = arr.length;
//2.交换堆顶元素和数组中的最后一个元素，再执行shiftDown 来实现排序
        while(size>0) {
            int temp = arr[0];
            arr[0] = arr[size-1];
            arr[size-1] = temp;
            size --;
            shiftDown(arr, 0, size);
        }
    }


    public void createMinHeap(int[] arr) {
        int length = arr.length;
//        for(int i= 0; i<=(arr.length-2)/2; i++) {
//            shiftDown(arr, i, length);
//        }
//        3->3->3->7->2->1->0->10->8->4->3->6->5->9------------------------ ！！！wrong
        for(int i = length/2-1; i>=0; i--) {
            shiftDown(arr, i, length);
        }
//        0->2->1->7->3->3->6->10->8->4->3->3->5->9------------------------
        Utils.printIntArray(arr);
    }

    //最小堆下沉 字段含义不清晰 导致逻辑混乱
    public void shiftDown(int[] arr, int parentIndex, int size) {
        int temp = arr[parentIndex];
        //得到i 位置元素对应的子元素childIndex
        int childIndex = 2*parentIndex+1;
        while(childIndex < size) { // size 可 = length-1
            // 找出左右子元素的大值
            if(childIndex+1<size && arr[childIndex] > arr[childIndex+1]) {
                childIndex++;
            }
            if(temp <= arr[childIndex]) {
                break;
            }
            // 传递值 最后赋值 !!!!! 如下几行赋值关系容易弄混
            arr[parentIndex] = arr[childIndex]; // ！！！wrong arr[childIndex] = arr[parentIndex];
//交换元素index 子index 为下一轮的 parent index
            parentIndex = childIndex;
            // 新一轮的childIndex 索引
            childIndex = childIndex*2 +1;
        }
//        arr[childIndex]  = temp;！！！ wrong
        arr[parentIndex] = temp;
    }


//    public void heapSort(int[] nums) {
//        int size = nums.length;
//        buildMinHeap(nums);
//        while (size != 0) {
//// 交换堆顶和最后一个元素
//            int tmp = nums[0];
//            nums[0] = nums[size - 1];
//            nums[size - 1] = tmp;
//            size--;
//            siftDown(nums, 0, size);
//            System.out.println("-----------------------");
//            Utils.printIntArray(nums);
//        }
//    }
//
//    // 建立小顶堆
//    private void buildMinHeap(int[] nums) {
//        int size = nums.length;
//        for (int j = size / 2 - 1; j >= 0; j--)
//            siftDown(nums, j, size); // 非 size-1 而是完整的size大小
//        Utils.printIntArray(nums);
//    }
//
//    private void siftDown(int[] nums, int i, int newSize) {
//        int key = nums[i];
//        while (i < newSize >>> 1) {
////            System.out.println("newSize-->>" + newSize + "->i->" + i);
////            if(i>0) {
////                return;
////            }
//            int leftChild = (i << 1) + 1;
//            int rightChild = leftChild + 1;
//// 最小的孩子，比最小的孩子还小
//            int min = (rightChild >= newSize || nums[leftChild] < nums[rightChild]) ? leftChild : rightChild;
//            if (key <= nums[min]){
//                System.out.println("i->" + i);
//                break;
//            }
//            nums[i] = nums[min];
//            if(i == 6) {
//                System.out.println("nums[i]->" + nums[i] + "->nums[min]->" + nums[min]);
//            }
//            i = min;
//        }
//        nums[i] = key;
//    }
}
