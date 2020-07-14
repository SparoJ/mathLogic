package com.sparo.leetcode.node;

/**
 * description: 92. 反转链表 II
 * 反转从位置 m 到 n 的链表。请使用一趟扫描完成反转。
 *
 * 说明:
 * 1 ≤ m ≤ n ≤ 链表长度。
 *
 * 示例:
 *
 * 输入: 1->2->3->4->5->NULL, m = 2, n = 4
 * 输出: 1->4->3->2->5->NULL
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-linked-list-ii
 * Created by sdh on 2020-07-14
 */
public class ReverseNodeII {

    public ListNode reverseBetween(ListNode head, int m, int n) {

        return rb(head, m, n);
    }

    //思路：反转链表m-n，将该问题通过递归转化成反转前n个节点的问题

    private ListNode rb(ListNode head, int m,  int n) {
        if(m == 1) {
            //当m==1时，相当于递归到以当前head为首，反转之后n-m节点的状态，这里递归调用传递来的n为步减后的n-m值，直接转化为以head为首，反转从head 到 tail中的前n个节点
            return reverseN(head, n);
        }
        head.next = rb(head.next, m-1, n-1);
        return head;
    }

    //反转链表前n个节点

    ListNode successor = null;
    private ListNode reverseN(ListNode head, int n) {
        if(n == 1) {
            //记录第n个节点处的后继节点,作为反转后尾部节点的next节点
            successor = head.next;
            return head;
        }
        ListNode node = reverseN(head.next, n-1);
        head.next.next = head;
        //head.next每次都会被重置 对比全链表反转 head.next指向null同理
        head.next = successor;
        return node;
    }

    //完整II/III
//    public ListNode reverseBetween(ListNode head, int m, int n) {
//
//        // return reverseLastN(head, n);
//        // return reverseAll(head);
//        return reverseMN(head, m, n);
//    }
//
//    //反转从m到n的节点
//    private ListNode reverseMN(ListNode head, int m, int n) {
//        //递归到m==1时，即此时相当于反转剩下链表的前x个节点
//        if(m == 1) {
//            return reverseN(head, n);
//        }
//        // 递归并链接返回的头节点
//        ListNode node = reverseMN(head.next, m-1, n-1);
//        //未到达反转位置时，传入节点直接返回不影响前续节点的指向
//        head.next = node;
//        return head;
//    }

//    //反转链表前n个节点 关键点在于后继节点的链接时机
//    ListNode successor = null;
//    private ListNode reverseN(ListNode head, int n) {
//        if(n == 1) {
//            //找到需要反转节点中最后节点的后继节点作为反转后链表尾部的后继节点 同步反转整个链表的链表尾节点指向null的逻辑
//            successor = head.next;
//            return head;
//        }
//        //反转前n个节点返回的头节点 同步反转整个链表的返回头节点逻辑
//        ListNode node = reverseN(head.next, n-1);
//        //相邻链表节点之间的指针反转 只需要此行关键代码
//        head.next.next = head;
//        head.next = successor;
//        return node;
//    }
//
//    //反转链表最后n个节点
//    private ListNode reverseLastN(ListNode head, int n) {
//        int i = 1;
//        ListNode pre = null;
//        ListNode cur = head;
//        // 从前到后遍历n-1次，当i==n时，i加了n-1次，head指向第n个节点
//        while(i<n) {
//            // 遍历到对应位置，记录前续节点
//            pre = cur;
//            // 遍历到对应位置，记录反转的起始节点
//            cur = cur.next;
//            // 用于记录当前遍历到的位置
//            i++;
//        }
//        //反转从n到链表尾的节点
//        ListNode node = reverseAll(cur);
//        //将反转后的链表和前续节点链接，判null在于 n==1时 即反转整个链表并返回
//        if(pre!=null) pre.next = node;
//        return pre==null?node:head;
//    }
//
//    private ListNode reverseAll(ListNode head) {
//        //遍历方式
//        ListNode pre = null;
//        ListNode cur = head;
//        while(cur!=null) {
//            ListNode temp = cur.next;
//            cur.next = pre;
//            pre = cur;
//            cur = temp;
//        }
//        return pre;
//    }
}
