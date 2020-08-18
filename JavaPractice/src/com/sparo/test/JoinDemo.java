package com.sparo.test;

public class JoinDemo extends Thread{
    int i;
    Thread previousThread; //上一个线程
    public JoinDemo(Thread previousThread,int i){
        this.previousThread=previousThread;
        this.i=i;
    }
    @Override
    public void run() {
        try {
          //调用上一个线程的join方法，大家可以自己演示的时候可以把这行代码注释掉
            previousThread.join();
//            Thread.sleep(200);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("num:"+i +"->currentThread->"+ Thread.currentThread());
    }
    public static void main(String[] args) {
        Thread previousThread=Thread.currentThread();
        for(int i=0;i<10;i++){
            System.out.println("previousThread->" + previousThread);
            JoinDemo joinDemo=new JoinDemo(previousThread,i);
            joinDemo.start();
//            try {
//                joinDemo.join();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            previousThread=joinDemo;
        }
        System.out.println("mainThread->" + Thread.currentThread());
    }
}