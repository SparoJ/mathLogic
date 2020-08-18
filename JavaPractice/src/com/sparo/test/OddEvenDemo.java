package com.sparo.test;

public class OddEvenDemo {
    private static volatile int number = 0;

    private static void logI(String tag, int str) {
        System.out.println(tag + str);}

    public static void main(String[] args) {
        final Object monitor = new Object();

        /**
         * 奇数线程
         */
        Runnable callable = new Runnable() {

            @Override
            public void run() {
                while (true) {
                    boolean interrupted = Thread.currentThread().isInterrupted();
                    if (interrupted) {
                        break;
                    }
                    synchronized (monitor) {
                        logI("beforeOdd:" , number);
                        while (number % 2 == 0) {
                            try {
                                logI("OddWaited:" , number);
                                monitor.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        logI("奇数线程, number:{}", number);
                        number++;
                        if(number>=100) return;
                        logI("afterOdd:" , number);
                        monitor.notify();
                    }

                    try {
                        Thread.sleep(0);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        Thread thread1 = new Thread(callable);
        thread1.setName("odd");
        thread1.start();

        /**
         * 偶数线程
         */
        Runnable evenCallable = new Runnable() {

            @Override
            public void run() {
                while (true) {
                    boolean interrupted = Thread.currentThread().isInterrupted();
                    if (interrupted) {
                        break;
                    }

                    synchronized (monitor) {
                        logI("beforeEven:" , number);
                        if (number % 2 != 0) {
                            try {
                                logI("EvenWaited:" , number);
                                monitor.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        logI("偶数线程, number:{}", number);
                        number++;
                        if(number>=100) return;
                        logI("afterEven:" , number);
                        monitor.notify();
                    }


                    try {
                        Thread.sleep(0);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        Thread thread = new Thread(evenCallable);
        thread.setName("even");
        thread.start();


    }
}