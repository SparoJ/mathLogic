package com.sparo.data.tree;

import com.sparo.util.Utils;

import java.util.Stack;

/**
 *
 * description: 什么是 递归
 * 递归分为两个内容 一个是 递／ 一个是归
 * 递是从上到下深入 归是从下到上返回
 *
 * Created by sdh on 2019-07-13
 */
public class BST<E extends Comparable<E>> {


    class Node {
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
        if(node == null) {
            size++;
            return new Node(e);
        }

        //比较 然后 遍历对应节点树
        if (e.compareTo(node.e)>0) {//e > root.e
            //right
            node.right = addElement(node.right, e);
        } else if(e.compareTo(node.e)<0) {
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
        if (e.compareTo(root.e)==0) {
            return true;
        } else if(e.compareTo(root.e)>0) {
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
     *
     * @param root 递归所在层节点
     * @param i 当前层数
     * @param builder
     * @return
     */
    private void generateToString(Node root, int i, StringBuilder builder) {
        if (root == null) {
            Utils.println(getDeepthStr(i) + "BST end");
            return;
        }
        Utils.println(getDeepthStr(i) +"node:"+ root);
        generateToString(root.left, i+1, builder);
        generateToString(root.right, i+1, builder);
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
     */
    public void leverOrder() {

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

        while(!stack.isEmpty()) { //stack.peak() stack.isEmpty() --->>> stack.peek() 会在empty时候null exception
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
}
