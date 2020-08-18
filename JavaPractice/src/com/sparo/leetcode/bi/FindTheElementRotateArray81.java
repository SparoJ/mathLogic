package com.sparo.leetcode.bi;

/**
 * description: 81. 搜索旋转排序数组 II
 * Created by sdh on 2020-07-04
 *
 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 *
 * ( 例如，数组 [0,0,1,2,2,5,6] 可能变为 [2,5,6,0,0,1,2] )。
 *
 * 编写一个函数来判断给定的目标值是否存在于数组中。若存在返回 true，否则返回 false。
 *
 * 示例 1:
 *
 * 输入: nums = [2,5,6,0,0,1,2], target = 0
 * 输出: true
 * 示例 2:
 *
 * 输入: nums = [2,5,6,0,0,1,2], target = 3
 * 输出: false
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/search-in-rotated-sorted-array-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class FindTheElementRotateArray81 {


    public boolean search(int[] nums, int target) {

        return biSearchPlain(nums, target);
    }

    // 按照 33 题的解题思路，延用在存在相同元素的旋转数组中查找最小值 的策略 能定位到最小值的位置，但形如11111121111的情形定位的位置并不在2之后的第一个位置，改造leetcode 154题获取存在重复元素的有序数组中的最小值来解决本问题（获取）
    private boolean biSearchPlain(int[] nums, int target) {
        //先在存在重复元素的数组中查找最小值的index
        if(nums == null || nums.length == 0) return false;
        int left = 0;
        int right = nums.length-1;
        if(nums[left]< nums[right]) {
            return halfSearch(nums, target, left, right);
        }
        int bound = getBoundMinIndex(nums, left, right);
        // System.out.println("bound->" + bound);
        if(bound<0) return false;
        //half search 边界处理！❌ nums[left]<=target
        if(nums[left]<target) { //左侧升序
            return halfSearch(nums, target, left, bound-1);
        } else if(nums[left]>target){
            return halfSearch(nums, target, bound, right);
        } else {
            return true;
        }
    }

    private boolean halfSearch(int[] nums, int target, int left, int right) {
        while(left<=right) {
            int mid = left + ((right-left)>>1);
            if(nums[mid]<target) {
                left = mid+1;
            } else if(nums[mid]>target) {
                right = mid-1;
            } else {
                return true;
            }
        }
        return false;
    }

    // 1111211
    private int getBoundMinIndex(int[] nums, int left,  int right) {
        while(left <= right) {
            if(nums[left]<nums[right] || left == right) {
                return left;
            }
            int mid = left + ((right-left)>>1);
            if(nums[left]<nums[mid]) {
                left = mid+1;
            } else if(nums[left]>nums[mid]) {
                right = mid;
            } else {
                left++;
            }
            if(left>0 && nums[left]<nums[left-1]) {
                return left;
            }
        }
        return -1;
    }

}
