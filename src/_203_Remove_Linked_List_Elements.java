
public class _203_Remove_Linked_List_Elements {
    class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
    public ListNode removeElements(ListNode head, int val) {
        ListNode pointer = head;
        ListNode preHead = new ListNode();
        preHead.next = head;
        ListNode pre = preHead;
        while(pointer!=null){
            if(pointer.val==val){
                pre.next = pointer.next;
                pointer = pointer.next;
            }
            else{
                pointer = pointer.next;
                pre = pre.next;
            }
        }
        return preHead.next;
    }
}
