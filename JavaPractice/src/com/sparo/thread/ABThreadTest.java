package com.sparo.thread;

/**
 * description: 如何让 threadA 和 threadB 依次顺序累加打印 1->100的数字
 * Created by sdh on 2019-12-01
 */
public class ABThreadTest {
    public  int a = 0;
    private final Object monitor = new Object();
//    public static final Runnable RUNNABLE = new Runnable() {
//        public void run() {
//            a++;
//            System.out.println(a);
//        }
//    };

    public static void main(String[] args) {
//        testErrorMethod();
        ABThreadTest test = new ABThreadTest();
        test.testOddEven();
//        test.testMultiThread(2);
//        test. obtainStr();
    }

    public void obtainStr() {
        String str = "https://pan.ba\"Hello\"idu.c\"Hello\"om/s/1Ok\"Hello\"xQ1838Cz\"Hello\"pxIev\"Hello\"QhRUlrQ";
        String rs = str.replace("\"Hello\"","");
        System.out.println(rs);
    }

    // 两个线程时能保证执行的顺序 且循环 但当大于2时不能
    public void testOddEven() {
        Thread threadA = new Thread(oddEvenRun, "偶数");
        Thread threadB = new Thread(oddEvenRun, "奇数");
        threadA.start();
        threadB.start();
    }

    /**
     * 如何唤醒指定的java 线程？
     * @param count
     */
    public void testMultiThread(int count) {
        for (int i = 0; i < count; i++) {
            Thread thread = new Thread(oddEvenRun, i+"-->>thread");
            thread.start();
        }
    }

    static String[] abcArr = {"A", "B", "C"};
    static int threadCount = 4;

    public Runnable oddEvenRun = new Runnable() {
        public void run() {
            try {
                while(a<=99) {
                    synchronized (monitor) {

                        monitor.notify();
//                        System.out.println("thread count number::" + (abcArr[(a-1)%abcArr.length]) + "-->>thread name::" + Thread.currentThread().getName());
                        System.out.println("thread count number::" + (a++) + "-->>thread name::" + Thread.currentThread().getName());
                        monitor.wait();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };



//    private static void testErrorMethod() {
//        Thread threadA = new Thread(RUNNABLE);
//        Thread threadB = new Thread(RUNNABLE);
//        try {
//            while (a<=99) {
//                /**
//                 * IllegalThreadStateException java 线程不能重复start 否则 抛出异常
//                 * if (threadStatus != 0)
//                 *             throw new IllegalThreadStateException();
//                 *
//                 *   解决： 即循环处理   start在循环外层，给thread 传入runnable
//                 */
//
//                threadA.start();
//                threadA.join();
//                threadB.start();
//                threadB.join();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }


}
