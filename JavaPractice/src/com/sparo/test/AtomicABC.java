package com.sparo.test;

public class AtomicABC {
    private static volatile int count = 0;
    private static int MAX_COUNT = 3 * 10;
    private final static String[] strs = {"A", "B", "C"};
    private final static Object monitor = new Object();

    private static int threadCount = 2;
    public static void main(String[] args) {

        for (int i = 0; i < 10; i++) {
            new Thread(new Task(0)).start();
            new Thread(new Task(1)).start();
            new Thread(new Task(2)).start();
            try {
                Thread.sleep(2000);
                System.out.println("-------------------");
                count =0;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    private static class Task implements Runnable{
        private int type;
        public Task(int type) {
            this.type = type;
        }
        @Override
        public void run() {
//            while(true){
//                synchronized (monitor){
                    while(count < MAX_COUNT){
                        if(count % 3 == type && count < MAX_COUNT){
                            System.out.print(count + ":" + strs[type]+ " ");
//                            count.getAndIncrement();
                            count++;
                        }
//                    }
//                    else break;
                }
//            }
        }
    }
 
//    private static class Task implements Runnable{
//        private int type;
//        public Task(int type) {
//            this.type = type;
//        }
//        @Override
//        public void run() {
//            while(true){
//                synchronized (Task.this){
//                    if(count.get() < MAX_COUNT){
//                        if(count.get() % 3 == type){
//                            System.out.print(count.get() + ":" + strs[type]+ " ");
//                            count.getAndIncrement();
//                        }
//                    }
//                    else break;
//                }
//            }
//        }
//    }
}