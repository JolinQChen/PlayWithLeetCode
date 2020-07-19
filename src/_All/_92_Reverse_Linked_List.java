package _All;

public class _92_Reverse_Linked_List {
    // 用扫描一趟完成从位置m到n的反转
    public class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
  }
    public ListNode reverseBetween(ListNode head, int m, int n) {

        ListNode newHead = new ListNode(-1);
        ListNode start_prev = newHead;
        newHead.next = head;
        ListNode start = head;
        int i=1;
        while(i<m){
            start = start.next;
            start_prev = start_prev.next;
            i++;
        }
        ListNode record_start = start;
        //开始反转
        ListNode prev = null;
        ListNode current = start;
        ListNode next = null;
        while(i<=n){
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
            i++;
        }
        start_prev.next = prev;
        record_start.next = current;
        return newHead.next;
    }
}
