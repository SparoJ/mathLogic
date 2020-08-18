package com.sparo.math.array;

import com.sparo.util.Utils;

/**
 * description:
 * Created by sdh on 2019-12-18
 */
public class LeetCode66 {

    public static void main(String[] args) {
        int[] arr = {4,3,3};
//        int[] arr = {4,3,9};
//        int[] arr = {9,9,9};
        LeetCode66 lc = new LeetCode66();
        int[] array = lc.plusOne(arr);
        Utils.printIntArray(array);
    }

    public int[] plusOne(int[] digits) {
        int length = digits.length;

        //代表进位 以及 初始的+1值，如果需要进位则+1
        int temp = 1;
        // 代表按照要求+1 以及 进位后的当前元素值
        int dig = 0;
        for(int i = length-1; i>=0; i--) {

            dig  = digits[i] + temp;
            // 是否进位判断
            if(dig>=10) {
                // 进位则 置 0
                digits[i] = 0;
                // 进位标记
                temp =1;
            } else {
                // 不进位则保持
                digits[i] = dig;
                // 重置 避免干扰
                temp = 0;
            }
        }
        // 9/99/999 等这种类型全量进位时处理
        if(temp == 1){
            // 元素重新初始化，首位为1，其他位取巧默认0值
            digits = new int[length+1];
            digits[0] = 1;
        }
        return digits;
    }
}
