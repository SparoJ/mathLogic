package com.sparo.leetcode.dp;

import java.util.Stack;

/**
 * 给定一个只包含 '(' 和 ')' 的字符串，找出最长的包含有效括号的子串的长度。
 *
 * 示例 1:
 *
 * 输入: "(()"
 * 输出: 2
 * 解释: 最长有效括号子串为 "()"
 * 示例 2:
 *
 * 输入: ")()())"
 * 输出: 4
 * 解释: 最长有效括号子串为 "()()"
 *
 * 链接：https://leetcode-cn.com/problems/longest-valid-parentheses
 * Created by sdh on 2020-06-28
 */

public class LongestValidParentheses {

    public int longestValidParentheses(String s) {
        //复杂度 o(n) 空间o(n)
        // return lvpDp(s);
        //复杂度 o(n) 空间o(n)
        // return lvpStack(s);
        //复杂度 o(n*2) 空间o(1)
        return lvpDoubleIndex(s);
    }

    /**
     * 动态规划版本
     * @param s
     * @return lvp
     */
    private int lvpDp(String s) {
        //base case
        if(s == null || s.length()<2) return 0;
        int len = s.length();
        //状态数组，dp[i]标记包括当前i位置的括号时最长且有效的括号长度 (() 2
        // (())(() 0024002
        int[] dp = new int[len];
        int max = 0;
        for(int i =1; i < len; i++) {
            if(s.charAt(i)== ')') {
                if(s.charAt(i-1)=='(') {
                    dp[i] = (i-2>=0?dp[i-2]:0)+2;
                    //❌ 边界问题处理
                } else if(i-dp[i-1]>0 && s.charAt(i-dp[i-1]-1)=='(') {
                    dp[i] = dp[i-1]+(i-dp[i-1]-2>=0?dp[i-dp[i-1]-2]:0)+2;
                }
                max = Math.max(max, dp[i]);
            }
            System.out.println("dp[i]->" + dp[i]);
        }
        //时间复杂度：o(n) 空间复杂度o(n)
        return max;
    }

    private int lvpStack(String s) {
        if(s==null || s.length()<2) return 0;
        int max = 0;
        //创建stack 栈用于存储 栈底元素和 push入栈的 ( index，栈底元素用于辅助判定（始终保持栈底为非‘（’ 位置的index）
        Stack<Integer> stack = new Stack<>();
        stack.push(-1); //safe keep to bottom stack
        for(int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == '(') {
                stack.push(i);
            } else{ // charAt i == ')'
                stack.pop();
                if(stack.isEmpty()) {
                    //说明current stack bottom 中没有'(' 此时safe push current i
                    stack.push(i);
                } else {
                    //❌ 当前i - top index 即为长度，不需要额外+2！stack save的top element 即为 符合条件的子序列括号起始位置的前一位置（便于 minus 直接得到结果而不用+1）
                    max = Math.max(max, i-stack.peek());
                    // System.out.println("max->" + max); //2/4/6
                }
            }
        }
        return max;
    }


    //双指针 从前到后 和 从后向前 分别规避 (() 和 ())两类情况
    private int lvpDoubleIndex(String s) {
        if(s == null || s.length()<2) return 0;
        //分别代表当前 左右括号的个数
        int left = 0; int right = 0;
        int max = 0;
        for(int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            //判定left 和 right 关系
            if(left == right) {
                max = Math.max(max, 2*left);
            } else if(right>left) {
                //reset
                left = 0;
                right = 0;
            }
        }
        left = 0; right = 0;
        for(int j = s.length()-1; j>=0; j--) {
            if(s.charAt(j) == '(') {
                left ++;
            } else {
                right ++;
            }
            if(left == right) {
                max = Math.max(max, 2*right);
            } else if(left>right){
                left = 0;
                right = 0;
            }
        }
        return max;
    }
}
