package _AAInterviews.MS;
import java.util.*;

public class MergeKSortedLists {
    public ListNode mergeKLists(ListNode[] lists) {
        ListNode curHead = null;
        int idx = -1;
        for(int i=0; i<lists.length; i++) {
            ListNode ln = lists[i];

            if(ln!=null) {
                if(curHead==null) {
                    curHead = ln;
                    idx = i;
                }
                else if(curHead.val>ln.val) {
                    curHead = ln;
                    idx = i;
                }
            }
        }
        if(curHead == null) {
            //全为null
            return null;
        }
        else {
            lists[idx] = lists[idx].next;
            curHead.next = mergeKLists(lists);
            return curHead;
        }
    }
}
