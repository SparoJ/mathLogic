package com.sparo.math;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * description:
 * Created by sdh on 2019/4/18
 */
public class ConcurrentTest {

    String[] testArr = new String[]{"arr", "test", "arraylist", "linkedlist"};

    public static void main (String[] args) {
        ConcurrentTest test = new ConcurrentTest();
        try {
//            test.testArrayList();
//            test.testLinkedList();
            test.testLinkedForList();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void testArrayList() {
        ArrayList<String> list = new ArrayList<>(Arrays.asList(testArr));
        for(String item : list) {
            list.remove("arr");
        }
    }

    public void testLinkedList() {
        LinkedList<String> list = new LinkedList<>(Arrays.asList(testArr));
        for(String item : list) {
            list.remove("test");
        }
    }

    public void testLinkedForList() {
        LinkedList<String> list = new LinkedList<>(Arrays.asList(testArr));
        for (int i = 0; i < list.size(); i++) {
            Iterator<String> iterator = list.iterator();
            if (iterator.hasNext()) {
                iterator.next();
                iterator.remove();
            }

        }
    }

}
