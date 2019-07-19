package com.sparo.data.node;

import com.sparo.util.Utils;

/**
 * description:
 * Created by sdh on 2019-07-19
 */
public class ListTest {

    public static void main(String[] args) {
//        testLinkedListStack();
//          testLinkedList();
        testLinkedListQueue();
    }

    private static void testLinkedListQueue() {
        LinkedListQueue queue = new LinkedListQueue();
        for (int i = 0; i < 10; i++) {
            queue.enqueue("x:" + i );
        }
        Utils.println(queue);
        for (int i = 0; i < 5; i++) {
            queue.dequeue();
        }
        Utils.println(queue);
    }

    private static void testLinkedList() {
        LinkedList<String> list = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            list.addElement(0, "x+ "+i);
        }
        Utils.println(list);
    }

    private static void testLinkedListStack() {
        LinkedListStack listStack = new LinkedListStack();
        String str = "index->";
        for (int i = 0; i < 10; i++) {
            listStack.push(str + i);
        }
        Utils.println(listStack);
        for (int i = 0; i < 10; i++) {
            listStack.pop();
        }
        Utils.println(listStack);
    }
}
