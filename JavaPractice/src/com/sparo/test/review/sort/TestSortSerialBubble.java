package com.sparo.test.review.sort;

import com.sparo.util.Utils;

/**
 *  遗忘指数***** 5为max bubble 的 优化2方案 重置边界 almost forget
 *  description:重新手写排序基础算法 之 冒泡排序优化三部曲
 *  https://mp.weixin.qq.com/s/wO11PDZSM5pQ0DfbQjKRQA （冒泡排序）
 *  https://mp.weixin.qq.com/s/CoVZrvis6BnxBQgQrdc5kA （鸡尾酒排序）
 *  什么是冒泡排序？
 *  冒泡排序如何优化？
 *  冒泡排序如何优化，为什么这么优化？代码上的实现原理是什么？
 *  时间空间复杂度 是否稳定？（稳定针对元素值相同的场景）
 * Created by sdh on 2020-03-01
 */
public class TestSortSerialBubble {

    public static void main(String[] args) {
        /**
         * arr length =14, 每冒泡一次有序序列+1，冒泡内循环需要执行次数为 （length-冒泡次数）
         */
        int[] arr = {7, 3, 6, 3, 4, 3, 9, 10, 2, 3, 1, 5, 0, 8};
//        int[] arr = {10,9,8,7,6,4,5,3,3,3,3,2,1,0};

        //冒泡A
        TestSortSerialBubble tss = new TestSortSerialBubble();
//        tss.bubbleSortOriginal(arr);
//        Utils.printIntArray(arr);
//        System.out.println("count-->" + count); //91
//        tss.bubbleSortOptimizeIsSorted(arr);
//        System.out.println("SortedCount-->" + count); //91 对于无序数组段 中已经有序的部分排序有优化效果

//        tss.bubbleSortIndexRelocate(arr);
//        System.out.println("RelocateCount-->" + count); // 81  对非完全逆序都有优化效果，对于数组右侧已经部分有序则可起到优化效果
//
//        tss.bubbleSortOptimizeCocktail(arr);
//        System.out.println("CocktailCount-->" + count); // 89(左右index 未选择 leftBorder和 rightBorder see)  // 修正后 74
//
        //快排
        tss.quickNew(arr);
        Utils.printIntArray(arr);
    }

    /**
     * definition of bubble sort
     * 什么是冒泡排序，通过比较相邻元素并交换对应位置上的元素值，
     * 每次循环得到当前次比较后的最大或者最小元素到数组一端，
     * 执行非有序序列个数的外层循环即可得到最终的有序数组
     * <p>
     * 细节：将数组分为两半，一半为无序数组，一半为有序数组， 有序为经过冒泡后排好序的一端数列
     * 每冒泡一次有序数组长度+1，总共需要的全量冒泡次数为无序数组长度-冒泡次数（可再-1长度，因为右侧有序为第k+1 到 整个数组最大的数）
     * 原始冒泡排序 o（n*n） 空间原地交换 o（1）
     *
     * @param nums
     */
    static int count = 0;
    private void bubbleSortOriginal(int[] nums) {
        int length = nums.length;
        //理解这里为什么可以少外循环一次，以及内循环为什么-i 且-1
        for (int i = 0; i < length - 1; i++) {
            for (int j = 0; j < length - i - 1; j++) {
                if (nums[j] > nums[j + 1]) {
                    swap(nums, j, j + 1);
                }
                count++;
            }
        }
    }

    private void bubbleSortOptimizeIsSorted(int[] nums) {
        int length = nums.length;
        boolean isSorted = true;
        for (int i = 0; i < length - 1; i++) {
            for (int j = 0; j < length - i - 1; j++) {
                if (nums[j] > nums[j + 1]) {
                    swap(nums, j, j + 1);
                    isSorted = false;
                }
                count++;
//                Utils.printIntArray(nums);
            }
            if (isSorted) {
                break;
            }
        }
    }

    private int getLength(int[] nums){
        return nums.length;
    }

    /**
     * !!!!!!!!!!!!!!!!!!遗忘指数***
     * 遍历位置偏移，当进行对应次数的冒泡排序后，并不是每次都会符合左右数据大小需要交换的情况
     * 如果之后的元素不再需要交换，则可以减少外层的循环次数截止到最后交换的位置，每次动态修正这一值
     * @param nums
     */
    private void bubbleSortIndexRelocate(int[] nums) {
        int length = getLength(nums);
       //每一次冒泡完成时 最后一次交换元素的位置
        int lastSwapIndex = -1;
        // 动态修正边界初始化
        int border = length-1;
        boolean isSorted = true;
        for(int i =0; i < length-1; i++) {
            /**
             * 内层循环的 终止结束条件本质是 有序和无序段的分界点 这里j<border而不是<= 并不代表没有取到border 这个脚标位置的值
             * 因为比较的是 j 和 j+1
             */

          for(int j = 0; j < border; j++) {
              if(nums[j]>nums[j+1]) {
                  swap(nums, j, j+1);
                  isSorted = false;
                  lastSwapIndex = j;
              }
              count++;
          }
          border = lastSwapIndex;
          if(isSorted) {
              break;
          }
        }
    }

    /**
     * 鸡尾酒排序 是对冒泡排序的进一步优化，适用于当数组中只有少数元素处于无序状态，其他元素基本保持有序
     * 核心实现是 分别从左到右比较移动较大元素 和 从右到左比较移动较小元素
     * @param nums
     */
    private void bubbleSortOptimizeCocktail(int[] nums) {
        int length = getLength(nums);
        int rightBorder = length-1;
        int leftBorder = 0;
        // 右侧最后一次swap 的index
        int lastRightSwapIndex = -1;
        // 左侧最后一次swap 的index
        int lastLeftSwapIndex = -1;
        boolean isSorted;
        for(int i = 0; i<length/2; i++) {
            //从左到右
            isSorted = true;
            // int j = i; 这里左侧有序 适用leftBorder
            for(int j =leftBorder; j<rightBorder; j++) {
                if(nums[j]>nums[j+1]) {
                    swap(nums, j, j+1);
                    lastRightSwapIndex = j;
                    isSorted = false;
                }
                count++;
            }
            rightBorder = lastRightSwapIndex;
            if (isSorted) {
                break;
            }
            // int k = length-1;!!!! 这里右侧有序 适用rightBorder  否则使用左右边界的优点体现不出来
            for(int k = rightBorder; k> leftBorder; k--) {
                if(nums[k]<nums[k-1]) {
                    swap(nums, k, k-1);
                    lastLeftSwapIndex = k;
                    isSorted = false;
                }
                count++;
            }
            leftBorder = lastLeftSwapIndex;
            if(isSorted) {
             break;
            }
        }
    }


    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }


    /**
     * 冒泡排序优化 终极 快速排序
     */

    public void quickNew(int[] nums) {
        if(nums== null || nums.length == 0) return;
        quickHelper(nums, 0, nums.length-1);
    }

    /**
     * 递归分治思想
     * @param nums
     * @param left
     * @param right
     */
    public void quickHelper(int[] nums, int left, int right) {
        //递归结束条件
        if(left>=right) return;
        int partion = quickPartion(nums, left, right);
        //每次锚点定位后即是最终位于数组的脚标位置，故递归时分别+-1，从partation index处分离
        quickHelper(nums, left, partion-1);
        quickHelper(nums, partion+1, right);
    }

    /**
     * 从nums 数组序列中得到从left下标到 right 下标的 pivot 锚点脚标
     * 锚点元素值通常给left 初始位置对应的元素，也可避免取值不匀导致复杂度提升
     * 可选择 左 中 右三者的中间值
     * 获取元素最后稳定在数组中位置的算法思路是：
     * 从右往左找第一个比 left 元素小的移到，从左往右找第一个比 left 元素大的
     * @param nums 数组
     * @param left 左边界
     * @param right 右边界
     * @return
     */
    public int quickPartion(int[] nums, int left, int right) {
        System.out.println("partation前递归入参左右脚标值-->>" + "->left->" + left + "->right->" + right);
        //暂存锚点值
        /**
         * 思考 结束条件是否需要= 号， 何时脚标自加/自减，何时赋值锚点最终脚标位置
         */
        int temp = nums[left];
        while(left<right) {

            while(left<right && nums[right]>=temp) {
                right--;
            }
            if(left<right) {
                nums[left] = nums[right];
                left++;
            }
            while(left<right && nums[left]<=temp) {
                left++;
            }
            if(left<right) {
                nums[right] = nums[left];
                right--;
            }
        }
        nums[left] = temp;
        System.out.println("每次快排partation 之后数组--->>>" + "partation->>" + left);
        Utils.printIntArray(nums);
        return left;
    }

}
