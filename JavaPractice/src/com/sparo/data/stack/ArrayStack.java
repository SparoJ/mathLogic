package com.sparo.data.stack;

import java.util.ArrayList;

/**
 * description: use dynamic ArrayList to implement the Stack interface
 * Created by sdh on 2019-07-17
 */
public class ArrayStack<E> implements Stack<E> {

    //同样可以使用前面自行实现的 动态数组完成 以数组为基础数据结构的 stack
    private ArrayList list;

    public ArrayStack(int capacity) {
        list = new ArrayList(capacity);
    }

    public ArrayStack() {
        list = new ArrayList();
    }

    @Override
    public void push(E e) {
        list.add(list.size(), e);
    }

    @Override
    public E pop() {
        return (E) list.remove(getSize()-1);
    }

    @Override
    public E peek() {
        return (E) list.get(getSize()-1);
    }

    @Override
    public int getSize() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }
}
