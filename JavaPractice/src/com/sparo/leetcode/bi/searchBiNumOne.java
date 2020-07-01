package com.sparo.leetcode.bi;

/**
 * description:剑指 Offer 53 - I. 在排序数组中查找数字 I
 * https://leetcode-cn.com/problems/zai-pai-xu-shu-zu-zhong-cha-zhao-shu-zi-lcof/
 * Created by sdh on 2020-07-01
 */
public class searchBiNumOne {

    public int search(int[] nums, int target) {

        // return searchNormal(nums, target);
        return searchBiOne(nums, target);
    }

    //常规遍历 o(n)
    private int searchNormal(int[] nums, int target) {
        int count = 0;
        for(int num : nums) {
            if(num == target) {
                count++;
            }
        }
        return count;
    }

    // 二分左右边界 lgn*2/lgn
    private int searchBiOne(int[] nums, int target) {
        //base case proguard safety
        if(nums == null || nums.length == 0) return 0;
        //❌ 不是直接取数组该位置的值 而是通过index再去获取值
        //int left = nums[0]; int right = nums[nums.length-1];
        int left = 0; int right = nums.length-1;
        //缩小边界判定 right-left+1
        while(left <= right) {
            int midL = left + (right-left)/2;
            if(nums[midL]>target) {
                right = midL-1;
            } else if(nums[midL]<target) {
                left = midL+1;
            } else {
                right = midL-1;
            }
        }
        if(left>=nums.length||nums[left]!=target) return 0;
        //记录左边界
        int l = left;
        // System.out.println("left->" + left + "->right->" + right);
        //优化2 ✅ 先左边界后右边界则 可不重置left 重置right，反过来则重置left 不重置right，不能两者都不重置 [1] 1 / right = -1 left = 0;
        // left = 0;
        right = nums.length-1;
        while(left<=right) {
            int midR = left + (right-left)/2;
            if(nums[midR]>target) {
                right = midR-1;
            }else if(nums[midR]<target) {
                left = midR+1;
            } else {
                left = midR+1;
            }
        }
        //这里不会出现 只要出现一个边界合规 则另一个肯定存在，前面已经判定是否存在如不存在则直接return
        //❌ 这里右边界right 可为0，左边界也可为0 「（1） target 为1 ，left = right = 0」
        if(right<0||nums[right]!=target) return 0;
        return right-l+1;
    }
}
