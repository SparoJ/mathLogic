package com.sparo.math.array;

import com.sparo.util.Utils;

/**
 * description:
 * 给定一个数组 nums 和一个值 val，你需要原地移除所有数值等于 val 的元素，返回移除后数组的新长度。
 *
 * 不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。
 *
 * 元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。
 *
 * 示例 1:
 *
 * 给定 nums = [3,2,2,3], val = 3,
 *
 * 函数应该返回新的长度 2, 并且 nums 中的前两个元素均为 2。
 *
 * 你不需要考虑数组中超出新长度后面的元素。
 * 示例 2:
 *
 * 给定 nums = [0,1,2,2,3,0,4,2], val = 2,
 *
 * 函数应该返回新的长度 5, 并且 nums 中的前五个元素为 0, 1, 3, 0, 4。
 *
 * 注意这五个元素可为任意顺序。
 *
 * 你不需要考虑数组中超出新长度后面的元素。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/remove-element
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * Created by sdh on 2019-12-18
 */
public class LeetCode27 {

    public static void main(String[] args) {
        LeetCode27 lc = new LeetCode27();
//        int[] arr = {3,2,2,3,3,3}; // arr1
        int[] arr = {3,2,2,2,2,3}; // arr2
        int count = lc.removeElementMehtod1(arr, 3);
        System.out.println("count->>" + count);
    }

    /**
     * 从前向后遍历，快慢双指针，
     * 当元素与 当前遍历的元素相等时，继续下一次遍历，直到不等于给定值时，将慢指针值覆盖为当前快指针的值
     * @param nums
     * @param val
     * @return
     */
    int count1 = 0;
    public int removeElementMehtod1(int[] nums, int val) {
        int i = 0;
        int length = nums.length;
        for (int j = 0; j < length; j++) {
            //适用于相等情况多，即 要移除的元素多时效率高
            if(nums[j] != val){
                count1++;
                nums[i] = nums[j];
                i++;
            }
        }
        // 2(arr1) / 4(arr2) 适用于相等情况多，即 要移除的元素多时效率高
        System.out.print(count1);
        Utils.printIntArray(nums);
        // 这里i即是项数
        return i;
    }

    /**
     * 左右双指针
     * 本方法从前开始while循环（而非 遍历 for），
     * 如果匹配值相等则通过 数组的最后一个值覆盖当前值再循环判断（while 非for）
     * 然后数组长度变量 --， 继续while 直到有值不等（这个被判定的值是位于数组首部的值）
     * 不等则左侧角标自加 直到数组长度结束
     *
     * 比method1 优化的点在于，将后面的元素拿来覆盖前面的元素，即可理解为将method1中的
     * swap 交换转换成了 覆盖，而n同时在自减 效率更高
     * @param nums
     * @param val
     * @return
     */
    int count2 = 0;
    public int removeElement(int[] nums, int val) {
        int i = 0;
        int n = nums.length;
        while (i < n) {
            //适用于相等情况少，即 要移除的元素少时效率高
            if (nums[i] == val) {
                count2++;
                nums[i] = nums[n - 1];
                // reduce array size by one
                n--;
            } else {
                i++;
            }
        }
        // 4 (arr1) / 2(arr2) 适用于相等情况少，即 要移除的元素少时效率高
        System.out.print(count2);
        Utils.printIntArray(nums);
        // 返回最终的数组长度 -> n
        return n;
    }

}
