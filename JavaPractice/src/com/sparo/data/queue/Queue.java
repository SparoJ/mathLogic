package com.sparo.data.queue;

/**
 * description: 队列接口
 * 提供 入队／出队／获取队首元素／队列大小／队是否为空
 * Created by sdh on 2019-07-16
 */
public interface Queue<E> {

    /**
     * 入队
     * @param e element
     */
     void enqueue(E e);

    /**
     * 出队
     */
     E dequeue();

    /**
     * @return 获取队首元素
     */
     E getFront();

    /**
     * @return 获取 队列size
     */
     int getSize();

    /**
     * @return 队列是否为空
     */
     boolean isEmpty();
}
