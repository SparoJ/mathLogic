package com.sparo.produce.standard;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockConditionModel1 implements Model {
  private final Lock BUFFER_LOCK = new ReentrantLock();
  private final Condition BUFFER_COND = BUFFER_LOCK.newCondition();
  private final Queue<Task> buffer = new LinkedList<>();
  private final int cap;

  private final AtomicInteger increTaskNo = new AtomicInteger(0);

  public LockConditionModel1(int cap) {
    this.cap = cap;
  }

  @Override
  public Runnable createConsume() {
    return new ConsumerImpl();
  }

  @Override
  public Runnable createProduce() {
    return new ProducerImpl();
  }

  private class ConsumerImpl extends AbstractConsume implements Runnable {
    @Override
    public void consume() {

      try {
        BUFFER_LOCK.lockInterruptibly();
        while (buffer.size() == 0) {
          BUFFER_COND.await();
        }
        Task task = buffer.poll();
        assert task != null;
        // 固定时间范围的消费，模拟相对稳定的服务器处理过程
        Thread.sleep(500 + (long) (Math.random() * 500));
        System.out.println("consume: " + task.no + "->threadName->" + Thread.currentThread().getName());
        BUFFER_COND.signalAll();
      }  catch (InterruptedException e) {
        e.printStackTrace();
      }finally {
        BUFFER_LOCK.unlock();
      }
    }
  }

  private class ProducerImpl extends AbstractProduce implements Runnable {
    @Override
    public void produce(){
      // 不定期生产，模拟随机的用户请求

      try {
        Thread.sleep((long) (Math.random() * 1000));
        BUFFER_LOCK.lockInterruptibly();
        while (buffer.size() == cap) {
          BUFFER_COND.await();
        }
        Task task = new Task();
        task.no = increTaskNo.getAndIncrement();
        buffer.offer(task);
        System.out.println("produce: " + task.no + "->threadName->" + Thread.currentThread().getName());
        BUFFER_COND.signalAll();
      }  catch (InterruptedException e) {
        e.printStackTrace();
      }finally {
        BUFFER_LOCK.unlock();
      }
    }
  }

  public static void main(String[] args) {
    Model model = new LockConditionModel1(3);
    for (int i = 0; i < 2; i++) {
      new Thread(model.createConsume(), "consumeThread:" + i).start();
    }
    for (int i = 0; i < 5; i++) {
      new Thread(model.createProduce(), "produceThread:" + i).start();
    }
  }
}