package com.sparo.leetcode.bi;

/**
 * description:
 * 34. 在排序数组中查找元素的第一个和最后一个位置
 * https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array/
 * Created by sdh on 2020-07-01
 */

/**
 * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
 *
 * 你的算法时间复杂度必须是 O(log n) 级别。
 *
 * 如果数组中不存在目标值，返回 [-1, -1]。
 *
 * 示例 1:
 *
 * 输入: nums = [5,7,7,8,8,10], target = 8
 * 输出: [3,4]
 * 示例 2:
 *
 * 输入: nums = [5,7,7,8,8,10], target = 6
 * 输出: [-1,-1]
 *
 */
public class FindFirst$Last34 {

    public int[] searchRange(int[] nums, int target) {

        return searchRangeBinary(nums, target);
    }

    private int[] searchRangeBinary(int[] nums, int target) {
        int[] ans = new int[2];
        if(nums==null || nums.length==0) {
            ans[0] = ans[1] = -1;
            return ans;
        }
        int length = nums.length;
        int left = 0; int right = length-1;
        //left boundary
        while(left<=right) {
            int midL = left+(right-left)/2;
            if(nums[midL]<target) {
                left = midL+1;
            } else if(nums[midL]>target) {
                right = midL-1;
            } else {
                right = midL-1;
            }
        }
        //超过数组长度 比所有元素都大或者 比所有元素都小或者不存在于数组中
        if(left>=length||nums[left]!=target) {
            ans[0] = -1;
        } else {
            ans[0] = left;
        }
        // right boundary ✅ 同样可以不重置left 来获取right 边界
        // left = 0;
        right = length-1;
        while(left<=right){
            int midR = left+(right-left)/2;
            if(nums[midR]<target) {
                left = midR+1;
            } else if(nums[midR]>target) {
                right = midR-1;
            } else {
                left = midR+1;
            }
        }
        //比所有元素都小， 或者比所有元素都大或不在数组列表中right 可取0值
        if(right<0||nums[right]!=target){
            ans[1] = -1;
        } else {
            ans[1] = right;
        }
        return ans;
    }
}
