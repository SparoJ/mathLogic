package com.sparo.data.stack;

/**
 * description: { [ ( )] } question from leetcode
 * 解答回顾：
 * 等价简化代码： 一行代替多行的原因！！
 * //        if (stack.empty()) {
 * //            return true;
 * //        }
 * //        return false;
 *
 * 另外判断空是避免左多右少，未匹配完全 题目逻辑是左括号和右括号完全匹配出栈完全
 *
 * 且在 stack 出栈时 确保 stack非空，否则异常！！！
 *
 * Created by sdh on 2019-07-17
 */
public class BraceLeet {

    public static void main(String[] args) {
        String str = "{[({({([])})})]}";

        BraceLeet leet = new BraceLeet();
        boolean isValid = leet.validBrace(str);
        System.out.println(isValid);
    }

    private boolean validBrace(String str) {

//        ArrayStack<Character> stack = new ArrayStack<>();
        java.util.Stack <Character> stack = new java.util.Stack<>();
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if(ch == '{' || ch == '[' || ch == '(') { //left brace
                stack.push(ch);
            } else {
                // 注意空栈问题！！！ 判断的问题肯定是以 左括号入栈开始管理
                if (stack.empty()) {
                    return false;
                }
                if (ch == '}' && stack.pop()!='{') {
                    return false;
                } else if(ch == ']' && stack.pop()!='[') {
                    return false;
                } else if(ch == ')' && stack.pop()!='(') {
                    return false;
                }
            }
        }
//        if (stack.empty()) {
//            return true;
//        }
//        return false;
        return stack.empty();
    }

}
