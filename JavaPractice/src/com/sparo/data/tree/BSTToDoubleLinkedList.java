package com.sparo.data.tree;


class Node{
    Node left;
    Node right;
    int value;
    public Node(int value){
        this.value = value;
    }
}

public class BSTToDoubleLinkedList {
    public static Node treeToDoubleLinkedList(Node head){
        if (head == null){
            return null;
        }
        return process(head);
    }

    private static Node process(Node head) {
        if (head == null){
            return null;
        }
        Node leftNodeHead = process(head.left);
        Node rightNodeHead = process(head.right);
        Node leftNodeEnd = leftNodeHead;
        head.right = null;
        head.left = null;
        
        if (leftNodeHead != null){
            while (leftNodeEnd.right != null){ //多了一层遍历，需要根据头结点找到尾节点
                leftNodeEnd = leftNodeEnd.right;
            }
        }
        
        if (leftNodeEnd != null){
            leftNodeEnd.right = head;
            head.left = leftNodeEnd;
        }
        
        if (rightNodeHead != null){
            head.right = rightNodeHead;
            rightNodeHead.left = head;
        }
        return leftNodeHead == null ? head : leftNodeHead;
    }


    public static void main(String[] args) {
        // 生成树
        Node head = new Node(5);
        head.left = new Node(2);
        head.right = new Node(9);
        head.left.left = new Node(1);
        head.left.right = new Node(3);
        head.left.right.right = new Node(4);
        head.right.left = new Node(7);
        head.right.right = new Node(10);
        head.left.left = new Node(1);
        head.right.left.left = new Node(6);
        head.right.left.right = new Node(8);
        // 树转双向链表
        head = treeToDoubleLinkedList(head);
        printDoubleLinkedList(head);

    }

    /**
     * 证明了 这个Node 是 合规的双向链表
     * @param head
     */
    public static void printDoubleLinkedList(Node head) {
        System.out.print("Double Linked List: ");
        Node end = null;
        while (head != null) {
            System.out.print(head.value + " ");
            end = head;
            head = head.right;
        }
        System.out.print("| ");
        while (end != null) {
            System.out.print(end.value + " ");
            end = end.left;
        }
        System.out.println();
    }

}

