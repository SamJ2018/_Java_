package com.cys.leetcode.package1;


/**
 * Given a non-negative number represented as a singly linked list of digits, plus one to the number.
 * The digits are stored such that the most significant digit is at the head of the list.
 *
 * Example:
 *
 * Input:
 * 1->2->3
 *
 * Output:
 * 1->2->4
 */
public class PlusOneLinkedList_369 {

    private static class ListNode {
        int val;
        ListNode next;

        //constructor
        ListNode(int x) {
            val = x;
        }
    }//ListNode


    private static ListNode plusOne(ListNode head) {
        //Dummy node
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode i = dummy;
        ListNode j = dummy;

        while (j.next != null) {
            j = j.next;
            if (j.val != 9) {
                i = j;
            }
        }

        if (j.val != 9) {
            //case1:plus one directly
            j.val++;
        } else {
            //case2:have a carry
            i.val++;
            i = i.next;
            while (i != null) {
                i.val = 0;
                i = i.next;
            }
        }

        if (dummy.val == 0) {
            return dummy.next;
        }

        return dummy;
    }
}
