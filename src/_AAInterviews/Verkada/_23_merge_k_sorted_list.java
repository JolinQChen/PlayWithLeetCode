package _AAInterviews.Verkada;
import java.util.*;
public class _23_merge_k_sorted_list {

    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    // compare one by one, PQ, NlogK
    public ListNode mergeKLists_1(ListNode[] lists) {
        ListNode dummyHead = new ListNode();
        ListNode cur = dummyHead;
        PriorityQueue<ListNode> queue = new PriorityQueue<>((a,b)->(a.val-b.val));
        for(ListNode node:lists) queue.add(node);
        while(!queue.isEmpty()) {
            ListNode node = queue.poll();
            cur.next = node;
            cur = cur.next;
            if(node.next!=null) queue.add(node.next);
        }
        return dummyHead.next;
    }
}
