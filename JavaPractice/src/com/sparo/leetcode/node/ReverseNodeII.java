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
}
