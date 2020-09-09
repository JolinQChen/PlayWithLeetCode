package _AAInterviews.Wish;

public class LinkedListCycleGetCycleBegin {
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
    public ListNode detectCycle(ListNode head) {
        if(head == null || head.next==null || head.next.next == null) return null;
        ListNode fast = head.next.next;
        ListNode slow = head.next;
        while(fast != slow){
            if (fast == null || fast.next == null) return null;
            fast = fast.next.next;
            slow = slow.next;
        }
        // first intersection
        fast = head;
        while(fast != slow){
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }
}
