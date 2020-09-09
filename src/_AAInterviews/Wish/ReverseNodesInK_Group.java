package _AAInterviews.Wish;

public class ReverseNodesInK_Group {
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

    public ListNode reverseKGroup(ListNode head, int k) {
        if(k <= 1) return head;
        ListNode preHead = new ListNode(-1);
        preHead.next = head;
        ListNode preStart = preHead;
        ListNode end = preHead;
        //移动到末端
        while(end.next!=null){
            for(int i=0; i<k && end!=null; i++) end = end.next;
            if(end == null) break;
            // end移动到当下最后一位
            ListNode start = preStart.next;
            ListNode postEnd = end.next;
            end.next = null;
            preStart.next = reverseEntire(start);
            start.next = postEnd;
            end = start;
            preStart = start;
        }
        return preHead.next;
    }

    private ListNode reverseEntire(ListNode head){
        if(head == null || head.next == null) return head;
        ListNode p = head;
        ListNode q = head.next;
        while(q!=null){
            ListNode tmp = q.next;
            q.next = p;
            p = q;
            q = tmp;
        }
        return p;
    }
}
