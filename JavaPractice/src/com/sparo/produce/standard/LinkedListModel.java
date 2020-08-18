package com.sparo.produce.standard;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * description: exception version
 * Created by sdh on 2020-08-07
 */
public class LinkedListModel implements Model<Task>{

    Queue<Task> queue;
    int capacity;
    //记录任务task no
    private AtomicInteger nom = new AtomicInteger(0);
    //记录容器size
    private AtomicInteger len = new AtomicInteger(0);
    public LinkedListModel(int cap) {
        queue = new LinkedList<>();
        capacity = cap;
    }
    //使用lock signal await实现 && 高吞吐量的并发容器，
    // 一端save 和 一端移除逻辑可以并行执行 分别创建对应的lock和condition
    ReentrantLock CONSUME_LOCK = new ReentrantLock();
    Condition NOT_EMPTY_COND = CONSUME_LOCK.newCondition();
    ReentrantLock PRODUCE_LOCK = new ReentrantLock();
    Condition NOT_FULL_COND = PRODUCE_LOCK.newCondition();

    @Override
    public Runnable createProduce() {
        return new ProduceImpl();
    }

    @Override
    public Runnable createConsume() {
        return new ConsumeImpl();
    }

    public class ConsumeImpl extends AbstractConsume {

        @Override
        public void consume() {
            int queenLen = -1;
            try {
                CONSUME_LOCK.lockInterruptibly();
                System.out.println("size:" + queue.size());
//                while(queue.size() == 0) {
                while(len.get() == 0) {
                    NOT_EMPTY_COND.await();
                }
                //使用while而不是用if的原因在于 if存在 虚假唤醒的可能，多个线程同时操作 当前await后，其他线程操作+1 再-1 仍然为0 这时候再去唤醒则
//                if(queue.size() == 0) {
//                    NOT_EMPTY_COND.await();
//                }
                Task task = queue.poll();
                assert task!=null;
                queenLen = len.decrementAndGet();
                System.out.println("consume:" + task.no);
                if(queenLen>0) {
                    NOT_EMPTY_COND.signalAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                CONSUME_LOCK.unlock();
            }
            // 不能直接使用 queue.size()
            if(queenLen<capacity) {
                try {
                    PRODUCE_LOCK.lockInterruptibly();
                    NOT_FULL_COND.signalAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    PRODUCE_LOCK.unlock();
                }
            }
        }
    }

    public class ProduceImpl extends AbstractProduce {

        @Override
        public void produce() {
            //在没有并发容器的前提下自行实现基于 linkedList的 并发queue生产消费逻辑
            //生产者 lock - 到达指定capacity后 await - unlock
            int queenLen = -1;
            try {
                PRODUCE_LOCK.lockInterruptibly();
                //while(queue.size()...
                while(len.get() == capacity) {
                    NOT_FULL_COND.await();
                }
                Task task = new Task();
                task.no = nom.getAndIncrement();
                queenLen = len.incrementAndGet();
                System.out.println("produce:" + task.no);
                queue.offer(task);
                if(queenLen<capacity) {
                    NOT_FULL_COND.signalAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                PRODUCE_LOCK.unlock();
            }
            //唤醒 另一个lock对象 ，因为这里使用了两个lock 故需要使用需要唤醒的lock对应的condition
            // && 且需要满足 size >0 才去唤醒 消费者线程
            if(queenLen>0) {
                try {
                    CONSUME_LOCK.lockInterruptibly();
                    NOT_EMPTY_COND.signalAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    CONSUME_LOCK.unlock();
                }
            }
        }
    }

    public static void main(String[] args) {
        LinkedListModel model = new LinkedListModel(3);
        for (int i = 0; i < 2; i++) {
            Thread thread = new Thread(model.createConsume(), "threadProduce:" + i);
            thread.start();
        }
        for (int i = 0; i < 5; i++) {
            Thread thread = new Thread(model.createProduce(), "threadConsume:" + i);
            thread.start();
        }
    }
}
