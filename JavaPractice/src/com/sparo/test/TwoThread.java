package com.sparo.test;

import java.util.concurrent.atomic.AtomicInteger;

// 谢谢阅读
public class TwoThread {

    public static void main(String[] args) {

//        printArr = new int[threadCount];
//        for (int i = 0; i < threadCount; i++) {
//            printArr[i] = i;
//        }
        TwoThread thread = new TwoThread();
        for (int i = 0; i < threadCount; i++) {
            Thread threads = new Thread(thread.new Al(i));
            threads.setName("thread-" +i);
            threads.start();
//            try {
//                threads.join();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }

//        Thread thread1 = new Thread(thread.new Ji());
//        thread1.start();
//        Thread thread2 = new Thread(thread.new Ou());
//        thread2.start();
//        Thread thread3 = new Thread(thread.new Th());
//        thread3.start();
//        try {
//            thread1.join();
//            thread2.join();
//            thread3.join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

    private AtomicInteger num = new AtomicInteger(0);
    private volatile int count = 0;

    static int[] printArr = {0,1,2};
    static String[] abcArr = {"A", "B", "C"};
    private static final int threadCount = 2;

    class Al implements Runnable {
        private  int type;
        public Al(int type) {
            this.type = type;
        }

        @Override
        public void run() {
            while (count <= 100) {
                if (count % threadCount == type) {
                    System.out.println("AlN:" + count + "->type:" + type);
                    count++;
                }
            }
        }
        // 01----100 奇偶 print
//        @Override
//        public void run() {
//            while (num.get() <= 100) {
//                if (num.get() % threadCount == type) { //printArr
//                    System.out.println("Al:" + num + "->type:" + type);
//                    num.incrementAndGet();
//                }
//            }
//        }
//@Override
//public void run() {
//    while (num.get() <= 100) {
//        if (num.get() % threadCount == type) {
//            System.out.println("Al:" + num + "->element:" + abcArr[type] + "-type->" + type);
//            System.out.println("threadName->" + Thread.currentThread().getName());
//            num.incrementAndGet();
//        }
//    }
//}
    }

    class Ji implements Runnable {
        @Override
        public void run() {
            while (num.get() <= 100) {
                if (num.get() % 3 == 1) {
                    System.out.println("ji:" + num);
                    num.incrementAndGet();
                }
            }
        }
    }

    class Th implements Runnable {
        @Override
        public void run() {
            while (num.get() <= 100) {
                if (num.get() % 3 == 2) {
                    System.out.println("Th:" + num);
                    num.incrementAndGet();
                }
            }
        }
    }

    class Ou implements Runnable {
        @Override
        public void run() {
            while (num.get() <= 100) {
                if (num.get() % 3 == 0) {
                    System.out.println("Ou:" + num);
                    num.incrementAndGet();
                }
            }
        }
    }

//    public static void main(String[]   args){
//        TwoThread thread = new TwoThread();
//        Thread thread1 = new Thread(thread.new Ji());
//        Thread thread2 = new Thread(thread.new Ou());
//        thread1.start();
//        thread2.start();
////        try {
////            thread1.join();
////            thread2.join();
////        }catch (InterruptedException e){
////            e.printStackTrace();
////        }
//    }
//    private volatile int num = 1;
//    class Ji implements Runnable{
//        @Override
//        public void run() {
//            System.out.println( "ji1:"+num );
//            while( num <= 100 ){  // && num % 2 == 1
//                if( num % 2 == 1&&num<=100){
//                    System.out.println( "ji:"+num );
//                    num++;
//                }
//            }
//        }
//    }
//    class Ou implements Runnable{
//        @Override
//        public void run() {
//            System.out.println( "ou1:"+num );
//            while( num <= 100 ){ // && num % 2 == 0
//                if( num % 2 == 0 &&num<=100){
//                    System.out.println( "ou:"+num );
//                    num++;
//                }
//            }
//        }
//    }
}