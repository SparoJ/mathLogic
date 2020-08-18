package com.sparo.data.node;

/**
 * description: implementation of LinkedList
 *
 * note: the complex of the algorithm:
 * addFirst/removeFirst o(1)  if u operate the front element the complex of the algorithm is o(1), otherwise its o(n)
 * Created by sdh on 2019-07-17
 */
public class LinkedList<E> {


    public static class Node<E> {
        private E e;
        //引用指向下一个节点
        private Node next;

        public Node(E e) {
            this(e, null);
        }

        public Node(E e, Node next) {
            this.e = e;
            this.next = next;
        }

        public Node() {
            this(null, null);
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
     * 头节点 head
     */
    @Deprecated
    private Node head;

    private Node dummyHead;

    /**
     * 链表 size
     */
    private int size;

    public LinkedList() {
//        head = null;
        dummyHead = new Node();
        size = 0;
    }

    /**
     * @return the size of the list
     */
    public int getSize() {
        return size;
    }

    //add element to index position
    public void addElement(int index, E e) {
        if(index < 0 || index > size) {
            throw new IllegalArgumentException("the index you want to add the element is illegal");
        }
        //we use dummy head to optimize the process @Deprecated
//        if (size == 0) {
//            head = new Node(e);
//            size ++;
//            return;
//        }

        // traverse to find the previous element
//        Node pre = head;
        Node pre = dummyHead;
        for (int i = 0; i < index; i++) { //if we didnt use the dummyHead, the for circle will end at (i < index-1)
            pre = pre.next;
        }
        //add the element to index position
//        Node temp = new Node(e);
//        Node cur = pre.next;
//        pre.next = temp;
//        temp.next = cur;
        pre.next = new Node(e, pre.next); // equals to these sentence above
        size ++;
    }

    /**
     * add element to the first of the linkedList
     * @param e
     */
    public void addFirst(E e) {
        addElement(0, e);
    }

    /**
     * add element to the end of the linkedList
     * @param e
     */
    public void addLast(E e) {
        addElement(size, e);
    }

//    public E findElement(int index) {
//        if (size == 0) {
//            return null;
//        }
//        if(index < 0 || index>= size) {
//            throw new IllegalArgumentException("the index is out of boundary");
//        }
//        Node cur = head;
//        for (int i = 0; i < index; i++) {
//            cur = cur.next;
//        }
//        return (E) cur.e;
//    }


    /**
     * contains
     * @return true/false
     */
    public boolean contains(E e) {
        if (isEmpty()) {
            return false;
        }
        Node cur = dummyHead;
        while(cur.next!=null && cur.next.e!=null) {
            if(cur.next.e.equals(e)) {
                return true;
            }
        }
        return false;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    //delete

    //set

    /**
     * set the e to the index position
     * @param index
     * @param e
     */
    public void setElement(int index, E e) {
        E element = getElement(index);
        element = e;
    }

    //get

    /**
     * get element at index
     * @param index
     * @return E e
     */
    public E getElement(int index) {
        if(index < 0 || index>=size) {
            throw new IllegalArgumentException("index is out of boundary");
        }
//        Node cur = dummyHead;
//        for (int i = 0; i < index+1; i++) {
//            cur = cur.next;
//        }
        // find the head node of the list
        Node cur = dummyHead.next;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        return (E) cur.e;
    }

    /**
     *
     * @return get element at first
     */
    public E getFirst() {
        return getElement(0);
    }

    /**
     *
     * @return get element af last
     */
    public E getLast() {
        return getElement(size-1);
    }

    /**
     *
     * @param index
     * @return remove the element at index and return
     */
    public E removeElement(int index) {
        if(index<0 || index>=size) {
            throw new IllegalArgumentException("index is out of boundary");
        }
        Node pre = dummyHead;
        for (int i = 0; i < index; i++) {
            pre = pre.next;
        }
        Node del = pre.next;
        pre.next = del.next;
        //we only need to reset the next node of the element u want to remove to null, to save space
        del.next = null;
        size --;
        return (E) del.e;
    }

    @Override
    public String toString() {

        StringBuilder builder = new StringBuilder();
        builder.append("NODE->HEAD>>");
        Node cur = dummyHead.next;
        for (int i = 0; i < size; i++) {
            builder.append(cur);  //Node 中已经 toString了所以不需要cur.e
            //不需要单独处理size-1 因为最后一段需要这个标识，所以去掉
//            if (i!=size-1) {
                builder.append("-->>");
//            }
            // u just forget this !!!
            cur = cur.next;
        }
        // 第二种 写法 ：：
//        while(cur.next!=null) {
//            builder.append(cur);
//            cur = cur.next;
//        }
        builder.append("TAIL");
        return builder.toString();
    }
}
