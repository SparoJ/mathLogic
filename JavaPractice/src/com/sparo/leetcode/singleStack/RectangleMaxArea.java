package com.sparo.leetcode.singleStack;

import java.util.ArrayDeque;

/**
 * description: 84. 柱状图中最大的矩形
 *
 * 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
 *
 * 求在该柱状图中，能够勾勒出来的矩形的最大面积。

 * 以上是柱状图的示例，其中每个柱子的宽度为 1，给定的高度为 [2,1,5,6,2,3]。
 *
 * 图中阴影部分为所能勾勒出的最大矩形面积，其面积为 10 个单位。
 *
 * 示例:
 *
 * 输入: [2,1,5,6,2,3]
 * 输出: 10
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/largest-rectangle-in-histogram
 * Created by sdh on 2020-07-15
 */
public class RectangleMaxArea {
    public int largestRectangleArea(int[] heights) {

        // return lraRecursion(heights, 0, heights.length-1);
        return lraSingleStack(heights);
    }


    //单调栈
    private int lraSingleStack(int[] heights) {
        int maxArea = 0;
        //左右各添加哨兵值用于左右端点比较值大小，全局思想为当前位置左右search 小值作为计算边界，然后步长*height 计为以current index为中心的最大面积值，即最大矩形面积转化为单调栈的应用 相比于递归 相当于从小扩散到大即类似从前到后，而递归是从大范围到小范围再递归回来得到比较结果
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        int[] newH = new int[heights.length+2];
        //需要copy的数组::从需要copy的数组中的指定index开始copy::copy到的目的地数组::copy到目的地数组的指定index::copy的长度值
        System.arraycopy(heights, 0, newH, 1, heights.length);
        for(int i = 0; i<newH.length; i++) {
            while(!stack.isEmpty()&& newH[i]< newH[stack.peek()]) {
                int curHeight = newH[stack.pop()];
                maxArea = Math.max(maxArea, curHeight*(i-stack.peek()-1));
            }
            stack.push(i);
        }
        return maxArea;
    }

    private int lraRecursion(int[] heights, int left, int right) {
        // if(left==right) return left;
        //递归终止条件 ❌ 方法体最后调用函数的 left/boundary-1 ：：boundary+1/right left可能比right大 此时应该终止递归并返回 返回值为不影响比较大小的值即可
        if(left>right) return 0;
        //找到当前区域的min index  作为下一次递归的分界
        int boundary = left;
        for(int i = left+1; i<=right; i++) {
            if(heights[boundary]> heights[i]) {
                boundary = i;
            }
        }
        return max((right-left+1)*heights[boundary], lraRecursion(heights, left, boundary-1), lraRecursion(heights, boundary+1, right));
    }

    private int max(int a, int b, int c) {
        return Math.max(a, Math.max(b, c));
    }
}
