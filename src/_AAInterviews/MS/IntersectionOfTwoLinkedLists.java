package _AAInterviews.MS;
import java.util.*;
public class IntersectionOfTwoLinkedLists {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        Set<ListNode> set = new HashSet<>();
        while(headA!=null || headB!=null) {
            if(headA!=null){
                if(!set.contains(headA)) {
                    set.add(headA);
                    headA = headA.next;
                }
                else return headA;
            }
            if(headB!=null){
                if(!set.contains(headB)) {
                    set.add(headB);
                    headB = headB.next;
                }
                else return headB;
            }

        }
        return null;
    }
}
