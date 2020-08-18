package com.sparo.leetcode.node;

/**
 * description: 反转从n个节点开始以后的链表
 * Created by sdh on 2020-07-14
 */
public class ReverseNodeIII {

    //反转链表最后n个节点
    private ListNode reverseLastN(ListNode head, int n) {
        int i = 1;
        ListNode pre = null;
        ListNode cur = head;
        // 从前到后遍历n-1次，当i==n时，i加了n-1次，head指向第n个节点
        while(i<n) {
            // 遍历到对应位置，记录前续节点
            pre = cur;
            // 遍历到对应位置，记录反转的起始节点
            cur = cur.next;
            // 用于记录当前遍历到的位置
            i++;
        }
        //反转从n到链表尾的节点
        ListNode node = reverseAll(cur);
        //将反转后的链表和前续节点链接，判null在于 n==1时 即反转整个链表并返回
        if(pre!=null) pre.next = node;
        return pre==null?node:head;
    }

    private ListNode reverseAll(ListNode head) {
        //遍历方式
        ListNode pre = null;
        ListNode cur = head;
        while(cur!=null) {
            ListNode temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
        }
        return pre;
    }
}
