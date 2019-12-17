package com.sparo.line;

import java.util.Stack;

/**
 * description:
 * Created by sdh on 2019-12-07
 */
public class TestLine {

    public static void main(String[] args) {
        TestLine line = new TestLine();
//        String str = "())(())"; // type 1 normal
//        String str = ")))(((((()"; // type 2
        String str = "()))()((((";
//      int max = line.longestValidParentheses(str);
        int max = line.longestValidParenthesesPlus(str);
        System.out.println("max" +
                max);
    }


    public int longestValidParentheses(String s) {
        int max = 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                //下标入栈
                stack.push(i);
                System.out.println("I-->>"+ i + "::stack->push->>>" + stack);
            } else {
                // 出栈
                stack.pop();
                System.out.println("I-->>"+ i + "::stack->pop->>>" + stack);
                // 看栈顶是否为空，为空的话就不能作差了
                if (stack.empty()) {
                    stack.push(i);
                    System.out.println("I-->>"+ i + "::stack->empty&push->>>" + stack);
                } else {
                    // i - 栈顶，获得档期有效括号长度
                    int peek = stack.peek();
                    System.out.println("I-->>"+ i + "::stack top ::" + peek);
                    max = Math.max(max, i - peek);
                }
            }
        }
        System.out.println("max-->>" + max);
        return max;
    }

    public int longestValidParenthesesPlus(String s) {
        int left = 0, right = 0, max = 0;
        // 从左到右
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            if (left == right) {
                max = Math.max(max, 2 * right);
            } else if( right > left) {
                left = right = 0;
            }
        }
        left = right = 0;
        // 从右到左 从左到右
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            if (left == right) {
                max = Math.max(max, 2 * left);
            } else if (left > right) {
                left = right = 0;
            }
        }
        return max;
    }
}
