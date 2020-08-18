package com.sparo.produce.standard;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * description: factory to create consume&produce
 * Created by sdh on 2020-08-07
 */
public class BlockQueueModel implements Model<Task> {

    private LinkedBlockingQueue<Task> queue;
    private AtomicInteger ai = new AtomicInteger(0);
    public BlockQueueModel(int cap) {
        queue = new LinkedBlockingQueue<>(cap);
    }

    @Override
    public Runnable createProduce() {
        return new ProduceImpl();
    }

    @Override
    public Runnable createConsume() {
        return new ConsumeImpl();
    }

    private class ConsumeImpl extends AbstractConsume {

        @Override
        public void consume() {
            //queue 的 poll实现和 take 不一样，差异在于前者没有在阻塞 后者后 下面的 生产者类似
//           Task task = queue.poll();
            try {
                Task task = queue.take();
                System.out.println("Consume->" + task.no);
                // 固定时间范围的消费，模拟相对稳定的服务器处理过程
                Thread.sleep(500 + (long) (Math.random() * 500));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private class ProduceImpl extends AbstractProduce {

        @Override
        public void produce() {
            Task task = new Task();
            try {
            Thread.sleep((long) (Math.random() * 1000));
            task.no = ai.getAndIncrement();
            //queue.offer 是普通队列使用 需要使用带阻塞的api take/put 方法队

                System.out.println("Produce->" + task.no);
                queue.put(task);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args) {

        //分别设置 并发处理容器容量为3，生产和消费线程分别 为 2/5
        BlockQueueModel model = new BlockQueueModel(3);
        //使用线程传入 runnable
        for (int i = 0; i < 2; i++) {
            new Thread(model.createProduce()).start();
        }

        for (int i = 0; i < 5; i++) {
            new Thread(model.createConsume()).start();
        }
    }
}
