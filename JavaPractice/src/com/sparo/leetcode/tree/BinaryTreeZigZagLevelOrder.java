package com.sparo.leetcode.tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

/**
 * description: 103. 二叉树的锯齿形层次遍历
 * 给定一个二叉树，返回其节点值的锯齿形层次遍历。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
 *
 * 例如：
 * 给定二叉树 [3,9,20,null,null,15,7],
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回锯齿形层次遍历如下：
 *
 * [
 *   [3],
 *   [20,9],
 *   [15,7]
 * ]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-zigzag-level-order-traversal
 * Created by sdh on 2020-07-19
 */
public class BinaryTreeZigZagLevelOrder {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    //解题方法同步🤔🆚 662. 二叉树最大宽度🚩
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {

        // return zzloBfsRight(root);

        // return zzloBfsSimple(root);

        return zzloRecurDfs(root);
    }

    //简化递归为终版：
    private void recurHelperFinal(TreeNode root, int level) {
        //递归终止 base case ❌ == 符号！而非 “=”
        if(root == null) return;
        if(level== ans.size()) {
            ans.add(new ArrayList<Integer>()); //注意这里new时需要范型
        }
        //root作为偶数层视角添加元素 方案 ❌ 位运算注意啊！🚩
        if((level&1)==1) {
            ans.get(level).add(0, root.val);
        } else {
            ans.get(level).add(root.val);
        }
        //递归
        recurHelperFinal(root.left, level+1);
        recurHelperFinal(root.right, level+1);
    }

    List<List<Integer>> ans;
    // dfs 深度递归方式 递归调用方法的同时记录当前所在层，根据层数奇偶添加数据
    private List<List<Integer>> zzloRecurDfs(TreeNode root) {
        ans = new ArrayList<>();
        //这里可以不需要，在递归helper方法里直接return 结束
        if(root == null) return ans;
        //过程思路版，按照root为1 奇数层来计算
        // recurHelper(root, 0);
        //简化版 按照root 为偶数层来计算
        recurHelperFinal(root, 0);
        return ans;
    }

    private void recurHelper(TreeNode root, int level) {
        if(root == null) return;
        //判断level是否是新开的一层，如果是则新建否则直接add，如果直接add也需要对应到当前层的子list， 子list如何和同当前level的list 对应？ 通过全局结果ans的 脚标来控制，so level建议初始化为0 方便直接拿来用，不用额外处理
        //ans 没有对应index的元素时，不能直接get 否则会NoSuchElementException,改为size和level的大小逻辑判断 同全局初始list 计算 max 和的 leetcode类似！（待补全该题目全名和题号）
        //以下到添加之前的代码作用为，构建属于当前层的list 用于添加当前层对应的元素集合
        List<Integer> res = null;
        if(level>=ans.size()) {
            res = new ArrayList<>();
            ans.add(res);
        } else {
            res = ans.get(level);
        }

        //❌ level 自加位置注意，如果root作为0 偶数层则 反过来，即 偶数层从左到右，奇数层从右到左添加add(0，xx)，如果level++ 在添加元素之后执行的话按照当前描述的root作为偶数层维护，如果要坚持按照root为 奇数层维护 则需要关注level在add元素前维护过后再进行元素添加
        level++;
        // 得到添加本层的元素list后，再根据奇偶level添加，奇数添加到尾部(add(xx))，偶数则从头开始添加(add(0,xx))
        if((level&1)==1) {
            res.add(root.val);
        } else {
            res.add(0, root.val);
        }

        //递归别忘了
        recurHelper(root.left, level);
        recurHelper(root.right, level);
    }



    private List<List<Integer>> zzloBfsSimple(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if(root == null) return ans;
        ArrayDeque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        int level = 0;
        while(!queue.isEmpty()) {
            List<Integer> list = new ArrayList<>();
            int size = queue.size();
            level++;
            for(int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                //bfs的顺序不变，仍然是从上到下，从左到右
                // !❌❌ 位运算符要使用括号避免运算符优先级问题❌❌！
                if((level&1)==1) {
                    //添加到list的尾部，先添加的在前面
                    list.add(node.val);
                } else {
                    //从list的头部添加，先添加的会排到后面
                    list.add(0, node.val);
                }
                if(node.left!=null)
                    queue.offer(node.left);
                if(node.right!=null)
                    queue.offer(node.right);
            }
            ans.add(list);
        }
        return ans;
    }

    //常规解题思维：完全按照bfs 想要的结果 奇数层的child 从左到右添加到尾部offer，到偶数层出队列时从队列尾部从后向前取出 removeLast， 偶数层的child 从head添加，且右子节点在前，左子节点在后添加
    private List<List<Integer>> zzloBfsRight(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if(root == null) return ans;
        ArrayDeque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        int level = 0;
        while(!queue.isEmpty()){
            int size = queue.size();
            List<Integer> res = new ArrayList<>();
            level++;
            for(int i = 0; i<size; i++) {
                if(level%2 == 0) {
                    //偶数层从tail端取出元素
                    TreeNode even = queue.removeLast();
                    res.add(even.val);
                    //添加偶数层的子元素从头部添加且右子节点先添加，左子节点后添加
                    if(even.right!=null) {
                        queue.addFirst(even.right);
                    }
                    if(even.left!=null) {
                        queue.addFirst(even.left);
                    }
                } else {
                    //奇数层取出元素从head端取出
                    TreeNode odd = queue.poll();
                    res.add(odd.val);
                    //奇数层子元素正常从左到右添加
                    if(odd.left!=null) {
                        queue.offer(odd.left);
                    }
                    if(odd.right!=null) {
                        queue.offer(odd.right);
                    }
                }
            }
            ans.add(res);
        }
        return ans;
    }

    //通过奇数偶数层来左右区分添加到队列 处理存在 当前添加的元素按照某一方向，只有对应元素的child 才按照反方向遍历添加进来，而不是整个下一层 即  [3,9,20,10,4,15,7] [[3],[20,9],[15,7,10,4]]wrong / [[3],[20,9],[10,4,15,7]] true
    private List<List<Integer>> zzloWrong(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if(root == null) return ans;
        ArrayDeque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        int level = 1;
        while(!queue.isEmpty()) {
            int size = queue.size();
            level++;
            ArrayList<Integer> res = new ArrayList<>();
            for(int i=0; i< size; i++) {
                TreeNode node = queue.poll();
                res.add(node.val);
                if(level%2 ==0) {
                    if(node.right!=null)
                        queue.offer(node.right);
                    if(node.left!=null)
                        queue.offer(node.left);
                } else {
                    if(node.left!=null)
                        queue.offer(node.left);
                    if(node.right!=null)
                        queue.offer(node.right);
                }
            }
            ans.add(res);
        }
        return ans;
    }
}
