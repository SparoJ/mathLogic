package com.sparo.leetcode.bi;

/**
 * description: 162. 寻找峰值
 * 峰值元素是指其值大于左右相邻值的元素。
 *
 * 给定一个输入数组 nums，其中 nums[i] ≠ nums[i+1]，找到峰值元素并返回其索引。
 *
 * 数组可能包含多个峰值，在这种情况下，返回任何一个峰值所在位置即可。
 *
 * 你可以假设 nums[-1] = nums[n] = -∞。
 *
 * 示例 1:
 *
 * 输入: nums = [1,2,3,1]
 * 输出: 2
 * 解释: 3 是峰值元素，你的函数应该返回其索引 2。
 * 示例 2:
 *
 * 输入: nums = [1,2,1,3,5,6,4]
 * 输出: 1 或 5
 * 解释: 你的函数可以返回索引 1，其峰值元素为 2；
 *      或者返回索引 5， 其峰值元素为 6。
 * 说明:
 *
 * 你的解法应该是 O(logN) 时间复杂度的。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-peak-element
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * Created by sdh on 2020-07-04
 */
public class FindPeek162 {

    public int findPeakElement(int[] nums) {

        //遍历o(n) 非o(lgn)
        // return fpeOnRight(nums);
        //二分法 基于对常规遍历分解问题的理解，而常规遍历分解问题是基于枚举各种不同场景后得到的核心判定条件
        return fpeBiSearch(nums);
    }


    /*
     * 根据 当前o(n)解法得到判定index位置是否为峰值因素 只需要关注 i和 i+1 的大小关系
     * 判定即可 常规遍历的方式是直观的从前到后判定i和i+1的关系
     * 在需要将复杂度从o(n) 降到o(lgn)时，获取mid位置比较 mid和 next位置的元素大小关系再做二分即可
     */
    private int fpeBiSearch(int[] nums) {
        // 根据题示意： nums[-1] = nums[n] = -∞ count 取1时 该值为peek
        if(nums == null || nums.length==0) return -1;
        // 二分递归
        //    return fpeBiRecursion(nums, 0, nums.length-1);

        //二分遍历
        return fpeBiTraversal(nums);
    }

    //递归
    private int fpeBiRecursion(int[] nums, int l, int r) {
        //base case
        if(l==r) return l;
        int mid = l + ((r-l)>>1);
        //下降区域 缩小区间为 【l-mid】
        if(nums[mid]>nums[mid+1]) {
            return fpeBiRecursion(nums, l, mid);
        }
        //上升区域 区间不包括mid 因为mid+1>mid的值
        return fpeBiRecursion(nums, mid+1, r);
    }

    //遍历
    private int fpeBiTraversal(int[] nums) {
        int l = 0; int r = nums.length-1;
        while(l<r) {
            int mid = l+((r-l)>>1);
            if(nums[mid]>nums[mid+1]) {
                r = mid;
            } else {
                l = mid+1;
            }
        }
        return l;
    }

    /**
     * 以下为 o(n)方案，思路根据 数组变化规律无非为 递增 递减 或者波浪
     * 不管是递增 递减还是 波浪 考虑只比较 当前元素和相邻其中一个即可确定当前位置是否为peek
     * 如果 比左侧的大 则为peek 右侧不需要比 反之亦然 but 思考后发现递增时比较规则无效
     * 只能使用比较右侧元素来过滤
     */

    // 判断peek 和 left 的关系 ❌
    private int fpeOnLeft(int[] nums) {
        for(int i =1; i<nums.length; i++) {
            if(nums[i]>nums[i-1])return i;
        }
        return 0;
    }
    // 判断peek 和 right 的关系
    private int fpeOnRight(int[] nums) {
        for(int i=0; i<nums.length-1; i++) {
            if(nums[i]>nums[i+1]) return i;
        }
        return nums.length-1;
    }
}
