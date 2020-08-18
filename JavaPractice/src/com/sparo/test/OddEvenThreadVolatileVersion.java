package com.sparo.test;

/**
 * Created by Administrator on 2019/7/20.
 */
public class OddEvenThreadVolatileVersion {
    private static volatile  boolean loopForOdd = true;

    private static volatile  boolean loopForEven = true;

    private static volatile int counter = 1;

    public static void main(String[] args) throws InterruptedException {
        new Thread(new Runnable() {

            // 奇数线程
            @Override
            public void run() {
                while (true) {
                    while (loopForOdd){

                    }

//                    int counter = OddEvenThreadVolatileVersion.counter;
                    if (counter > 100) {
                        break;
                    }
                    System.out.println("奇数线程：" + counter);

                    OddEvenThreadVolatileVersion.counter++;

                    // 修改volatile，通知偶数线程停止循环，同时，准备让自己陷入循环
                    loopForEven = false;

                    loopForOdd = true;

                }

            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    while (loopForEven) {

                    }

//                    int counter = OddEvenThreadVolatileVersion.counter;
                    if (counter > 100) {
                        break;
                    }
                    System.out.println("偶数线程：" + counter);

                    OddEvenThreadVolatileVersion.counter++;

                    // 修改volatile，通知奇数线程停止循环,同时，准备让自己陷入循环
                    loopForOdd = false;

                    loopForEven = true;
                }
            }
        }).start();

        // 先启动奇数线程
        loopForOdd = false;

    }
}