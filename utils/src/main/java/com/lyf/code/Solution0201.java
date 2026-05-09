package com.lyf.code;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode(int x) { val = x; }
 * }
 * 编写代码，移除未排序链表中的重复节点。保留最开始出现的节点。
 */
public class Solution0201 {
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public static ListNode removeDuplicateNodes(ListNode head) {
        if (null == head) {
            return head;
        }
        ListNode temp = head;
        ListNode temp2 = temp;
        ListNode p = temp.next;
        while (null != temp) {
            if (null == p) {
                temp = temp.next;
                if (null != temp) {
                    p = temp.next;
                    temp2 = temp;
                }
            } else {
                if (temp.val == p.val) {
                    temp2.next = p.next;
                    p = p.next;
                } else {
                    temp2 = temp2.next;
                    p = p.next;
                }
            }
        }
        return head;
    }



    public static void main(String[] args) {

//        ListNode head = new ListNode(0);
//        ListNode listNode1 = new ListNode(1);
//        head.next = listNode1;
//        ListNode listNode2 = new ListNode(2);
//        listNode1.next = listNode2;
//        ListNode listNode3 = new ListNode(3);
//        listNode2.next = listNode3;
//        ListNode listNode4 = new ListNode(3);
//        listNode3.next = listNode4;
//        ListNode listNode5 = new ListNode(2);
//        listNode4.next = listNode5;
//        ListNode listNode6 = new ListNode(1);
//        listNode5.next = listNode6;
//        listNode6.next = null;


        int[] arr = new int[]{};
        ListNode head2 = new ListNode(0);
        ListNode p = head2;
        for (int i : arr) {
            p.next = new ListNode(i);
            p = p.next;
        }
        removeDuplicateNodes(head2.next);
        System.out.println(123);
    }
}
