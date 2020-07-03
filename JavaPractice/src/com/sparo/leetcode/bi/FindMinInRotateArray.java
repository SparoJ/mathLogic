package com.sparo.leetcode.bi;

/**
 * description: 153. 寻找旋转排序数组中的最小值
 * Created by sdh on 2020-07-02
 */

/*假设按照升序排序的数组在预先未知的某个点上进行了旋转。

( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。

请找出其中最小的元素。

你可以假设数组中不存在重复元素。

示例 1:

输入: [3,4,5,1,2]
输出: 1
示例 2:

输入: [4,5,6,7,0,1,2]
输出: 0

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。*/
public class FindMinInRotateArray {

    public int findMin(int[] nums) {

        // return fm(nums);
        //153I -> 154II 旋转数组问题解决方案
        // https://imageslr.github.io/2020/03/06/leetcode-33.html
        return fmP(nums);
    }

    private int fmP(int[] nums) {
        if(nums == null || nums.length == 0) return -1;
        int left = 0;
        int right = nums.length-1;
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




    private int fm(int[] nums) {
        if(nums==null||nums.length==0) return -1;
        int left = 0; int right = nums.length-1;
        //边界保护 [0] [1,2][2,1] 前者会在下面代码中 指针越界，而对于后面的两种情况存在需要单独处理 mid位置和 0位置相等的特殊情况
        if(nums[left]<=nums[right])return nums[left];
        //以下代码只能用于>=三个元素的查找
        while(left<=right) {
            int mid = left+(right-left)/2;
            if(nums[mid]<nums[0]) {
                right = mid-1;
            } else {
                left = mid+1;
            }
        }
        return nums[left];
    }

}
