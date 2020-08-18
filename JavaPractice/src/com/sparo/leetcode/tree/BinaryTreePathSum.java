package com.sparo.leetcode.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * description: 剑指 Offer 34. 二叉树中和为某一值的路径
 *
 * 输入一棵二叉树和一个整数，打印出二叉树中节点值的和为输入整数的所有路径。从树的根节点开始往下一直到叶节点所经过的节点形成一条路径。
 *
 *  
 *
 * 示例:
 * 给定如下二叉树，以及目标和 sum = 22，
 *
 *               5
 *              / \
 *             4   8
 *            /   / \
 *           11  13  4
 *          /  \    / \
 *         7    2  5   1
 * 返回:
 *
 * [
 *    [5,4,11,2],
 *    [5,8,4,5]
 * ]
 *  
 *
 * 提示：
 *
 * 节点总数 <= 10000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/er-cha-shu-zhong-he-wei-mou-yi-zhi-de-lu-jing-lcof
 *
 * 本题与主站 113 题相同：https://leetcode-cn.com/problems/path-sum-ii/
 *
 * Created by sdh on 2020-07-18
 */
public class BinaryTreePathSum {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    List<List<Integer>> ans;
    int target;
    //参考 二叉树 所有的路径问题，可以先求的路径然后累加确认是否为符合条件的解
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        //声明全局变量，直接使用
        ans = new ArrayList<>();
        target = sum;
        //不用回溯 直接添加计算
        psList(root, new ArrayList<Integer>(), 0);

        return ans;
    }

    //❌ 关键点：1使用回溯过滤了list 可能重复添加的元素，因为全局只传递了一个list用于维护走过的路径节点，传递引用的值到其他方法栈中会导致数据重复，需要在归时按照回溯框架移除最后添加的节点 恢复到上一层， 🚩因为这里的list 不等于String，同之前提到的二叉树全路径中的 StringBuilder 全局递归只有一个有相同道理，在于如何维护当前层所遍历过的节点  2.改进过滤方案避免不必要的添加数据，通过额外的 addVal记录当前从root到 根节点的和 不等则直接返回 提升优化效率
    private void psList(TreeNode root, List<Integer> list, int addVal) {
        if(root == null) return;
        if(root.left == null && root.right == null) {
            addVal+=root.val;
            if(addVal!=target) return;
            List<Integer> res = new ArrayList<>();
            for(Integer num : list) {
                res.add(num);
            }
            res.add(root.val);
            ans.add(res);
            return;
        }
        list.add(root.val);
        addVal+=root.val;
        psList(root.left, list, addVal);
        psList(root.right, list, addVal);
        list.remove(list.size()-1);
    }

}
