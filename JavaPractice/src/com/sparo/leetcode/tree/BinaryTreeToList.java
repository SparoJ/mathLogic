package com.sparo.leetcode.tree;

import java.util.ArrayDeque;

/**
 * description:普通二叉树展开成链表 114. 二叉树展开为链表
 * 给定一个二叉树，原地将它展开为一个单链表。
 *
 *  
 *
 * 例如，给定二叉树
 *
 *     1
 *    / \
 *   2   5
 *  / \   \
 * 3   4   6
 * 将其展开为：
 *
 * 1
 *  \
 *   2
 *    \
 *     3
 *      \
 *       4
 *        \
 *         5
 *          \
 *           6
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/flatten-binary-tree-to-linked-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * Created by sdh on 2020-07-17
 */
public class BinaryTreeToList {


    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * 考点：二叉树性质理解（遍历特性 ->非二叉搜索树）
     * 	->变种遍历方式扩展（递归/普通遍历实现，需要结合遍历方式和题目双方的特点使用）
     * 	etc: 按照展开的结果来看符合前序特点，通过常规前序遍历方式无法保证记录当前前序到的root节点的右节点，需要辅助数据结构处理
     * 	反向来看展开的结果数据，可以从后向前串，这样既不会存在丢失右侧子节点的问题，也可以完成串联指向，从6<-5<-4...
     * 	以及常规画图后的清晰思路，将右侧子树接到左侧子树最右侧节点，再将左子树转成右子树，然后依次迭代右侧子节点
     * 	不管使用那种方法解决问题，都需要考虑 root left/right 之间的关系如何维护
     */

    public void flatten(TreeNode root) {
        // flatTraversal(root);
        //效率较低 因为额外使用了空间和空间处理元素耗时
        // flatPreOrderTraversalStack(root);
        flatRecursion(root);
    }

    //使用stack 前序遍历 原种前序遍历方式+功能扩展结合
    private void flatPreOrderTraversalStack(TreeNode root) {
        if(root == null) return;
        ArrayDeque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);
        TreeNode pre = null;
        while(!stack.isEmpty()) {
            TreeNode node = stack.pop();
            //额外处理 将当前弹栈的node节点作为下一次的root节点，结合栈内按照前序存储的子节点顺序弹栈对root 节点右侧赋值，左侧置空
            if(pre!=null) {
                pre.right = node;
                pre.left = null;
            }
            if(node.right!=null) stack.push(node.right);
            if(node.left!=null) stack.push(node.left);
            //额外处理
            pre = node;
        }
    }

    private void flatTraversal(TreeNode root) {
        while(root!=null) {
            if(root.left == null) {
                root = root.right;
            } else {
                //search rightest from left tree
                TreeNode rightest = root.left;
                while(rightest.right!=null) {
                    rightest = rightest.right;
                }
                //将当前root 节点的右子树接入到左子树 最右侧节点
                rightest.right = root.right;
                //将root右侧子树整合到root的左子树最右侧节点后，将root 左子树右倾
                root.right = root.left;
                //❌ 问题点关键容易遗漏:左子树置空
                root.left = null;
                root = root.right;
            }
        }
    }

    //根据变种后序遍历+递归思路 从后向前处理
    TreeNode pre = null;
    private void flatRecursion(TreeNode root) {
        if(root == null) return;
        flatRecursion(root.right);
        flatRecursion(root.left);
        root.right = pre;
        pre = root;
        //❌ 问题点关键容易遗漏: 将root 返回作为下一层root节点的右子树后，当前root的左子树置空，root这里如果有左子树则会作为下层root的pre 赋值给下层root的右子树 所以需要root 左子树置null
        root.left = null;
    }
}
