package _AAInterviews.VMware;
import java.util.*;
public class MergeKSortedLists {
    static class ListNode{
        int val;
        ListNode next;
        public ListNode(int v) {
            val = v;
        }
        public ListNode(){}
    }

    public static ListNode mergeKLists(ListNode[] lists) {
        if(lists.length==0) return null;

        ListNode resHead = new ListNode();
        ListNode p = resHead;
        PriorityQueue<ListNode> minHeap = new PriorityQueue<>((a, b)->(a.val-b.val));
        // initialization
        for(ListNode node:lists) {
            if(node != null) minHeap.add(node);
        }

        while (!minHeap.isEmpty()) {
            ListNode tmp = minHeap.poll();
            p.next = new ListNode(tmp.val);
            p = p.next;
            tmp = tmp.next;
            if(tmp!=null) minHeap.add(tmp);
        }
        return resHead.next;


    }
}
