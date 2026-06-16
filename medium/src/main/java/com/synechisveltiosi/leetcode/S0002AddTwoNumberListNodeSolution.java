package com.synechisveltiosi.leetcode;

import com.synechisveltiosi.leetcode.leetcode.leetcode.models.ListNode;

public class S0002AddTwoNumberListNodeSolution {
    private S0002AddTwoNumberListNodeSolution() {
        /* This utility class should not be instantiated */
    }

    static class MathAlgoSolution {
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            ListNode head = new ListNode(0);
            ListNode curr = head;
            int carry = 0;
            while (l1 != null || l2 != null || carry != 0) {
                int l1Val = l1 != null ? l1.val : 0;
                int l2Val = l2 != null ? l2.val : 0;
                int sum = l1Val + l2Val + carry;
                carry = sum / 10;
                curr.next = new ListNode(sum % 10);
                curr = curr.next;
                if (l1 != null) l1 = l1.next;
                if (l2 != null) l2 = l2.next;
            }
            return head.next;
        }
    }
}
