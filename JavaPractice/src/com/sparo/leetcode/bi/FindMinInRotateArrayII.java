package com.sparo.leetcode.bi;

/**
 * description: 154. 寻找旋转排序数组中的最小值 II
 * Created by sdh on 2020-07-03
 */

/*假设按照升序排序的数组在预先未知的某个点上进行了旋转。

        ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。

        请找出其中最小的元素。

        注意数组中可能存在重复的元素。

        示例 1：

        输入: [1,3,5]
        输出: 1
        示例 2：

        输入: [2,2,2,0,1]
        输出: 0
        说明：

        这道题是 寻找旋转排序数组中的最小值 的延伸题目。

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array-ii
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。*/

public class FindMinInRotateArrayII {

    public int findMin(int[] nums) {
        //153 方案回顾 🤔为何不能在重复元素下使用
        // return fm(nums);
        //改造==情况和边界处理
        return fmP(nums);
    }

    //改造153 的方案: 对 <= 的情况做改造
    private int fmP(int[] nums) {
        if(nums==null || nums.length == 0) return -1;
        int left = 0;
        int right = nums.length-1;
        while(left <= right) {
            int mid = left + ((right-left)>>1);
            if(nums[left]<nums[right] || left == right) {
                return nums[left];
            }
            if(nums[left]<nums[mid]) {
                left = mid+1;
            } else if(nums[left]>nums[mid]) {
                right = mid;
            } else {
                left++;
            }
        }
        return -1;
    }

    // 153的方案对于 重复元素在同侧的查找而言是可行的，但如果重复元素左侧和右侧升序序列均有分布例：3.1.3 则
    private int fm(int[] nums) {
        if(nums==null || nums.length ==0) return -1;
        int left = 0; int right = nums.length-1;
        while(left<=right) {
            int mid = left + ((right-left)>>1);
            if(nums[left]<=nums[right]) {
                return nums[left];
            }
            if(nums[left]<=nums[mid]) {
                left = mid+1;
            } else {
                right = mid;
            }
        }
        return -1;
    }
}
