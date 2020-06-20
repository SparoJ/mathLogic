package com.sparo.produce;

import java.util.LinkedList;
import java.util.Queue;

/**
 * description:
 * Created by sdh on 2019-12-10
 */
public class ProduceConsume {

    public static void main(String[] args) {
        /**
         * 使用容器和 设定count 还有 同步wait/notify实现 生产消费模式
         */
        Queue<IProduct> queue = new LinkedList<>();
        int maxCount = 5;
        for (int i = 0; i < 3; i++) {
            Producer producer = new Producer("produceThread" + i, queue, maxCount);
            producer.start();
        }

        for (int i = 0; i < 2; i++) {
            Consumer consumer = new Consumer("consumerThread" + i, queue);
            consumer.start();
        }

    }

    // 生产者
    public static class Producer extends Thread {


        private int maxCount;
        private Queue<IProduct> queue;

        public Producer(String threadName, Queue queue, int maxCount) {
            this.setName(threadName);
            this.queue = queue;
            this.maxCount = maxCount;
        }

        @Override
        public void run() {
            try {
                while(true) {
                   synchronized (queue) {
                        while(queue.size() == maxCount) {
                            queue.wait();
                        }
                       Product product = new Product();
                       queue.add(product);
                       queue.notifyAll();
                       System.out.println("produce----size::" + queue.size() + "name---" + getName());
                   }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // 消费者
    public static class Consumer extends Thread {

        private Queue<IProduct> queue;

        public Consumer(String name, Queue<IProduct> queue) {
            setName(name);
            this.queue = queue;
        }

        @Override
        public void run() {
            try {
                while(true) {
                    synchronized (queue) {
                        while(queue.isEmpty()) {
                            queue.wait();
                        }
                        queue.remove();
                        queue.notifyAll();
                        System.out.println("consume----size::" + queue.size() + "name---" + getName());
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static class Product implements IProduct{

    }

    public interface IProduct {

    }
}

