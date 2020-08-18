package com.sparo.data.queue;

import com.sparo.data.Array;

/**
 * description: 数组队列
 * trait: 1 实现的数据结构为 数组 2 队列特性为 FIFO 即 先进先出 队尾添加 队首移出
 * Created by sdh on 2019-07-11
 */
public class ArrayQueue<E> implements Queue<E> { //1. 实现通用接口协议 声明行为能力

    private Array<E> array;

    //2. 数组队列使用数组实现，初始化数组
    public ArrayQueue(int capacity) {
        array = new Array<>(capacity);
    }

    public ArrayQueue() {
        array = new Array<>();
    }

    @Override
    public void enqueue(E e) {
        array.addLast(e);
    }

    @Override
    public E dequeue() {
        return array.removeFirst();
    }

    @Override
    public E getFront() {
        return array.getFirst();
    }

    @Override
    public int getSize() {
        return array.getSize();
    }

    @Override
    public boolean isEmpty() {
        return array.isEmpty();
    }

    public int getCapacity() {
        return array.getCapacity();
    }

    //根据建立的 动态数组 和 队列的特性


    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Queue::size::d%, capacity::d%/n", getSize(), getCapacity());
        builder.append("front [");
        int size = array.getSize();
        for (int i = 0; i < size ; i++) {
            builder.append(array.find(i));
            if(i<size-1) {
                builder.append(",");
            }
        }
        builder.append("] end");
        return builder.toString();
    }
}
