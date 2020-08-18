package com.sparo.test.review.sort;

import com.sparo.util.Utils;

/**
 * description: 重写代码之 选择排序
 * Created by sdh on 2020-03-01
 * 什么是选择排序？
 * 选择排序不同于冒泡排序，选择排序每次排序选出当前未排序列的最小值，
 * 并将最小值和左侧未排序的第一个位置交换，所以选择排序不是稳定排序（简单理解不是左右交换的都不是稳定排序）
 * 选择排序的特点是什么？
 * 每次选择无序序列中的最小元素出来然后放到无序序列头，再从剩下的无序序列继续后续流程
 * 选择排序如何优化？
 * 时间空间复杂度？
 *
 * 选择排序的最后优化：
 * 什么是堆？ 堆和数组之间的关系？什么是堆排序？堆排序如何实现？heapify / shiftUp 和 shiftDown 如何实现？
 * 堆在数据结构中是 完全二叉树，且满足父节点和子节点之间存在 满足父节点都大于和小于的子节点 分别对应最大堆和最小堆
 *
 * 堆和数组的关系是：堆是定义在完全二叉树基础上的，而完全二叉树有从上到下从左到右排满的特性，即满足左边排满再排右边子元素
 * （另外完全二叉树的高度 以及二叉树左右边界节点都有特性）
 *  完全二叉树可以用数组来表示从左到右的关系，这里即使用数组来构建堆对应的完全二叉树性质，同时考虑节之间点对应在数组中的脚标
 *  位置关系
 */
public class TestSortSerialSelect {

    public static void main(String[] args) {
        int[] arr = {3, 3, 6, 7, 4, 3, 9, 10, 2, 3, 1, 5, 0, 8};
        TestSortSerialSelect tsss = new TestSortSerialSelect();
//        tsss.selectSort(arr);
//        tsss.selectSortOriginal(arr);
//        Utils.printIntArray(arr);
        tsss.heapSort(arr);
        System.out.println("count->" + count);
    }

    private int getLength(int[] nums) {
        return nums.length;
    }

    /**
     * 原始选择排序
     * @param nums
     */
    private void selectSortOriginal(int[] nums) {
        int length = getLength(nums);
        for(int i = 0; i<length-1; i++) {
           // 比较对应i位置的元素和所有i后面的元素，小则完成交换
            for(int j = i+1; j<length; j++) {
                if(nums[j]<nums[i]) {
                    swap(nums, i, j);
                }
            }
        }
    }


    static int count = 0;

    /**
     * 正统优化版选择排序
     * @param nums
     */
    private void selectSort(int[] nums) {
        int length = getLength(nums);
        //选择最小值的次数
        for(int i = 0; i<length-1; i++) {
            //save遍历过程中的最小值 查找下一次无序序列的最小值前重置这里的 最小值
            int minVal = Integer.MAX_VALUE;
            // 最小值所在脚标
            int minIndex = -1;
            //选择当前无序序列最小值，即排除i之前的有序序列
            //int j = i;
            for(int j = i; j<length; j++) {
                if(minVal>nums[j]) {
                    minVal = nums[j];
                    minIndex = j;
                }
                count++;
            }
            //交换的 index 不是 i 和 j 这里j必然会跟随for到数组最后一个位置 这样是为了减少交换元素 只是做元素的转移
            swap(nums, i, minIndex);
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }


    /**
     * 选择排序终版 堆排序
     */

    /**
     * 堆排序的思路
     */
    public void heapSort(int[] nums) {
        //safe
        if(nums == null || nums.length==0) return;
        /**
         * heapify 二叉堆化
         * 从倒数第一个非叶子节点开始到root节点，下沉（完成堆的 shiftDown操作）
         * 每完成一次shiftDown 则完成当前次数的shiftDown位置节点对应的二叉树
         * 符合当前要构建的二叉堆（最小或者最大）的要求
         */
        //因为需要脚标所以使用 普通for 循环  最后一个非叶子结点脚标根据最后一个叶子节点可以算出
        int lastUnLeafIndex = getParentIndex(nums.length-1);
        //这也是为什么之前写的for 循环起点i 赋值为 (nums.length-2)/2的原因
        for(int i = lastUnLeafIndex; i>=0; i--) {
            /**
             * 这里符合需求的shiftDown不再是简单的堆化场景需要，
             * 因为在处理堆化之后的排序需要依赖 shiftDown到指定位置 shiftDown结束位置
             */
            shiftDown(nums, i, nums.length);
        }
        System.out.println("heapify 堆化完成后的数组->->->");
        Utils.printIntArray(nums);
        /**
         * 将最大堆的第一个root元素 和最后一个元素交换后 将root位置的元素下沉
         * 总共要完成对应元素个次数
         * 每次操作为 交换+下沉
         */
//        for(int i = nums.length-1; i>=1;) {
//            swap(nums, i, 0);
//            i--;
//            shiftDown(nums, 0, i);
//        }

        int size = nums.length;
        while(size>0) {
            swap(nums, 0, size-1);
            size--;
            shiftDown(nums, 0, size);
        }
        System.out.println("完成交换及root位置元素下沉后的数组->->->");
        Utils.printIntArray(nums);
    }

    /**
     * 二叉堆下沉 如果是正序 从大到小排序则构建最大堆， 如果是逆序 从小到大排序则构建最小堆
     * 这里正序 构建最大堆
     * @param nums
     * @param startIndex 下沉脚标
     * @param endIndex 下沉结束边界
     */
    public void shiftDown(int[] nums, int startIndex, int endIndex) {
        //临时变量存当前需要拆入元素的值 的原因在下面 （说明）有给出解释
        int temp = nums[startIndex];
        //结束条件是 要下沉位置节点的子节点还在允许的取值范围
        int childIndex = 2*startIndex+1;//getLeftChildIndex(startIndex);
        while(childIndex < endIndex) {
            if(childIndex+1<endIndex && nums[childIndex+1]>nums[childIndex]) {
                childIndex++;
            }
            //如果当前已经满足了最大堆的性质，即节点的值大于子节点的值 终止当前startIndex位置元素的下沉
            if(temp>=nums[childIndex]) {
                break;
            }
            //否则完成下沉的元素交换操作
            /**
             * 这里的下沉不能通过如下注释后的代码实现，注释代码单次看行为符合要求
             * 但并不满足交换之后下一次符合需求的交换（说明）
             */
//            swap(nums, startIndex, childIndex); 这里永远只改变了root位置的值，只堆root位置的值做了交换
            nums[startIndex] = nums[childIndex];
            /**
             * 将当前位置元素作为下一次判断的parent元素，即startIndex 重新赋值
             * 将当前下沉位置的元素的左子节点元素脚标作为下一次判断的初始值？？？
             */
            startIndex = childIndex;
            childIndex = 2*childIndex+1;
        }
        //插入到最终位置 startIndex 而非childIndex 因为此时while判断的 childIndex 已经不在数组长度约束内
        nums[startIndex] = temp;
        Utils.printIntArray(nums);
    }

    /**
     * 获取节点位置之间的关系 parent 和 child 节点之间的关系
     * 以 0 作为root 位置 ...1/2/3...
     */
    public int getParentIndex(int childIndex) {
        return (childIndex-1)>>1;
    }

    public int getLeftChildIndex(int parentIndex) {
        return  (parentIndex<<1)+1;
    }

    public int getRightChildIndex(int parentIndex) {
        return (parentIndex+1)<<1;
    }

}
