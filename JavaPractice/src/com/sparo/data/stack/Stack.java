package com.sparo.data.stack;

/**
 * description: combine the function of stack
 * trait: FILO first in last out
 * Created by sdh on 2019-07-17
 */
public interface Stack<E> {

    void push(E e);

    E pop();

    E peek();

    int getSize();

    boolean isEmpty();
}
