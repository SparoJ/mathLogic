package com.sparo.leetcode.node;

/**
 * description:
 * Created by sdh on 2019-11-28
 */
public class NodeReverse {

    public static class Node<E> {
        public Node next;
        public E e;

        public Node(E e) {
            this.e = e;
            this.next = null;
        }
    }

    // 链表反转 递归

    //链表反转 遍历

    public void reverseNode(Node node) {
        // 前一个节点
        Node pre = null;
        // 后一个节点
        Node next = null;
        while(node!=null) {
            next = node.next;
        }
    }
}
