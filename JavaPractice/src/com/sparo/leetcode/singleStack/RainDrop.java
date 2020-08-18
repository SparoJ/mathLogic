package com.sparo.leetcode.singleStack;

import java.util.ArrayDeque;

/**
 * description:
 * Created by sdh on 2020-07-15
 */
public class RainDrop {


    public int trap(int[] height) {


        if(height == null || height.length<=2) return 0;
        int ans = 0;
        int n = height.length;
        // ---------- brute ------------
        // for(int i =1; i<n-1; i++) {
        //     int lb = height[i];
        //     for(int j = i; j>=0; j--) {
        //         if(lb<height[j]) lb = height[j];
        //     }
        //     int rb = height[i];
        //     for(int j = i; j<n; j++) {
        //         if(rb<height[j]) rb = height[j];
        //     }
        //     ans+= Math.min(lb, rb)-height[i];
        // }

        //--------- memo ---------
        // int[] la = new int[n];
        // int lb = height[0];
        // for(int i = 1; i<n-1; i++) {
        //     if(lb<height[i]) {
        //         lb = height[i];
        //     }
        //     la[i] = lb;
        // }
        // int[] ra = new int[n];
        // int rb = height[n-1];
        // for(int i=n-2; i>0; i--) {
        //     if(rb<height[i]) {
        //         rb = height[i];
        //     }
        //     ra[i] = rb;
        // }
        // //一次遍历求ans
        // for(int i = 1; i<n-1; i++) {
        //     ans+=Math.min(la[i], ra[i])-height[i];
        // }
        // return ans;

        // ---- -----double index ---- -----
        // int l = 1; int r = n-2;
        // int lb = height[0];
        // int rb = height[n-1];
        // while(l<=r) {
        //     if(lb<height[l]) lb = height[l];
        //     if(rb<height[r]) rb = height[r];
        //     if(lb<rb) {
        //         ans+=lb-height[l];
        //         l++;
        //     } else {
        //         ans+=rb-height[r];
        //         r--;
        //     }
        // }


        // -------------- single stack --------------
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        int[] newH = new int[n+2];
        System.arraycopy(height, 0, newH, 1, n);
        for(int i = 1; i<newH.length-1; i++) {
            //这里>= 与 矩形面积 判定带= 不影响结果 原因？ min一侧>=/一侧= pop的高度为相等的值 即 min-height =0; 不影响结果 但多一次运算
            while(!stack.isEmpty() && newH[i]>newH[stack.peek()]) {
                int cur = stack.pop();
                if(stack.isEmpty()) break;
                ans+=(i-stack.peek()-1)*(Math.min(newH[i], newH[stack.peek()])-newH[cur]);
            }
            stack.push(i);
        }

        return ans;
    }

//    public int trap(int[] height) {
        //暴力/ 提前记录 /双指针 / 单调栈 4种方案尝试
        //o(n2/1)
        // return trapDropBrute(height);
        //o(n/n)
        // return trapDropMemo(height);
        //o(n/1)
        // return trapDropDoubleIndex(height);
        //o(n/n)
//        return trapDropSingleStack(height);
//    }


    // single stack 单调栈如何解决？
    /*
     * 当前位置的左右最近大值边界包围的区域求面积，和 最大矩形面积类似，保证的是当前元素左右两边比自己大的栈结构，
     */
    private int trapDropSingleStack(int[] nums) {
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        // int[] newN = new int[nums.length+2];
        // System.arraycopy(nums, 0, newN, 1, nums.length);
        int ans = 0;
        for(int i = 0; i < nums.length; i++) {

            while(!stack.isEmpty()&& nums[i] > nums[stack.peek()]) {
                int cur = nums[stack.pop()];
                if(stack.isEmpty()) break;
                int width = i-stack.peek()-1;
                ans+= (Math.min(nums[i], nums[stack.peek()])-cur)*width;
            }
            stack.push(i);
        }
        return ans;
    }


    /*
     * 解决问题的核心在于，当前index位置的左右两侧最近的max值中取min再minus 当前高度值
     * ans+=(1-n)[min(lm,rm)-height]*1
     */

    private int trapDropBrute(int[] height) {
        if(height == null || height.length<=1) return 0;
        //遍历直接求当前位置的左右近处的最大值
        //❌ ❌ ❌ 问题在于 lm/rm在当前index的理解不准确，lm/rm并非就近最大值，为左侧所有数中的最大值 所以初始给定边界值没问题
        //结果累加初始值
        int ans = 0;
        // 边界柱体只能成为盛水的边界，不参与当前位置的盛水计量故从1到last-1
        for(int i = 1; i < height.length-1; i++) {
            //❌ lm/rm需要跟随遍历过程改变，应放在循环体中更新❌
            // int lm = height[0]; 0/边界值均可 因为for里包括了边界值在内的大小值判定 可以取到边界index
            int lm = 0;
            // int rm = height[height.length-1];
            int rm = 0;
            //求 当前index 左右最近的最大值
            for(int j = i; j>=0; j--) {
                if(lm<height[j]) {
                    lm = height[j];
                }
            }
            //这里k/j可以为左右0/last index 位置
            for(int k=i; k<height.length; k++) {
                if(rm<height[k]) {
                    rm = height[k];
                }
            }
            ans+=Math.min(lm,rm)-height[i];
        }
        return ans;
    }

    //memo 暴力法 将lm/rm 先用数据结构缓存 最后遍历时直接取出使用
    private int trapDropMemo(int[] height) {
        // base case
        if(height == null || height.length<=1) return 0;
        int len = height.length;
        int ans = 0;
        int[] lma = new int[len];
        int[] rma = new int[len];
        int lm = 0; int rm = 0;
        for(int i = 0; i<len; i++) {
            lma[i] = Math.max(lm, height[i]);
            //❌ 在赋值问题 应该将当前的大值赋给变量，之后每次都是之前比较的结果和下一次需要比较的数据进行比较判定
            // lm = height[i];
            lm = lma[i];
        }
        for(int j = len-1; j>=0; j--) {
            rma[j] = Math.max(rm, height[j]);
            //❌ 在赋值问题
            // rm = height[j];
            rm = rma[j];
        }
        //边界不会构造凹形区域盛水 可去掉计算
        for(int k = 1; k<len-1; k++) {
            // System.out.println("lma" + lma[k] + "-rma-" + rma[k] +"-hk-"+height[k]);
            ans+= Math.min(lma[k], rma[k])-height[k];
            // System.out.println("ans->" + ans);
        }
        return ans;
    }

    //double index
    private int trapDropDoubleIndex(int[] height) {
        if(height == null || height.length<=1) return 0;
        //双指针压缩比较，即双指针在遍历的同时获取当前index的左右最值
        int left = 0; int right = height.length-1;
        int ans = 0;
        //思考 这里是否需要边界，在边界 相等时也需要处理当前位置的盛水可能性
        int lm = height[0];
        int rm = height[height.length-1];
        while(left<=right) {
            //❌ 在未比较直接取当前值作为最值，依次遍历当前比较过的数据并同步更新
            // int lm = height[left];
            // int rm = height[right];
            lm = Math.max(lm, height[left]);
            rm = Math.max(rm, height[right]);
            if(lm<rm) {
                ans+=lm-height[left];
                left++;
            } else {
                ans+=rm-height[right];
                right--;
            }
        }
        return ans;
    }



    /**
     * //解题核心判定
     * ans+=Math.min(lm, rm) - height[i];
     */
//    public int trap(int[] height) {
//        //暴力法 复杂度o(n2) 空间复杂度o(1)
//        // return bruteTrap(height);
//        //空间换时间 memo数组 复杂度o(n) 空间复杂度o(n)
//        // return trapDropMemo(height);
//        //双指针 复杂度o(n) 空间o(1)
//        return trapDropDoubleIndex(height);
//    }
//
//    //双指针优化 在遍历的同时得到对应index左侧和右侧的max 值
//    private int trapDropDoubleIndex(int[] height) {
//        //base case safe proguard
//        if(height == null || height.length<=1) return 0;
//        //当前index所承载的水量依赖于边界最小值
//        int left = 0; int right = height.length-1;
//        int lm = height[0];
//        int rm = height[right];
//        int ans = 0;
//        while(left<right) {
//            //当前left 左侧 max 值
//            lm = Math.max(lm, height[left]);
//            //当前right 位置 右侧 max值
//            rm = Math.max(rm, height[right]);
//            //以下有效是根据 min(leftMax, rightMax)而来！❌✅
//            // 如果lm<rm 则说明 lm相对当前的left位置有效
//            if(lm<rm) {
//                ans+= lm-height[left];
//                left++;
//                //如果lm>=rm 说明 rm 相对当前right 位置有效
//            } else {
//                ans+= rm-height[right];
//                right--;
//            }
//        }
//        return ans;
//    }
//
//    // 使用数组存储对应index位置的lm 和 rm，再来计算 避免可以优化的for嵌套部分
//    private int trapDropMemo(int[] height) {
//        if(height==null || height.length<=1) return 0;
//        int ans = 0;
//        int len = height.length;
//        int[] lma = new int[len];
//        int[] rma = new int[len];
//        //根据下面预前赋值遗漏错误点添加base case 代码：
//        lma[0] = height[0];
//        rma[len-1] = height[len-1];
//        //❌ 遗漏赋值边界元素 最左侧 i = 0 又因为i-1不能超界 所以需要添加base case
//        for(int i = 1; i<len; i++) {
//            //当前高度和 左侧之前最大高度比
//            lma[i] = Math.max(height[i], lma[i-1]);
//        }
//        //❌ 遗漏赋值边界元素 最右侧 i = len-2,又因为i+1不能超界 所以需要添加base case
//        for(int i = len-2; i>=0; i--) {
//            //当前高度和 右侧之前最大高度比
//            rma[i] = Math.max(height[i], rma[i+1]);
//        }
//        for(int i =1; i<len; i++) {
//            ans+=Math.min(lma[i], rma[i]) - height[i];
//        }
//        return ans;
//    }
//
//    //暴力法 复杂度o(n2) 纯暴力从前到后每次获取当前位置的左右最大元素
//    private int bruteTrap(int[] height) {
//        if(height == null || height.length<=1) return 0;
//        int ans = 0;
//        int len = height.length;
//        //❌ 遍历过程中需要重置当前index的 lm和rm，旧有的lm 和 rm不一定在新遍历到的index位置继续生效
//        // int lm = 0; // height[0];
//        // int rm = 0; // height[len-1];
//        for(int i = 1; i<len-1; i++) {
//            int lm = height[0];
//            int rm = height[len-1];
//            //计算当前i位置的左侧max
//            for(int j = i; j>=0; j--) {
//                lm = Math.max(lm, height[j]);
//            }
//
//            //计算当前i位置的右侧max
//            for(int k = i; k<len; k++) {
//                rm = Math.max(rm, height[k]);
//            }
//            //解题核心判定
//            ans+=Math.min(lm, rm) - height[i];
//        }
//        return ans;
//    }


    //复习1 20200715
//    public int trap(int[] height) {
//        //暴力/memo/double index/single stack 四种方案
//        // return trapDropStack(height);
//
//        // return trapDropBrute(height);
//        // return trapDropMemo(height);
//        return trapDropDoubleIndex(height);
//    }
//
//    //双指针 优化空间
//    private int trapDropDoubleIndex(int[] height) {
//        if(height==null||height.length<=2) return 0;
//        int n = height.length-1;
//        int l = 1; int r = n-1;
//        int lb= height[0]; int rb = height[n];
//        int ans = 0;
//        //❌ 边界条件 🤔 为什么需要保证l<=r 中的=号！元素丢失
//        while(l<=r) {
//            if(lb<height[l]) {
//                lb = height[l];
//            }
//            if(rb<height[r]) {
//                rb = height[r];
//            }
//            if(lb<rb) {
//                ans+=lb-height[l];
//                //❌ l++/r--的位置 在完成当前位置遍历后再进行更新
//                l++;
//            }else {
//                ans+=rb-height[r];
//                r--;
//            }
//        }
//        return ans;
//    }
//
//    //备忘录前置记录数据 优化时间
//    private int trapDropMemo(int[] height) {
//        if(height==null || height.length<=2) return 0;
//        int n = height.length;
//        //提前记录好前index位置的大值到数组中，解除循环嵌套带来的复杂度增加问题
//        int[] l = new int[n];
//        //寻找 从 1到n-2 对应位置之前的所有位置的较大元素
//        int lb = height[0];
//        for(int i=1; i<n-1; i++) {
//            if(lb<height[i]) {
//                lb = height[i];
//            }
//            l[i] = lb;
//        }
//        int[] r = new int[n];
//        int rb = height[n-1];
//        //寻找从 n-2 到1 位置之间的所有位置的较大元素
//        for(int i=n-2; i>0; i--) {
//            if(rb<height[i]) {
//                rb = height[i];
//            }
//            r[i] = rb;
//        }
//        int ans = 0;
//        for(int i = 1; i<n-1; i++) {
//            ans+=Math.min(l[i],r[i])-height[i];
//        }
//        return ans;
//    }
//
//    //暴力解法
//    private int trapDropBrute(int[] height) {
//        if(height == null || height.length<=2) return 0;
//        int ans = 0;
//        //因为0/length-1为边界无法盛水 故不需要纳入计算范围直接缩小范围
//        for(int i = 1; i<height.length-1; i++) {
//            int lb = height[i]; int rb = height[i];
//            //search before i
//            for(int j = i; j>=0; j--) {
//                if(lb<height[j]) lb = height[j];
//            }
//            //search after i
//            for(int j = i; j<=height.length-1; j++) {
//                if(rb<height[j]) rb = height[j];
//            }
//            ans += Math.min(lb, rb)- height[i];
//        }
//        return ans;
//    }
//
//    /**
//     * 思路可沿用 矩形最大面积，这里类似 添加哨兵边界方便判断
//     * 以当前index search左右大值 得到约束范围内的雨水单位，步长*(Math.min(a,b)-currentHeight) 累加即为所能接的雨水单位值
//     */
//    private int trapDropStack(int[] heights) {
//        if(heights == null || heights.length<=2) return 0;
//        int ans = 0;
//        int[] newH = new int[heights.length+2];
//        System.arraycopy(heights, 0, newH, 1, heights.length);
//        ArrayDeque<Integer> stack = new ArrayDeque<>();
//        for(int i = 0; i<newH.length; i++) {
//            while(!stack.isEmpty() && newH[i]>newH[stack.peek()]) {
//                int curIndex = stack.pop();
//                //❌ 同理于柱状矩形最大面积 while中pop未push则存在NPE问题
//                if(stack.isEmpty()) break;
//                int index = stack.peek();
//                ans+=(i-index-1)*(Math.min(newH[i], newH[index])-newH[curIndex]);
//            }
//            stack.push(i);
//        }
//        return ans;
//    }
}
