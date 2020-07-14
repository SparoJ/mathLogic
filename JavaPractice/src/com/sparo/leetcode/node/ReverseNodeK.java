package com.sparo.leetcode.node;

/**
 * description:
 * Created by sdh on 2020-07-14
 */
public class ReverseNodeK {

    public ListNode reverseKGroup(ListNode head, int k) {

        return rkg(head, k);
    }

    //思路：1.每k个一反转，相当于反转前k个后继续对后续链表做相同k个一反转逻辑，并依次到最后 即子问题逻辑相同 采用每k个的递减调用 2.每k个节点反转 转化为1-k/k+1-2k.. 区间反转问题，如果只是沿用旧有的链表反转前n个节点的逻辑可行？ 3.递归的base 在于如何判定当前递归传递的链表节点是否够k个节点，即满足反转k个节点的条件
    private ListNode rkg(ListNode head, int k) {
        //base case 保护！ ❌
        if(head == null) return head;
        int i = 0;
        //a/b 分别记录 当前递归层需要进行k个反转的 起始节点和 结束节点
        ListNode a = head;
        ListNode b = head;
        while(i<k) {
            // b = b.next;
            // b 为null 返回原有顺序链表的头节点 a
            if(b == null) return a;
            //❌ b=b.next的位置不能放在if前，否则相当于提前+1，此时i少了1，未达到终止判定条件 以head为头的当前链表是否有可以直接反转的k个节点，循环有两处终止条件，要么当前head为首的链表够k个，要么不够直接返回头节点
            b = b.next;
            i++;
        }
        //反转a/b区间的节点
        ListNode node = reverseAB(a, b);
        //递归后续k个节点,返回递归后的头节点
        ListNode kNode = rkg(b, k);
        //链接每次反转k个节点后的链表,将k个节点链表反转后的头节点和前续节点链接
        //此时 前k个反转的尾节点a 指向 后k个反转后的头部节点kNode
        a.next = kNode;
        //返回的是k个一组反转的结果，相当于第一个k链表节点反转后的头节点, 这里即是最终的头节点 也是 rkg递归调用返回的k一组头节点，用于和前续节点链接
        return node;
    }

    //反转从a到b 的前闭后开区间
    private ListNode reverseAB(ListNode a, ListNode b) {
        ListNode pre = null;
        ListNode cur = a;
        while(cur!=b) {
            ListNode temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
        }
        //最后 pre->链表头 cur->b 指向区间开节点
        return pre;
    }
}
