package com.sparo.leetcode.dp;

import com.sparo.util.Utils;

/**
 * 最长上升子序列
 * 给定一个无序的整数数组，找到其中最长上升子序列的长度。
 *
 * 示例:
 *
 * 输入: [10,9,2,5,3,7,101,18]
 * 输出: 4
 * 解释: 最长的上升子序列是 [2,3,7,101]，它的长度是 4。
 * 说明:
 *
 * 可能会有多种最长上升子序列的组合，你只需要输出对应的长度即可。
 * 你算法的时间复杂度应该为 O(n2) 。
 * 进阶: 你能将算法的时间复杂度降低到 O(n log n) 吗?
 *
 * 链接：https://leetcode-cn.com/problems/longest-increasing-subsequence
 * Created by sdh on 2020-06-25
 */
public class LengthOfLIS {

    public static void main(String[] args) {
        int[] nums = {6,3,5,10,11,2,9,14,13,7,4,8,12};
        int l = lengthOfLIS(nums);
        Utils.printWithTag("LIS->", l);
    }


    public static int lengthOfLIS(int[] nums) {
        int[] top = new int[nums.length];
        // 牌堆数初始化为 0
        int piles = 0;
        for (int i = 0; i < nums.length; i++) {
            // 要处理的扑克牌
            int poker = nums[i];

            /***** 搜索左侧边界的二分查找 *****/
            int left = 0, right = piles;
            while (left < right) {
                int mid = (left + right) / 2;
                if (top[mid] > poker) {
                    right = mid;
                } else if (top[mid] < poker) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
            /*********************************/

            // 没找到合适的牌堆，新建一堆
            if (left == piles) piles++;
            // 把这张牌放到牌堆顶
            Utils.printWithTag("LIS->Left->", left);
            top[left] = poker;
        }
        // 牌堆数就是 LIS 长度
        for (int i = 0; i < top.length; i++) {
            Utils.print("->i->"+top[i]);
        }
        return piles;
    }
}
