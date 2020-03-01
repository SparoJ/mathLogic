package com.sparo.math.base;

import com.sparo.math.MergeSortStrategy;
import com.sparo.util.Utils;

/**
 * description:
 * Created by sdh on 2020-02-02
 */
public class BinarySearch {

        public static void main(String[] args) {
            BinarySearch bs = new BinarySearch();
            int[] arr = {3, 3, 5, 7 , 7, 3, 9, 10, 2 , 3, 1, 5, 0, 8};
            MergeSortStrategy mss = new MergeSortStrategy();
            mss.sort(arr);
            Utils.printIntArray(arr);
            int index = bs.binarySearchMultiple(arr, 5);
            System.out.println("index->>" + index);
        }

        public int binarySearch(int[] arr, int item) {
            int left = 0;
            int right = arr.length-1;
            int index = -1;
            while(left <= right) { // mis left == right 8-> < 可行 但 4 <= 才可行
                int mid = left+((right-left)>>1)/2; // 运算符号 的优先级 大于 位运算操作！！！
                if(item < arr[mid]) {
                  right = mid-1;
                } else if(item > arr[mid]) {
                    left = mid+1;
                } else {
//                    System.out.println("left->" + left + "right->" + right + "mid->" + mid + "index->" + index);
                    //  返回最后一次出现的index 需要元素向右移动否则 while循环持续执行
                    left = mid+1; // = 同于 binarySearchLastIndex
                    index = mid;
//                    return mid; // 如果存在相同元素 则返回第一次出现的index
                }
            }
//            return -1;
            return index;
        }


    /**
     * 总结：如果想要将元素添加到指定位置
     * 要求-> 按照元素大小的先后顺序，排在小于该元素和 大于该元素位置之间 则找到小于该元素最近的位置+1 or 找到大于该元素最近的位置
     * 这两个标记位置 即 小于该元素最近的位置  和 大于该元素最近的位置 可对应出现重复元素时的前和后位置关系（x555x）
     */
    public int binarySearchMultiple(int[] arr, int item) {
        int left = 0;
        int right = arr.length-1;
        int mid = 0;
//            int index = -1;
        while(left <= right) { // mis left == right 8-> < 可行 但 4 <= 才可行
            mid = left+((right-left)>>1)/2; // 运算符号 的优先级 大于 位运算操作！！！
            System.out.println("!!!!!!!!!!left->" + left + "->right->" + right + "->mid->" + mid);
            if(arr[mid]>item) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        Utils.printIntArray(arr);
        System.out.println("left->" + left + "->right->" + right + "->mid->" + mid);
        return arr[left-1] == item ? left-1:-1; // 最后出现的位置

//        return arr[mid] == item?mid:-1; // 第一次出现的位置
//        return arr[right+1]>item?right+1:-1; // 返回 > item 最近index 9  arr[mid]>item

//        return arr[left-1] < item? left-1:-1; // index = 6 注意使用场景 和 arr[mid]>=item 的关系
//            return index;
    }


}
