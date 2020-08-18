package com.sparo.data;

import com.sparo.data.queue.ArrayQueue;

/**
 * description:
 * Created by sdh on 2019-07-11
 */
public class TestMain {

    public static void main(String[] args) {

//        removeArrItem();
        enqueue2Deque();
    }

    private static void removeArrItem() {

        Array<Integer> intArr  = new Array<>(4);
        for (int i = 0; i < 20; i++) {
            intArr.addLast(i);
        }
        printString("before remove --->>>" + intArr);
        for (int i = 0; i < intArr.getSize(); i++) {
            if (i%3 == 0) {
                intArr.removeElement(i);
                printString("after remove --->>>" + intArr);
            }
        }
    }

    private static void enqueue2Deque() {

        ArrayQueue<Integer> arrayQueue = new ArrayQueue<>();
        for (int i = 0; i < 20; i++) {
            arrayQueue.enqueue(i);
        }
        printString("before dequeue::" + arrayQueue.toString() +"::size::"+ arrayQueue.getSize());
        int size = arrayQueue.getSize();
        for (int i = 0; i <size ; i++) {
            Integer dequeue = arrayQueue.dequeue();
            printString("dequeue current::" + dequeue);
        }
        printString("after dequeue::" + arrayQueue.toString());
    }


    private static void printString(Object obj) {
        System.out.println(obj.toString());
    }

}
