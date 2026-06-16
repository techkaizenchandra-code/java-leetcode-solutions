package com.synechisveltiosi.leetcode.leetcode.leetcode.models;

import lombok.Data;

@Data
public class ListNode {
    public final int val;
    public ListNode next;

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    public ListNode(int val) {
        this.val = val;
        this.next = null;
    }
}
