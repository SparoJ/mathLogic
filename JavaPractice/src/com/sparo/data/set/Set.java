package com.sparo.data.set;

/**
 * description: 抽象行为接口
 * Created by sdh on 2019-07-26
 */
public interface Set<E> {

    void add(E e);

    void remove(E e);

    boolean contains(E e);

    int getSize();

    boolean isEmpty();
}
