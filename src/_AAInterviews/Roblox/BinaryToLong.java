package _AAInterviews.Roblox;

public class BinaryToLong {
    static class ListNode{
        ListNode next;
        int val;
        public ListNode(int v) {
            this.val = v;
            next = null;
        }
        public ListNode(){}
    }

    public static long toLong(ListNode head) {
        long res = 0;
        while (head!=null) {
            res = res * 2 + head.val;
            head = head.next;
        }
        return res;
    }
    private static ListNode toHead(int[] arr) {
        ListNode head = new ListNode();
        ListNode node = head;

        for(int i=0; i<arr.length; i++) {
            node.next = new ListNode(arr[i]);
            node = node.next;
        }
        return head.next;
    }
    public static void main(String[] args) {
        int[] arr = {0,0,0,0,0,1,1,1,1,1};
        ListNode head = toHead(arr);
        long res = toLong(head);
        System.out.println(res);
    }
}
