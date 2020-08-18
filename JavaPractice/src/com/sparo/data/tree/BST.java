package com.sparo.data.tree;

import com.sparo.util.Utils;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 * description: 什么是 递归
 * 递归分为两个内容 一个是 递／ 一个是归
 * 递是从上到下深入 归是从下到上返回
 * <p>
 * Created by sdh on 2019-07-13
 */
public class BST<E extends Comparable<E>> {


    public class Node {
        public E e;

        public Node left;

        public Node right;

        public Node(E e) {
            this.e = e;
            left = null;
            right = null;
        }

        @Override
        public String toString() {
            return e.toString();
        }
    }

    /**
     * 二分搜索树 根节点
     */
    private Node root;

    /**
     * size
     */
    public int size;

    public BST() {
        root = null;
        size = 0;
    }

    /**
     * @return empty
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * @return 返回tree element size
     */
    public int size() {
        return size;
    }

    public void add(E e) {
        //完成递归调用后 将链表返回给 Node 的根节点
        root = addElement(root, e);
    }

    //递归调用
    private Node addElement(Node node, E e) {
        if (node == null) {
            size++;
            return new Node(e);
        }

        //比较 然后 遍历对应节点树
        if (e.compareTo(node.e) > 0) {//e > root.e
            //right
            node.right = addElement(node.right, e);
        } else if (e.compareTo(node.e) < 0) {
            node.left = addElement(node.left, e);
        }
        return node;
    }

    public boolean contains(E e) {
        return contains(root, e);
    }

    //二分搜索树 查询操作
    private boolean contains(Node root, E e) {
        if (root == null) {
            return false;
        }
        if (e.compareTo(root.e) == 0) {
            return true;
        } else if (e.compareTo(root.e) > 0) {
            //右子树
            return contains(root.right, e);
        } else {
            //左子树
            return contains(root.left, e);
        }
    }

    /**
     * 二分搜索树 前序遍历 ： 节点 left right 作用：遍历
     */
    public void preOrder() {
        preOrder(root);
    }

    private void preOrder(Node root) {
        if (root == null) {
            return;
        }
        //node
        Utils.println(root);
        //left
        preOrder(root.left);
        //right
        preOrder(root.right);
    }


    /**
     * 中序遍历 作用 ： 先左 再 父亲节点 再右 获取顺序
     */
    public void midOrder() {
        midOrder(root);
    }

    private void midOrder(Node root) {
        if (root == null) {
            return;
        }
        midOrder(root.left);
        //
        Utils.println(root);
        //right
        midOrder(root.right);
    }

    /**
     * 后序遍历 作用：先访问 左右子树 再父亲节点 删除操作／统计
     */
    public void laterOrder() {
        laterOrder(root);
    }

    private void laterOrder(Node root) {
        if (root == null) {
            return;
        }
        laterOrder(root.left);
        laterOrder(root.right);
        Utils.println(root);
    }


    @Override
    public String toString() {
        //递归遍历 打印
        StringBuilder builder = new StringBuilder();
        generateToString(root, 0, builder);
        return builder.toString();
    }

    /**
     * @param root    递归所在层节点
     * @param i       当前层数
     * @param builder
     * @return
     */
    private void generateToString(Node root, int i, StringBuilder builder) {
        if (root == null) {
            Utils.println(getDeepthStr(i) + "BST end");
            return;
        }
        Utils.println(getDeepthStr(i) + "node:" + root);
        generateToString(root.left, i + 1, builder);
        generateToString(root.right, i + 1, builder);
    }

    private Object getDeepthStr(int deep) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < deep; i++) {
            builder.append("--");
        }
        return builder.toString();
    }


    /**
     * 层序遍历
     * 原理： 使用队列 入队和出队来管理node
     * 可使用 LinkedList 作为 queue
     */
    public void levelOrder() {
        Queue<Node> list = new LinkedList<>();

        //入队
        list.add(root); //添加队尾
        Utils.println(root);
        while (!list.isEmpty()) { //移除从队首移除 参考 Queue 接口实现 这里是双端队列接口继承后的 双端链表
            Node remove = list.remove();//移除队首元素
            if (remove.left != null) {
                list.add(remove.left);
            }
            if (remove.right != null) {
                list.add(remove.right);
            }
        }
    }

    /**
     * 非递归 前序遍历
     * 使用栈数据结构
     */
    public void noTR() {
        Utils.println(this);
        //首先声明 存储结构 栈
        Stack<Node> stack = new Stack<>();
        //其次入栈同时出栈对应子树
        if (root != null) {
            stack.push(root);
        }

        while (!stack.isEmpty()) { //stack.peak() stack.isEmpty() --->>> stack.peek() 会在empty时候null exception
            //出栈打印
            Node node = stack.pop();
            Utils.println(node);
            //前序遍历 先左后右 结合栈的特点是：右先入栈 左再入栈【出的时候相反 后入栈先出栈 即先遍历】
            if (node.right != null) {
                stack.push(node.right);
            }
//            Utils.println("right:"+ node.right);
            if (node.left != null) {
                stack.push(node.left);
            }
//            Utils.println("left:" + node.left);
        }
    }

    /**
     * *********************************************delete***************************************************
     * 1. 先 find max／min
     * 2. 再删除 max ／min
     * 3. 再删除普通节点
     */
    public Node minNode() {
        return minNode(root);
    }

    //     * 1. 先 find max／min
    /**
     * @param root
     * @return 查找以root 为 根节点的 二分搜索树中的 最小值
     */
    private Node minNode(Node root) {
        if (root == null) {
            return null;
        }
        return root.left!=null?minNode(root.left):root;
    }

    public Node maxNode() {
        return maxNode(root);
    }

    /**
     * @param root
     * @return  查找以root 为根节点的 二分搜索树中的 最大值
     */
    private Node maxNode(Node root) {
        if (root == null) {
            return null;
        }
        return root.right !=null?maxNode(root.right):root;
    }

    // * 2. 再删除 max ／min [要删除max／min 需要先找到max 和min]
    public Node removeMin() {
        //find min
        Node minNode = minNode();
        //traversal
        root = removeMin(root);
        return minNode;
    }

    /**
     * @param root
     * @return 删除以root为根的 最小元素后的 二分搜索树
     */
    private Node removeMin(Node root) {
        if (root == null) {
            return null;
        }
        //左子树继续
        if (root.left != null) {
            root.left = removeMin(root.left);
        } else { //root 为最小值
            //说明没有左子树 则将右子树 赋值给当前root 返回 且删除当前节点
            Node right = root.right;
            // 删除当前节点【min值】
            root.right = null;
            size --;
            root =  right;
        }
        return root;
    }


    /**
     * 删除以root 为根，Node 中 值为 e的 bst
     * delete element
     */
    public void removeElement(E e) {
        root = removeElement(root, e);
    }

    /**
     * 移除以root 为根 中的 node值为e 的节点【而非删除Node 节点】
     * @param root
     * @param e 需要删除的元素
     */
    private Node removeElement(Node root, E e) {
        if (root == null || e == null) {
            return null;
        }
        if (e.compareTo(root.e)>0) { // 右子树
            root.right = removeElement(root.right, e);
        } else if(e.compareTo(root.e)<0) { // 左子树
            root.left = removeElement(root.left, e);
        } else { // 相等 即为要移除的 node
            if(root.right == null) {
                Node left = root.left;
                root.left = null;
                size --;
                return left;
            } else if(root.left == null) {
                Node right = root.right;
                root.right = null;
                size --;
                return right;
            } else {
                //左右子树都不为空时
                //获取当前所要删除节点的子树中的 右子树中的最小值[比root大且最接近root的值]
                // 当作删除后的新root 而将当前root删除
                Node minRoot = minNode(root.right);
                Node rightRoot = removeMin(root.right); //删除右子树中最小值后的root树
                Node left = root.left;
                root = minRoot;
                root.left = left;
                root.right = rightRoot;
                return root;
            }
        }
        return root;
    }


    //*********************************************delete***************************************************

    public List<Integer> testPreorderNew() {
        return preorderTraversal(root);
    }

    /**
     * Definition for a binary tree node.
     */
    public List<Integer> preorderTraversal(Node root) {
        // 结果集合
        ArrayList<Integer> list = new ArrayList<>();
        /**
         * 将遍历考虑成一个Node 的三个行为操作，print/left/right 整体是一个traversal
         * 封装成对象
         */
        ArrayDeque<Path> deque = new ArrayDeque<>();
        deque.addFirst(new Path(1, root));
        while (!deque.isEmpty()) {
            Path path = deque.removeFirst();
            if (path.node != null) {
                if (path.val == 0) {
                    // print
                    list.add((Integer) path.node.e);
                } else if (path.val == 1) {
                    /**
                     * traversal one node with three events （traversal left and                          * right and print the node value）
                     */
                    deque.addFirst(new Path(1, path.node.right));
                    deque.addFirst(new Path(1, path.node.left));
                    deque.addFirst(new Path(0, path.node));
                }
            }
        }
        return list;
    }

    public class Path {
        // 0 stands for print , 1 stands for traversal
       public int val;
        Node node;

        public Path(int value, Node node) {
            this.val = value;
            this.node = node;
        }
    }


    public Node convertBSTtoNode() {
        return recursionNode(root);
    }

    private Node recursionNode(Node root) {

        if (root==null) {
            return null;
        }
        // 左子树
        Node cur = recursionNode(root.left);
        if (cur!=null) {
            while(cur.right!=null) {
                cur = cur.right;
            }
            root.left = cur;
            cur.right = root;
        }
        // 右子树
        Node curR = recursionNode(root.right);
        if (curR != null) {
            while(curR.left!=null) {
                curR = curR.left;
            }
            root.right = curR;
            curR.left = root;
        }

        return null;
    }
}
