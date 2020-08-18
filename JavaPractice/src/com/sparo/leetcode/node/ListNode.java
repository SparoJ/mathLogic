package com.sparo.leetcode.node;

import com.sparo.util.Utils;

public class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }

     ListNode(int[] array) {
        //form list
         if (array == null || array.length==0) {
             return;
         }
         //初始化 链表头节点
         this.val = array[0];
         ListNode cur = this;
         //循环形成链表后续节点 从1开始，头节点已经初始化
         for (int i = 1; i < array.length; i++) {
             cur.next = new ListNode(array[i]);
             // 向下传递链条节点 即新的代码处理从下一个节点开始
             cur = cur.next;
         }

         //不用return this 构造生成的node 即为对应的链表
     }



    public static void main(String[] args) {

//        testDeleteRepeatNodeList
        //test3 {1, 2, 3, 3,4, 4, 5};
        //test2 {1, 1, 2, 3,3}
        // {1,1,1}
        int[] array = new int[]{1,1,1,2,3};
        ListNode node = new ListNode(array);
        Utils.println(node);
        ListNode listNode = new Solution().testElement(node, 0);
        Utils.println(listNode);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("NODE->HEAD->");
        ListNode cur = this;
        while(cur!=null){
            builder.append(cur.val).append("->>");
            cur = cur.next;
        }
        builder.append("NULL");
        return builder.toString();
    }
}