package com.sparo.leetcode.tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * description: 662. 二叉树最大宽度
 * 给定一个二叉树，编写一个函数来获取这个树的最大宽度。树的宽度是所有层中的最大宽度。这个二叉树与满二叉树（full binary tree）结构相同，但一些节点为空。
 *
 * 每一层的宽度被定义为两个端点（该层最左和最右的非空节点，两端点间的null节点也计入长度）之间的长度。
 *
 * 示例 1:
 *
 * 输入:
 *
 *            1
 *          /   \
 *         3     2
 *        / \     \
 *       5   3     9
 *
 * 输出: 4
 * 解释: 最大值出现在树的第 3 层，宽度为 4 (5,3,null,9)。
 * 示例 2:
 *
 * 输入:
 *
 *           1
 *          /
 *         3
 *        / \
 *       5   3
 *
 * 输出: 2
 * 解释: 最大值出现在树的第 3 层，宽度为 2 (5,3)。
 * 示例 3:
 *
 * 输入:
 *
 *           1
 *          / \
 *         3   2
 *        /
 *       5
 *
 * 输出: 2
 * 解释: 最大值出现在树的第 2 层，宽度为 2 (3,2)。
 * 示例 4:
 *
 * 输入:
 *
 *           1
 *          / \
 *         3   2
 *        /     \
 *       5       9
 *      /         \
 *     6           7
 * 输出: 8
 * 解释: 最大值出现在树的第 4 层，宽度为 8 (6,null,null,null,null,null,null,7)。
 * 注意: 答案在32位有符号整数的表示范围内。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-width-of-binary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * Created by sdh on 2020-07-19
 */
public class WidthOfBinaryTree {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

//20200720 版
//    int maxWidth;
//    LinkedList<Integer> list;
//    private int wobtRecursion(TreeNode root) {
//        if(root == null) return 0;
//        //用于记录最大宽度值
//        maxWidth = 1; //root 非空，width至少为1
//        //用于记录对应level层第一个节点的索引
//        list = new LinkedList<>();
//        recurHelper(root, 0, 1);
//        return maxWidth;
//    }
//
//    private void recurHelper(TreeNode node, int level, int index) {
//        if(node == null) return;
//        if(level==list.size()) {
//            list.add(index);
//        } else {
//            //list 存的是当前层的第一个节点的索引，用当前层其他节点的索引减去第一个节点索引+1即为当前最大宽度值
//            maxWidth = Math.max(maxWidth, index-list.get(level)+1);
//        }
//        //递归
//        recurHelper(node.left, level+1, 2*index);
//        recurHelper(node.right, level+1, 2*index+1);
//    }
//
//
//    private int wobtTraversalOriginal(TreeNode root) {
//        if(root == null) return 0;
//        //队列初始化
//        ArrayDeque<TreeNode> queue = new ArrayDeque<>();
//        queue.offer(root);
//        //存储index 的list getLast/getFirst && removeLast peekFirst/peekLast均是deque 接口的api，deque继承queue 接口，queue接口没有以上api，只有基础的 offer/poll/peek/remove/add等
//        LinkedList<Integer> list = new LinkedList<>();//因为需要remove所以选择链表
//        list.add(1);
//        int maxWidth = 1;
//        while(!queue.isEmpty()) {
//            int size = queue.size();
//            //LinkedList 作为 Deque才有的api能力
//            maxWidth = Math.max(maxWidth, list.getLast()-list.getFirst()+1);
//            //取队首作为锚点值
//            int pivotIndex = list.peekFirst();
//            for(int i = 0; i < size; i++) {
//                //❌ 同步队列 从队首出队 (错误示范 按照记忆？调用了removeLast！)
//                int index = list.removeFirst();
//                TreeNode node = queue.poll();
//                if(node.left!=null) {
//                    queue.offer(node.left);
//                    list.add(index*2-pivotIndex);
//                }
//                if(node.right!=null) {
//                    queue.offer(node.right);
//                    list.add(index*2+1-pivotIndex);
//                }
//            }
//        }
//        return maxWidth;
//    }
//
//    private int wobtTraversal(TreeNode root) {
//        if(root == null) return 0;
//        ArrayDeque<TreeNode> queue = new ArrayDeque<>();
//        queue.offer(root);
//        int maxWidth = 1;
//        //修改root val
//        root.val = 1;
//        while(!queue.isEmpty()) {
//            int size = queue.size();
//            maxWidth = Math.max(maxWidth, queue.peekLast().val-queue.peekFirst().val+1);
//            //防止超界可设置pivot 值
//            int pivotIndex = queue.peekFirst().val;
//            for(int i = 0; i < size; i++) {
//                TreeNode node = queue.poll();
//                int index = node.val;
//                if(node.left!=null) {
//                    node.left.val = 2*index-pivotIndex;
//                    queue.offer(node.left);
//                }
//                if(node.right!=null) {
//                    node.right.val = 2*index+1-pivotIndex;
//                    queue.offer(node.right);
//                }
//            }
//        }
//        return maxWidth;
//    }

    //如果在尝试dfs/bfs 得到width时 存在超过32层越界int的可能时，可改用double 记录，另对bfs可在每层遍历时记录first值
    public int widthOfBinaryTree(TreeNode root) {

        //递归 稳定1ms 时间最优 空间last
        // return wobtRecursion(root);

        //以下两种方法实测 时间效率不如 递归方案，空间效率依次提升
        //使用两个额外的队列数据结构处理分别处理遍历+遍历过程中node 的 index维护 稳定2ms 时间last 空间次之
        // return wobtTraversal(root);
        //只使用一个队列维护遍历过程，index 通过改变root的val记录即可！（1-2ms间） 时间次之，空间最优
        return wobtTraversalP(root);
    }

    //不使用额外的数据结构存储index，减少在遍历时移除的操作，这里改变Node 中val的值为index
    private int wobtTraversalP(TreeNode root) {
        if(root == null) return 0;
        ArrayDeque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        root.val = 1; // 修改node val 值为对应的index
        int maxWidth = 1; //root 非null则 width 最小值为1！！！
        while(!queue.isEmpty()) {
            maxWidth = Math.max(maxWidth, queue.peekLast().val - queue.peekFirst().val+1);
            int size = queue.size();
            //防止越界处理 🚩
            int pivotIndex = queue.peek().val;
            for(int i=0; i<size; i++) {
                TreeNode node = queue.poll();
                int index = node.val;
                if(node.left!=null) {
                    // node.left.val = 2*index;
                    node.left.val = 2*index-pivotIndex;
                    queue.offer(node.left);
                }
                if(node.right!=null) {
                    // node.right.val = 2*index + 1;
                    node.right.val = 2*index+1-pivotIndex;
                    queue.offer(node.right);
                }
            }
            //最后一层叶子节点判定后，这里queue会null，当queue为null 时 peekLast/peekFirst都会NPE
            //❌当层遍历完成后获取queue的首尾node 中存的 index得到当前层的width
            // maxWidth = Math.max(maxWidth, queue.peekLast().val - queue.peekFirst().val+1); //注意+1， 4567/7-4+1 index 的差值+1才是正确的宽度
        }
        return maxWidth;
    }


    private int wobtTraversal(TreeNode root) {
        if(root == null) return 0;
        //层序遍历
        ArrayDeque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        LinkedList<Integer> list = new LinkedList<>();
        int maxWidth = 1;
        list.add(1);
        //这里可将index 替换成 list.add 在每次遍历当前节点时remove
        // int index = 1; //first root index，后序二叉树节点index在root index按照二叉树子节点规律更新
        while(!queue.isEmpty()) {
            int size = queue.size();
            // if(list.size()>=2) 移动到这里进行大值的判定！
            maxWidth = Math.max(list.getLast()-list.getFirst()+1, maxWidth);
            for(int i = 0; i < size; i++) {
                //从头取出（遍历完成时会将前一层的index移除，因为计算当前层的前后index关系是通过首位index 关系维护的）
                int index = list.removeFirst();
                TreeNode node = queue.poll();
                if(node.left!=null) {
                    queue.offer(node.left);
                    list.add(2*index);
                }
                if(node.right!=null) {
                    queue.offer(node.right);
                    list.add(2*index+1);
                }
            }
            //❌ list这里在循环中对应元素的index，队列中的元素移除清空时，list size ==0, LinkedList 中 getLast  和 getFirst 如果 last/first 为null 会报NoSuchElementException 运行时异常 ， 所以这里可以将获取大值的判定移到for 循环体之前，当到达二叉树最后一层时，在for前计算完成，for计算完后queue/list均空，此时因为没有后续子节点，所以也没有比较的意义 故改变api调用位置，理解代码和实际使用场景的含义才能更好更合理的规避风险和认识本质
            // if(list.size()>=2)
            // maxWidth = Math.max(list.getLast()-list.getFirst()+1, maxWidth);
        }
        return maxWidth;
    }



    //用于记录对应层数（对应数组index）的第一个起始位置index，用于持续更新maxWidth
    List<Integer> list = new ArrayList<>();
    //base case ❌ 最少root 非null时置为1
    int maxWidth=1;
    private int wobtRecursion(TreeNode root) {
        //初始化 root 节点处 level=0， index =1(level 为1 则需要从1位置开始add 和 get)
        if(root == null) return 0;
        recurHelper(root, 0, 1);
        return maxWidth;
    }


    private void recurHelper(TreeNode root, int level, int index) {
        //递归终止条件，屏蔽了之后左右子树中可能存在的null元素干扰，递归中root元素的index皆为有效index
        if(root == null) return;
        //说明为该level 对应层的第一个元素，记录当前层的最左index
        //❌ java 语法基础问题：如果list中对应index位置无元素，会BOIndex异常，这里改用 level 和 size 之间的关系,怎样判断当前递归到的是新的一层？
        // if(list.get(level)==null) {
        // if(level>=list.size()) { //说明当前level对应的index还不存在于list中，即level为新递归到的层数
        //     list.add(index);
        // } else {
        //     //非第一个元素，通过当前层对应的最左index记录最大width
        //     maxWidth = Math.max(maxWidth, index-list.get(level)+1);
        // }
        if(level==list.size()) {
            list.add(index);
        }
        //非第一个元素，通过当前层对应的最左index记录最大width
        maxWidth = Math.max(maxWidth, index-list.get(level)+1);
        recurHelper(root.left, level+1, 2*index);
        recurHelper(root.right, level+1, 2*index+1);
    }
}
