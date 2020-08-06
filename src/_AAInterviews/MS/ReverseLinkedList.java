package _AAInterviews.MS;
import java.util.*;
public class ReverseLinkedList {

    public ListNode reverseList(ListNode head) {
        if(head == null || head.next==null) return head;
        ListNode node = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return node;
    }
    // 法2：三个指针
}
