package com.sparo.leetcode.tree;

/**
 * description: 109. 有序链表转换二叉搜索树
 * 给定一个单链表，其中的元素按升序排序，将其转换为高度平衡的二叉搜索树。
 *
 * 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。
 *
 * 示例:
 *
 * 给定的有序链表： [-10, -3, 0, 5, 9],
 *
 * 一个可能的答案是：[0, -3, 9, -10, null, 5], 它可以表示下面这个高度平衡二叉搜索树：
 *
 *       0
 *      / \
 *    -3   9
 *    /   /
 *  -10  5
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/convert-sorted-list-to-binary-search-tree
 * Created by sdh on 2020-07-16
 */
public class SortedListToBST {

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public TreeNode sortedListToBST(ListNode head) {

        return sltBST(head);
    }

    // 1.数组元素升序 2.二叉搜索树 高度平衡  -> 中序遍历构建二叉搜索树+二分
    //找到中间节点 作为当前层的root 然后左右继续按照中序构建bst 最后链接
    ListNode cur = null;

    private TreeNode sltBST(ListNode head) {
        cur = head;
        //对应角标从前到最后一个元素
        int end = 0;
        while (head != null) {
            head = head.next;
            end++;
        }
        return sltBSTHelper(0, end);
    }

    private TreeNode sltBSTHelper(int start, int tail) {
        if (start == tail) return null;
        //找到中间位置
        int middle = start + (tail - start) / 2;

        TreeNode left = sltBSTHelper(start, middle);
        TreeNode root = new TreeNode(cur.val);
        root.left = left;
        cur = cur.next;
        TreeNode right = sltBSTHelper(middle + 1, tail);
        root.right = right;
        return root;
    }
}
