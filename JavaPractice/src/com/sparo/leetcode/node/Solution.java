package com.sparo.leetcode.node;

import com.sparo.util.Utils;

/**
 * 给定一个排序链表，删除所有重复的元素，使得每个元素只出现一次。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 1->1->2
 * 输出: 1->2
 * 示例 2:
 * <p>
 * 输入: 1->1->2->3->3
 * 输出: 1->2->3
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
class Solution {

    /**
     * 遍历
     *
     * @param head
     * @return
     */
    public ListNode deleteDuplicates(ListNode head) {
        //因为已经是经过排序后的链表 所以不需要考虑跳过某个节点之后的判断场景 使用while
        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;
        ListNode pre = dummyHead;
        while (pre.next != null) {
            if (pre.next.next != null && pre.next.val == pre.next.next.val) {
                pre.next = pre.next.next;
            } else {
                pre = pre.next;
            }
        }
        return dummyHead.next;
    }

    /**
     * 递归
     *
     * @param head
     * @return
     */
    public ListNode deleteDuplicates2(ListNode head) {
        if (head.next == null) {
            return head;
        }
        ListNode listNode = deleteDuplicates2(head.next);

//        listNode
        if (head.next != null && head.val == head.next.val) {
            //如果相邻元素值相等 则 直接返回 去头的链表
            return listNode;
        } else {
            //如果不相等说明 保持当前链表
            head.next = listNode;
        }
        return head;

    }


    /**
     * 给定一个排序链表，删除所有含有重复数字的节点，只保留原始链表中 没有重复出现 的数字。
     *
     * 示例 1:
     *
     * 输入: 1->2->3->3->4->4->5
     * 输出: 1->2->5
     * 示例 2:
     *
     * 输入: 1->1->1->2->3
     * 输出: 2->3
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list-ii
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param head
     * @return
     */
    public ListNode deleteDuplicates3(ListNode head) {
        return null;
    }

    public ListNode testElement(ListNode head, int depth) {
        String depthString = generateDepthString(depth);
        Utils.print(depthString);
        Utils.println("Call: delete "+ head.val +" in:" + head);
        if (head.next == null) {
            Utils.print(depthString);
            Utils.println("Return: " + head);
            return head;
        }
        ListNode listNode = testElement(head.next, depth+1);
        Utils.print(depthString);
        Utils.println("After delete: " + head.val + " in:"+ listNode);
//        listNode
        if (head.next != null && head.val == head.next.val) {
            //如果相邻元素值相等 则 直接返回 去头的链表
            Utils.print(depthString);
            Utils.println("Return: " + listNode);
            return listNode;
        } else {
            //如果不相等说明 保持当前链表
            head.next = listNode;
        }
        Utils.print(depthString);
        Utils.println("Return: " + head);
        return head;

    }

    private String generateDepthString(int depth) {
        StringBuilder builder = new StringBuilder();
        //根据深度延长 间隔线
        for (int i = 0; i < depth; i++) {
            builder.append("--");
        }
        return builder.toString();
    }
}