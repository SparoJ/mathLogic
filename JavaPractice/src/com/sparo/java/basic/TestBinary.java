package com.sparo.java.basic;

import com.sparo.util.Utils;

/**
 * description:
 * Created by sdh on 2020-06-15
 */
public class TestBinary {

    public static void main(String[] args) {
        int[] nums = {1,2,2,2,2,2,2};
        int index =left_bound(nums,2);
        Utils.printWithTag("index", index);
    }

    private static int left_bound(int[] nums, int target) {
        if (nums.length == 0) return -1;
        int left = 0;
        int right = nums.length; // 注意

        while (left < right) { // 注意
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                right = mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid; // 注意
            }
        }
        return left;
    }

}
