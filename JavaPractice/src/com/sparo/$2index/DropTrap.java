package com.sparo.$2index;

/**
 * description:
 * https://leetcode-cn.com/problems/trapping-rain-water/
 * 42. 接雨水
 * Created by sdh on 2020-07-02
 */

/*给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。

上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。 感谢 Marcos 贡献此图。

示例:

输入: [0,1,0,2,1,0,1,3,2,1,2,1]
输出: 6

链接：https://leetcode-cn.com/problems/trapping-rain-water*/

public class DropTrap {
    /**
     * //解题核心判定
     * ans+=Math.min(lm, rm) - height[i];
     */
    public int trap(int[] height) {
        //暴力法 复杂度o(n2) 空间复杂度o(1)
        // return bruteTrap(height);
        //空间换时间 memo数组 复杂度o(n) 空间复杂度o(n)
        // return trapDropMemo(height);
        //双指针 复杂度o(n) 空间o(1)
        return trapDropDoubleIndex(height);
    }

    //双指针优化 在遍历的同时得到对应index左侧和右侧的max 值
    private int trapDropDoubleIndex(int[] height) {
        //base case safe proguard
        if(height == null || height.length<=1) return 0;
        //当前index所承载的水量依赖于边界最小值
        int left = 0; int right = height.length-1;
        int lm = height[0];
        int rm = height[right];
        int ans = 0;
        while(left<right) {
            //当前left 左侧 max 值
            lm = Math.max(lm, height[left]);
            //当前right 位置 右侧 max值
            rm = Math.max(rm, height[right]);
            //以下有效是根据 min(leftMax, rightMax)而来！❌✅
            // 如果lm<rm 则说明 lm相对当前的left位置有效
            if(lm<rm) {
                ans+= lm-height[left];
                left++;
                //如果lm>=rm 说明 rm 相对当前right 位置有效
            } else {
                ans+= rm-height[right];
                right--;
            }
        }
        return ans;
    }

    // 使用数组存储对应index位置的lm 和 rm，再来计算 避免可以优化的for嵌套部分
    private int trapDropMemo(int[] height) {
        if(height==null || height.length<=1) return 0;
        int ans = 0;
        int len = height.length;
        int[] lma = new int[len];
        int[] rma = new int[len];
        //根据下面预前赋值遗漏错误点添加base case 代码：
        lma[0] = height[0];
        rma[len-1] = height[len-1];
        //❌ 遗漏赋值边界元素 最左侧 i = 0 又因为i-1不能超界 所以需要添加base case
        for(int i = 1; i<len; i++) {
            //当前高度和 左侧之前最大高度比
            lma[i] = Math.max(height[i], lma[i-1]);
        }
        //❌ 遗漏赋值边界元素 最右侧 i = len-2,又因为i+1不能超界 所以需要添加base case
        for(int i = len-2; i>=0; i--) {
            //当前高度和 右侧之前最大高度比
            rma[i] = Math.max(height[i], rma[i+1]);
        }
        for(int i =1; i<len; i++) {
            ans+=Math.min(lma[i], rma[i]) - height[i];
        }
        return ans;
    }

    //暴力法 复杂度o(n2) 纯暴力从前到后每次获取当前位置的左右最大元素
    private int bruteTrap(int[] height) {
        if(height == null || height.length<=1) return 0;
        int ans = 0;
        int len = height.length;
        //❌ 遍历过程中需要重置当前index的 lm和rm，旧有的lm 和 rm不一定在新遍历到的index位置继续生效
        // int lm = 0; // height[0];
        // int rm = 0; // height[len-1];
        for(int i = 1; i<len-1; i++) {
            int lm = height[0];
            int rm = height[len-1];
            //计算当前i位置的左侧max
            for(int j = i; j>=0; j--) {
                lm = Math.max(lm, height[j]);
            }

            //计算当前i位置的右侧max
            for(int k = i; k<len; k++) {
                rm = Math.max(rm, height[k]);
            }
            //解题核心判定
            ans+=Math.min(lm, rm) - height[i];
        }
        return ans;
    }
}
