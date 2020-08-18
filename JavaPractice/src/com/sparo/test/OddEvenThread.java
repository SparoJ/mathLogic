package com.sparo.test;

public class OddEvenThread {
    private static volatile Integer counter = 0;
    private static Object monitor = new Object();

    public static void main(String[] args) {
        new Thread(new Runnable() {
            // 奇数线程
            @Override
            public void run() {
                while (true){
                    synchronized (monitor){
                        if (counter % 2 != 0){
                            continue;
                        }
                        int i = ++counter;
                        if (i > 100){
                            return;
                        }
                        System.out.println("奇数线程："  + i);
                        try {
                            monitor.notify();
                            monitor.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    synchronized (monitor){
                        if (counter % 2 == 0){
                            continue;
                        }
                        int i = ++counter;
                        if (i > 100){
                            return;
                        }
                        System.out.println("偶数线程："  + i);
                        try {
                            monitor.notify();
                            monitor.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();


    }
}