package com.sparo.thread;

/**
 * description: 多线程循环打印累加后的数值
 * Created by sdh on 2019-12-02
 */
public class MultiCycleThread implements Runnable {

    public static final Object LOCK = new Object();

    private static int current = 0;
    private int maxValue;
    private int threadNo;
    private int threadCount;


    /**
     *
     * @param threadNo 线程号
     * @param threadCount 线程数
     * @param maxValue
     */
    public MultiCycleThread(int threadNo, int threadCount, int maxValue) {
        this.threadNo = threadNo;
        this.threadCount = threadCount;
        this.maxValue = maxValue;
    }

    public void run() {
        while(true) {
            synchronized (LOCK) {
//                System.out.println("current" + current + "::threadNo-->>" + threadNo);
                while(current % threadCount != threadNo) {
                    if(current>maxValue) {
                        break;
                    }
                    try {
                        LOCK.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if(current>maxValue) {
                    break;
                }
                System.out.println("final-->>>" + "thread" + threadNo + "::" + current);
                current++;
                LOCK.notifyAll();
            }
        }

    }

    public static void main(String[] args) {
        int threadCount = 50;
        int max = 100;
        for (int i = 0; i < threadCount; i++) {
            new Thread(new MultiCycleThread(i, threadCount, max)).start();
        }
    }
}
