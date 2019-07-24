package com.sparo.data.tree;

import com.sparo.util.Utils;

/**
 * description:
 * Created by sdh on 2019-07-24
 */
public class TreeTest {

    public static void main(String[] args) {
        BST<Integer> bst = new BST<>();

        int [] arr = {6, 4, 5, 7 , 8 , 3 , 2, 1, 9};
        for (int i = 0; i < arr.length; i++) {
            bst.add(arr[i]);
            //check
            Utils.println(bst.contains(arr[i]));
        }
        //遍历 toString
        Utils.println(bst.toString());

        Utils.println("---------------------------");

//        bst.midOrder();
//
//        bst.laterOrder();

        bst.noTR();

        bst.leverOrder();

    }
}
