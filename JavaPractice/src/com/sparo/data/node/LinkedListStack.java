package com.sparo.data.node;

import com.sparo.data.stack.Stack;

/**
 * description: use list to implement stack
 * 使用链表去实现栈 入栈和出栈都在队首执行【复杂度o(1)】
 * Created by sdh on 2019-07-18
 */
public class LinkedListStack<E> implements Stack<E> {

    private final LinkedList<E> list;

    public LinkedListStack() {
        list = new LinkedList<>();
    }

    @Override
    public void push(E e) {
        list.addElement(0, e);
    }

    @Override
    public E pop() {
        return list.removeElement(0);
    }

    @Override
    public E peek() {
        return list.getElement(0);
    }

    @Override
    public int getSize() {
        return list.getSize();
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public String toString() {
        return list.toString();
    }
}
