package _AAInterviews.Wish;
import java.util.*;
public class IntersectionOfTwoLinkedLists {
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
