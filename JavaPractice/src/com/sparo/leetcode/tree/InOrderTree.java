package com.sparo.leetcode.tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * description:
 * 94. 二叉树的中序遍历
 * 给定一个二叉树，返回它的中序 遍历。
 *
 * 示例:
 *
 * 输入: [1,null,2,3]
 *    1
 *     \
 *      2
 *     /
 *    3
 *
 * 输出: [1,3,2]
 * Created by sdh on 2020-07-16
 */
public class InOrderTree {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
    class Path {
        TreeNode node;
        int state;
        public Path(TreeNode node, int state) {
            this.node = node;
            this.state = state;
        }
    }

    private List<Integer> itPath(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if(root == null) return ans;
        ArrayDeque<Path> stack = new ArrayDeque<>();
        stack.push(new Path(root, 1));
        while(!stack.isEmpty()) {
            Path path  = stack.pop();
            if(path.node!=null) {
                if(path.state == 0) {
                    ans.add(path.node.val);
                } else {
                    stack.push(new Path(path.node.right,1));
                    stack.push(new Path(path.node.left, 1));
                    stack.push(new Path(path.node, 0));
                }
            }
        }
        return ans;
    }

    private List<Integer> itStack(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if(root == null) return ans;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while(!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if(node == null) {
                TreeNode cur = stack.pop();
                ans.add(cur.val);
            } else {
                if(node.right!=null) {
                    stack.push(node.right);
                }
                stack.push(node);
                stack.push(null);
                if(node.left!=null) {
                    stack.push(node.left);
                }
            }
        }
        return ans;
    }
}
