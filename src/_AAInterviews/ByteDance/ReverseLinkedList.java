package _AAInterviews.ByteDance;


import java.util.*;

public class ReverseLinkedList {

    class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
    //递归
    public ListNode reverseList1(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode next = head.next;
        ListNode newNode = reverseList1(head.next);
        next.next = head;
        head.next = null;
        return newNode;
    }

    //迭代
    public ListNode reverseList2(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode p1 = null;
        ListNode p2 = head;
        ListNode p3 = head.next;
        while(p3!=null) {
            p2.next = p1;
            p1 = p2;
            p2 = p3;
            p3 = p3.next;
        }
        p2.next = p1;
        return p2;
    }
}
