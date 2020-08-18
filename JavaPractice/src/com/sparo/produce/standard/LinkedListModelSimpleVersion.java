package com.sparo.produce.standard;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * description:
 * Created by sdh on 2020-08-08
 */
public class LinkedListModelSimpleVersion implements Model<Task> {


    private final Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();
    private int capacity;
    private AtomicInteger nom = new AtomicInteger();
    private Queue<Task> queue = new LinkedList<>();

    public LinkedListModelSimpleVersion(int cap) {
        capacity = cap;
    }

    @Override
    public Runnable createProduce() {
        return new ProduceImpl();
    }

    @Override
    public Runnable createConsume() {
        return new ConsumeImpl();
    }

    private class ProduceImpl extends AbstractProduce {

        @Override
        public void produce() {
            try {
                Thread.sleep((long) (Math.random() * 1000));
//                final AtomicInteger length = len;
                lock.lockInterruptibly();
                while (queue.size() == capacity) {
//                    System.out.println("produce full:");
                    condition.await();
                }
                Task task = new Task();
                task.no = nom.getAndIncrement();
                queue.offer(task);
                System.out.println("produce:" + task.no + "->name:" + Thread.currentThread().getName());
                    condition.signalAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    private class ConsumeImpl extends AbstractConsume {

        @Override
        public void consume() {
            try {
                lock.lockInterruptibly();
                while(queue.size() == 0) {
//                    System.out.println("consume empty:");
                    condition.await();
                }
                Thread.sleep(500 + (long) (Math.random() * 500));
                Task task = queue.poll();
                System.out.println("consume:" + task.no + "->name:" + Thread.currentThread().getName());
                    condition.signalAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        LinkedListModelSimpleVersion model = new LinkedListModelSimpleVersion(5);
        for (int i = 0; i < 2; i++) {
            Thread thread = new Thread(model.createConsume(), "->consumeThread:"+i);
            thread.start();
        }
        for (int i = 0; i < 5; i++) {
            Thread thread = new Thread(model.createProduce(), "->produceThread:" + i);
            thread.start();
        }

    }

}
