package com.sparo.leetcode.bi;

import com.sparo.util.Utils;


/**
 * description: 二分搜索总结：以下为二分搜索模版代码，前后均为闭区间 注意统一性和差异性
 * 边界保护判定 和 返回direction==left?left:right;
 * Created by sdh on 2020-06-29
 */
public class BinarySearchTemplate {

    public static void main(String[] args) {
        int[] nums = {1,2,4,4,4,5,6};
       int res =  right_bound(nums, 4);
        Utils.printWithTag("right_bound->",  res);
        int resL =  left_bound(nums, 4);
        Utils.printWithTag("left_bound->",  resL);
    }



    /**
     * 常规二分搜索index
     * @param nums
     * @param target
     * @return
     */
    private int binarySearch(int[] nums, int target) {
        int left = nums[0]; int right = nums[nums.length-1];
        while(left <= right) {
            int mid = left + (right-left)/2;
            if(nums[mid] < target) {
                left = mid+1;
            } else if(nums[mid] > target) {
                right = mid-1;
            } else {
                return mid;
            }
        }
        return -1;
    }




    /*
    二分总结
    right 和 left
    right 值表示 比target 大的元素从该位置index开始（不包括index位置），数量为 length-index-1
    left 值表示 比target 小的元素从开头到该位置index结束（不包括index位置），数量为 index
    （以下结果 左为right边界index 右为 left边界index）
    int[] nums = {1,2,2,4,4,4,5,6};
    int res =  right_bound(nums, 3);/left_bound(nums, 3); 返回 -1/ -1

    int[] nums = {1,2,2,4,4,4,5,6,6};
    int res =  right_bound(nums, 6);/left_bound(nums, 6); 返回 8/7

    int[] nums = {1,1,2,4,4,4,5,6,6};
    int res =  right_bound(nums, 1); / left_bound(nums, 1); 返回 1/0

     int[] nums = {1,2,4,4,4,5,6};
       int res =  right_bound(nums, 4); / left_bound(nums, 1); 返回 4/2
    */

    /**
     * 二分搜索左边界
     * @param nums
     * @param target
     * @return
     */
    static int left_bound(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        // 搜索区间为 [left, right]
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                // 搜索区间变为 [mid+1, right]
                left = mid + 1;
            } else if (nums[mid] > target) {
                // 搜索区间变为 [left, mid-1]
                right = mid - 1;
            } else if (nums[mid] == target) {
                // 收缩右侧边界
                right = mid - 1;
            }
        }
        // 检查出界情况
        if (left >= nums.length || nums[left] != target)
            return -1;
        return left;
    }

    /**
     * 二分搜索右边届
     * @param nums
     * @param target
     * @return
     */
   static int right_bound(int[] nums, int target) {
        if (nums.length == 0) return -1;
        int left = 0, right = nums.length-1;

        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                left = mid + 1; // 注意
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid-1;
            }
        }
        //❌ 这里右边界right 可为0，左边界也可为0 「（1） target 为1 ，left = right = 0」
        if(right<0|| nums[right]!=target)
        return - 1; // 注意
       return right;
    }



}
