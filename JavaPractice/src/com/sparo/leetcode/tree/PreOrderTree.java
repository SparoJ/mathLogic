package com.sparo.leetcode.tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * description: 144. 二叉树的前序遍历
 * 给定一个二叉树，返回它的 前序 遍历。
 *
 *  示例:
 *
 * 输入: [1,null,2,3]
 *    1
 *     \
 *      2
 *     /
 *    3
 *
 * 输出: [1,2,3]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-preorder-traversal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * Created by sdh on 2020-07-16
 */
public class PreOrderTree {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

        public List<Integer> preorderTraversal(TreeNode root) {
            // return preBSTStack(root);
            return preBSTPath(root);
        }

        // path 效率次之
        class Path {
            public int val;
            public TreeNode node;

            public Path(int status, TreeNode tn) {
                this.val = status;
                this.node = tn;
            }
        }

        private List<Integer> preBSTPath(TreeNode root) {
            ArrayList<Integer> list = new ArrayList<>();
            if (root == null) return list;
            ArrayDeque<Path> deque = new ArrayDeque<>();
            //或者使用 ArrayDeque 作为stack方式使用 ，这里的 addFirst == push 都是让后入的先出，先入的后出
            deque.addFirst(new Path(1, root));
            while (!deque.isEmpty()) {
                Path path = deque.poll();
                if (path.node != null) {
                    if (path.val == 0) {
                        //print 操作
                        list.add(path.node.val);
                    } else {
                        // ❌ 对象添加 以stack方式使用deque 添加包装类对象
                        deque.addFirst(new Path(1, path.node.right));
                        deque.addFirst(new Path(1, path.node.left));
                        deque.addFirst(new Path(0, path.node));
                    }
                }
            }
            return list;
        }

    // 最后
        private List<Integer> preBSTStack(TreeNode root) {
            ArrayList<Integer> list = new ArrayList<>();
            if (root == null) return list;
            //辅助遍历结构
            Stack<TreeNode> stack = new Stack<>();
            stack.push(root);
            while (!stack.isEmpty()) {
                //先pop 第一次执行遍历操作
                TreeNode node = stack.pop();
                //之后 每次都是先pop null 然后 按照 print/遍历进行操作
                if (node != null) {
                    //遍历
                    if (node.right != null)
                        stack.push(node.right);
                    if (node.left != null)
                        stack.push(node.left);
                    stack.push(node);
                    //作为print/遍历操作的区分依据
                    stack.push(null);
                } else {
                    //真实出栈 执行print操作
                    TreeNode tn = stack.pop();
                    list.add(tn.val);
                }
            }
            return list;
        }
}
