package com.sparo.math;

/**
 * description:
 * Created by sdh on 2019-11-24
 */
public class QuickSortStrategy implements IAlgorithmStrategy {

    private int getIndexPoint(int[] array, int left, int right) {
        int leftBoundary = left;
        int rightBoundary = right;
        int keyPrivot = array[left];
        /**
         * 问题：
         * 1. 如果不考虑 = 会怎样
         * 2. 如果不考虑 超界会怎样 leftBoundary 和 rightBoundary的相对位置关系
         */
        while(leftBoundary < rightBoundary) {
            while(array[rightBoundary] >= keyPrivot && leftBoundary<rightBoundary) {
                rightBoundary --;
            }
            while(array[leftBoundary] <= keyPrivot && leftBoundary<rightBoundary) {
                leftBoundary++;
            }
            if(leftBoundary < rightBoundary) {
                //直到找到left 边界 第一个大于 锚点值 的 元素同时找到right 边界第一个小于 锚点值的元素 然后switch 直到找到临界point返回 ！！！
                array[leftBoundary] = array[leftBoundary] ^ array[rightBoundary];
                array[rightBoundary] = array[leftBoundary] ^ array[rightBoundary];
                array[leftBoundary] = array[leftBoundary] ^ array[rightBoundary];
            }
            /**
             * 使用位运算并不一定比 使用 临时变量效率高！！！，这里相反需要使用临时变量的方案
             */
        }
        // 交换 keyPrivot 元素和 array[leftBoundary] 的元素 供后续递归使用
        array[left] = array[leftBoundary];
        array[leftBoundary] = keyPrivot;

        return leftBoundary;
    }

    @Override
    public int[] sort(int[] array) {

        quickSort(array, 0, array.length-1);

        return array;


    }

    /**
     * 递归调用方法
     * @param array
     * @param left
     * @param right
     */
    private void quickSort(int[] array, int left, int right) {
        //递归缺少的终止条件
        if (left>right) {
            return;
        }
        int indexPoint = getIndexPoint(array, left, right);
        quickSort(array, left, indexPoint-1);
        quickSort(array, indexPoint+1, right);
    }
}
