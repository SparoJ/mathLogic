package com.sparo.data.node;

import com.sparo.data.queue.Queue;

/**
 * description: use LinkedList to implement queue
 * <p>
 * 1& we dont need to use dummyHead here
 * 2& to find out which side to add element and which side to remove element
 * so that we can satisfy the feature of the queue and ensure the the complex of the algorithm at a relative low state
 * <p>
 * Created by sdh on 2019-07-18
 */
public class LinkedListQueue<E> implements Queue<E> {

    public LinkedListQueue() {
        head = null;
        tail = null;
        size = 0;
    }

    /**
     * 链表头
     */
    public Node head;
    /**
     * 链表尾
     */
    public Node tail;
    /**
     * 链表size
     */
    public int size;


//    public static class Node {
      public class Node { //we dont need to use static to modify the class type

        public E e;

        Node next;

        public Node() {
            this(null);
        }

        public Node(E e) {
            this(e, null);
        }

        public Node(E e, Node next) {
            this.e = e;
            this.next = next;
        }

        @Override
        public String toString() {
            if (e != null) {
                return e.toString();
            }
            return null;
        }
    }

    /**
     * add element at the tail
     *
     * @param e element
     */
    @Override
    public void enqueue(E e) {
//        head = new Node(e, head); add element to the front position
        if (tail == null) { //means the queue is empty
            head = tail = new Node(e);

        } else {
            Node cur = new Node(e);
            tail.next = cur;
            tail = cur;
        }
        size++;
    }

    /**
     * remove element at the front
     *
     * @return
     */
    @Override
    public E dequeue() {
        if (head == null) {
            throw new IllegalArgumentException("the queue is empty");
        }
        // local variable
        Node cur = head;
        head = cur.next;
        // u need to cut the link(set the next node to null)!!!! if u want to remove an element from a list
        cur.next = null;
        // remove to the last element
        if(head == null){
            tail =null;
        }
        size --;
        return cur.e;
    }

    @Override
    public E getFront() {
        return head == null ? null :  head.e;
    }

//    public E getLast() {
//
//    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("QUEUE:FRONT:-->>");
        Node cur = head;
        for (int i = 0; i < size; i++) {
            builder.append(cur).append("-->>");
            cur = cur.next;
        }
            builder.append("TAIL!!"+ "head->" + head + "tail->" + tail);
        return builder.toString();
    }
}
