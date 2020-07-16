package com.sparo.leetcode.tree;

/**
 * description: leetcode
 * 222. 完全二叉树的节点个数
 * 给出一个完全二叉树，求出该树的节点个数。
 *
 * 说明：
 *
 * 完全二叉树的定义如下：在完全二叉树中，除了最底层节点可能没填满外，其余每层节点数都达到最大值，并且最下面一层的节点都集中在该层最左边的若干位置。若最底层为第 h 层，则该层包含 1~ 2h 个节点。
 *
 * 示例:
 *
 * 输入:
 *     1
 *    / \
 *   2   3
 *  / \  /
 * 4  5 6
 *
 * 输出: 6
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/count-complete-tree-nodes
 * Created by sdh on 2020-07-16
 */
public class CountCompleteTreeNode {

     public class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;
      TreeNode(int x) { val = x; }
     }

    //lgn*lgn
    private int cn(TreeNode root) {
        if(root == null) return 0;
        int left = height(root.left);
        int right = height(root.right);
        if(left == right) {
            //右子树 不完全
            return (1<<left)+cn(root.right);
        } else {
            //左子树不完全
            return (1<<right)+cn(root.left);
        }
    }

    //本题完全二叉树 优化方式 lgn
    private int height(TreeNode node) {
        int count = 0;
        while(node!=null) {
            count++;
            node = node.left;
        }
        return count;
    }

    //通用获取二叉树高度方式 2*lgn
    private int countLevel(TreeNode node) {
        if(node == null) return 0;
        return Math.max(countLevel(node.left), countLevel(node.right))+1;
    }
}
