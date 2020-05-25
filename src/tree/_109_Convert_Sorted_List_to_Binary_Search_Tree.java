package tree;

/**
 * Given a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.
 *
 * For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two
 * subtrees of every node never differ by more than 1.
 *
 * Example:
 * Given the sorted linked list: [-10,-3,0,5,9],
 * One possible answer is: [0,-3,9,-10,null,5], which represents the following height balanced BST:
 *
 *       0
 *      / \
 *    -3   9
 *    /   /
 *  -10  5
 *
 * */


public class _109_Convert_Sorted_List_to_Binary_Search_Tree {

    class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) {
            this.val = val;
        }
        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public TreeNode sortedListToBST(ListNode head) {
        return helper(head, null);

    }

    private ListNode findMiddle(ListNode head, ListNode end){
        ListNode res = head;
        if(head == end) return null;
        while(head.next!=end && head.next.next!=end){
            res = res.next;
            head = head.next.next;
        }
        return res;
    }

    private TreeNode helper(ListNode head, ListNode end){
        ListNode mid = findMiddle(head, end);
        if(mid == null) return null;
        TreeNode root = new TreeNode(mid.val);
        root.left = helper(head, mid);
        root.right = helper(mid.next,end);
        return root;
    }
}
