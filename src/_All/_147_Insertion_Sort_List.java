package _All;

public class _147_Insertion_Sort_List {
    public static ListNode insertionSortList(ListNode head) {
        if(head==null || head.next==null) return head;
        ListNode preHead = new ListNode(Integer.MIN_VALUE);
        preHead.next = head;
        ListNode pre = preHead.next;
        head = head.next;
        while (head!=null) {
            pre.next = null;
            ListNode next = head.next;
            ListNode insert = preHead;
            while (insert.next!=null && insert.next.val<=head.val) {
                insert = insert.next;
            }
            if(insert.next!=null) head.next = insert.next;
            insert.next = head;
            if(pre.next==null) {
                pre.next = next;
            }
            else pre = pre.next;

            head = pre.next;
            ListNode p = preHead;
            while (p.next!=null) {
                System.out.print(p.next.val+" ");
                p = p.next;
            }
            System.out.println();
        }
        return preHead.next;
    }
}
