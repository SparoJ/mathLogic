package com.sparo.leetcode.bi;

/**
 * description: 33. 搜索旋转排序数组
 * Created by sdh on 2020-07-04
 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 *
 * ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
 *
 * 搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。
 *
 * 你可以假设数组中不存在重复的元素。
 *
 * 你的算法时间复杂度必须是 O(log n) 级别。
 *
 * 示例 1:
 *
 * 输入: nums = [4,5,6,7,0,1,2], target = 0
 * 输出: 4
 * 示例 2:
 *
 * 输入: nums = [4,5,6,7,0,1,2], target = 3
 * 输出: -1
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/search-in-rotated-sorted-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class FindTheElementRotateArray33 {

    public int search(int[] nums, int target) {

        // 常规思路，先找到临界点，然后分段 左右有序区间做二分
        //    return biSearchNormal(nums, target);
        //   return biSearchSmart(nums, target);
        // biSearchSmart 简版
        return biSearchSmartP(nums, target);
    }


    private int biSearchSmartP(int[] nums, int target) {
        if(nums == null || nums.length==0) return -1;
        int left = 0;
        int right = nums.length-1;
        while(left<=right) {
            // 判定当前mid位置是否位于有序子序列中
            int mid = left + ((right-left)>>1);
            int num = (nums[left]<=target)== (nums[left]<=nums[mid]) ?nums[mid]:(nums[left]>nums[mid])?Integer.MAX_VALUE:Integer.MIN_VALUE;

            if(num<target) {
                left = mid+1;
            } else if(num>target) {
                right = mid-1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    // 处理方式是对边界模糊判定，取mid位置元素后 判定左右升序数组位置关系改变非有序数组关系多次收缩边界直至匹配或者不存在
    private int biSearchSmart(int[] nums, int target) {
        //base case
        if(nums == null || nums.length==0) {
            return -1;
        }
        //initial variable(n)
        int left = 0;
        int right = nums.length-1;
        while(left<=right) {
            int mid = left + ((right-left)>>1);
            //先判定target 在左还是右侧升序子数组中，再判定mid位置的元素 和 left 的关系，是左/右 此时非升序关系改为升序关系，如何改变 将非target位置的mid位置值改变为 max or min
            //判定元素在左侧升序还是右侧升序区间

            if(nums[left]<target) {   //左侧升序
                if(nums[mid]<nums[left]) {
                    nums[mid] = Integer.MAX_VALUE;
                }
            } else if(nums[left]>target) { //右侧升序
                if(nums[mid]>nums[left]) {
                    nums[mid] = Integer.MIN_VALUE;
                }
            } else {
                return left;
            }
            //更新left/right
            if(nums[mid]<target) {
                right = mid-1;
            } else if(nums[mid]>target) {
                left = mid+1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    // 找到左右临界点 再对临界点两边做二分search
    private int biSearchNormal(int[] nums, int target) {
        if(nums == null || nums.length==0) return -1;
        int left = 0;
        int right = nums.length-1;
        //❌ 数组本身就满足升序则 最小元素为0位置时，需特殊处理，后续bound只支持区间内而非头部（bound index的位置在中间或者尾部均可行）
        if(nums[left]<nums[right]) {
            return halfSearch(nums, target, left, right);
        }
        //bound 指最小元素所在index，可能会在数组非头位置，也可能在此时bound-1无效，应该直接前置使用当前数组本身即为升序直接求halfSearch 返回
        int bound = getBoundMin(nums, target, left, right);
        // left = 0; 值传递 这里不再需要care 值被方法改变
        //在左边升序中找
        // System.out.println("bound->" + bound);
        if(nums[left]<target) {
            return halfSearch(nums, target, 0, bound-1);
        } else if(nums[left]> target){
            //在右侧升序
            return halfSearch(nums, target, bound, right);
        } else {
            //值=target 直接返回
            return left;
        }
    }
    private int getBoundMin(int[] nums,int target, int left, int right) {
        while(left<=right) {
            if(nums[left]<=nums[right]) {
                return left;
            }
            int mid = left + (right-left)/2;
            if(nums[left]<=nums[mid]) {
                //说明left-mid有序，临界位置不在此区间，这里的临界位置选择在最小值处
                left = mid+1;
            } else {
                //left - mid 区域 非单调区 即临界值在该范围
                right = mid;
            }
        }
        return 0;
    }

    private int halfSearch(int[] nums, int target, int l, int r) {
        while(l<=r) {
            //❌ 在 运算符优先级 基础问题！！！！❌ 位移运算要打括号 否则都是错的
            int mid = l +((r-l)>>1);
            // System.out.println("mid->" + mid + "->l->" + l + "->r->" + r);
            if(nums[mid]<target) {
                l = mid+1;
            } else if(nums[mid]>target) {
                r = mid-1;
            } else {
                return mid;
            }
        }
        return -1;
    }
}
