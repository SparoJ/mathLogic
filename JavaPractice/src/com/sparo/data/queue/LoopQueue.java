package com.sparo.data.queue;

/**
 * description: 循环数组构建的队列
 * 为什么会有循环队列：因为 动态数组构建的队列，每次 dequeue 都是从头开始移除，移除的同时后面每一个i+1的元素都要向前移动一位，
 * 复杂度对应为 O(n); 既然是因为向前移动导致的复杂度增加如何降低复杂度？-> 维护对应的指针指向队首的元素而不因为每次从队首移除元素后、
 * 产生向前移动的行为，这样整个队列间接变成了circle 首尾相连【tail 指向最后一个元素的后一个位置， tail的作用是为了描述当前队列的状态，
 * tail == front 时 queue is empty， tail+1 == front 时， queue is full, 这里tail 会浪费一个 space，所以需要在维护队列时注意size问题
 * 因为queue 变成了circle 的结构（理解层面）， tail可能比front小 故维护时注意 %length, 此外仍然需要关注 动态扩容的问题 】
 * Created by sdh on 2019-07-11
 *
 * 此外 队列需要满足：FIFO的特性，所以从队首出队，从队尾入队
 */
public class LoopQueue<E> implements Queue<E> {

    /**
     * 动态循环数组的头和尾 position
     */
    private int front, tail;
    /**
     * 当前队列中元素个数
     */
    private int size;
    private E[] data;

    public LoopQueue(int capactiy) {
        data = (E[]) new Object[capactiy+1]; //we will waste one space so we need to add one
        size = front = tail = 0;
    }
    //default size
    public LoopQueue() {
        this(10);
    }

    /**
     * @param e element add an element to the end of the queue
     */
    @Override
    public void enqueue(E e) {

        //when (tail+1)%data.length equals to front ,which means that the loop queue is full
        if((tail+1)%data.length == front)
            resizeLoopQueue(getCapacity()*2);

            data[tail] = e;//tail 指向的是要要入队的元素【添加】当前最后一个元素是
            tail = (tail+1) % data.length;
            //出队时才会+1
//            front = (front+1) % data.length;
            size++;

    }

    private void resizeLoopQueue(int capacity) {
        E[] e = (E[]) new Object[capacity+1];
        for (int i = 0; i < size; i++) {
            e[i] = data[(i+front)%data.length];
        }
        data = e;
        front = 0;
        tail = size;
    }

    /**
     *
     * @return remove the front element of the queue and return
     */
    @Override
    public E dequeue() {
        //dequeue element from front
        E frontElement = getFront();// getFront()
        front = (front+1)%data.length;
        size--;

        /**when the size of the queue decrease and equals to the one fourth of the
         * capacity ,we need to resize the queue to the original size to save space
         */
        if(size == getCapacity()/4){
            resizeLoopQueue(getCapacity()/2);
        }
        return frontElement;
    }

    /**
     *
     * @return the front element of the queue without remove action
     */
    @Override
    public E getFront() {
        //获取第一个元素需要判断当前是否有element
        if (size == 0) {
            throw new IllegalArgumentException("the queue is empty, u cant obtain the front element");
        }
        return data[front];
    }

    @Override
    public int getSize() {
        return size;
    }

    //we already waste one element space
    public int getCapacity() {
        return data.length - 1;
    }

    @Override
    public boolean isEmpty() {
        return front == tail;
    }
}
