package _AAInterviews.Wish;

import java.util.*;
public class SwapNodesInPairs {
    class ListNode{
        int val;
        ListNode next;
        ListNode(){}
        ListNode(int val){
            this.val = val;
        }
        ListNode(int val, ListNode next){
            this.val = val;
            this.next = next;
        }
    }
    public ListNode swapPairs(ListNode head) {
        if(head==null || head.next == null) return head;
        ListNode preHead = new ListNode();
        preHead.next = head;
        ListNode pre = preHead;
        ListNode p1 = head;

        while (p1!=null && p1.next!=null) {
            ListNode p2 = p1.next;
            ListNode next = p2.next;
            p2.next = p1;
            p1.next = next;
            pre.next = p2;

            pre = p1;
            p1 = next;

        }
        return preHead.next;

    }
}
