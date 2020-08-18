package com.sparo.test;

import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Lock_Condition_ABC {
    private static Lock lock = new ReentrantLock();
    private static Condition A = lock.newCondition();
    private static Condition B = lock.newCondition();
    private static Condition C = lock.newCondition();

    private static int count = 0;

    //········································final 测试（final 变量允许赋值的地方）··················································
    static {
        numT = 3; //T2
    }

    {
        num = 2; //A2
    }

    private final int num; // A1 need initial final 类/实例环境的final 变量允许位置 A1/2/3
      private final static int numT; // T1/T2 允许初始化
    public Lock_Condition_ABC() {
//        num = 1; // A3 非static final 可行
//        numT = 2; // static 不行
    }
//
//    public void testFinalVarable() {
//        num = 2;
//    }
//········································final 测试··················································

    public static void main(String[] args) throws InterruptedException {
//        new ThreadA().start();
//        new ThreadB().start();
//        new ThreadC().start();
        // 需要提前初始化 condition 给对应的 thread 使用，用于暂停当前线程和 唤醒下一个线程
        // （如果直接在thread循环里创建可能出现前面的thread 已经跑到要调用下一轮的threadCondition 唤醒的时候了，
        // 该对象还未得到初始化）
        for(int i = 0; i<arr.length; i++) {
            Condition condition = lock.newCondition();
            conds[i] = condition;
        }
        //
        for (int i = 0; i < arr.length; i++) {
            Thread thread = new ThreadS(i);
            thread.setName("thread:" + i);
            thread.start();
        }
    }

    static String[] arr = {"A", "B", "C"};
    static Condition[] conds = new Condition[arr.length];

    static class ThreadS extends Thread {
        //标记当前是哪个线程，以及用该标记确认当前对应需要打印的字符串 比如0 线程打印A，1线程打印B，2线程打印C
        private int type;
        public ThreadS(int type) {
            this.type = type;
        }

        // 用变量控制打印count位置
        @Override
        public void run() {
            try {
                lock.lock();
                 while(count<100) {
//                for (int i = 0; i < 10; i++) {
                     // 🚩 改if为while 防止 虚假唤醒
                    while (count % 3 != type){//注意这里是不等于0，也就是说没轮到该线程执行，之前一直等待状态
                        conds[type].await(); //该线程A将会释放lock锁，构造成节点加入等待队列并进入等待状态
                    }
                    //🚩 添加count边界保护
                    if(count>100) return;
                    System.out.println("element:" + arr[type] + "->name:" + Thread.currentThread().getName() + "->count:" + count);
                    count++;
                    conds[count%3].signal(); // A执行完唤醒B线程
//                }
                 }

            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        /**
         * 步骤同 两个 Thread 使用 wait notify + Synchronized 类似，同步+wait+notify
         */
//        @Override
//        public void run() {
//            try {
//                lock.lock();
//
//                for (int i = 0; i < 10; i++) {
//                    while (count % 3 != type){//注意这里是不等于0，也就是说没轮到该线程执行，之前一直等待状态
//                        conds[type].await(); //该线程A将会释放lock锁，构造成节点加入等待队列并进入等待状态
//                    }
//                    System.out.println("element:" + arr[type] + "->name:" + Thread.currentThread().getName());
//                    count++;
//                    conds[count%3].signal(); // A执行完唤醒B线程
//                }
//
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            } finally {
//                lock.unlock();
//            }
//        }
    }

    static class ThreadA extends Thread {
        @Override
        public void run() {
            try {
                lock.lock();

                for (int i = 0; i < 10; i++) {
                    while (count % 3 != 0){//注意这里是不等于0，也就是说没轮到该线程执行，之前一直等待状态
                        A.await(); //该线程A将会释放lock锁，构造成节点加入等待队列并进入等待状态
                    }
                    System.out.print("A");
                    count++;
                    B.signal(); // A执行完唤醒B线程
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    static class ThreadB extends Thread {
        @Override
        public void run() {
            try {
                lock.lock();
                for (int i = 0; i < 10; i++) {
                    while (count % 3 != 1)
                        B.await();// B释放lock锁，当前面A线程执行后会通过B.signal()唤醒该线程
                    System.out.print("B");
                    count++;
                    C.signal();// B执行完唤醒C线程
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    static class ThreadC extends Thread {
        @Override
        public void run() {
            try {
                lock.lock();
                for (int i = 0; i < 10; i++) {
                    while (count % 3 != 2)
                        C.await();// C释放lock锁
                    System.out.print("C");
                    count++;
                    A.signal();// C执行完唤醒A线程
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    public <T> void copy(List<? super T> dsc, List<? extends T> src)  {

    }

}