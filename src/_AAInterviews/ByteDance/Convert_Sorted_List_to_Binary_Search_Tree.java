package _AAInterviews.ByteDance;

public class Convert_Sorted_List_to_Binary_Search_Tree {
    class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() { }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public TreeNode sortedListToBST(ListNode head) {
        //二叉搜索树的中序遍历是sorted list
        return helper(head,null);
    }
    private ListNode findMid(ListNode head, ListNode end) {
        // find the middle ListNode in part of the sorted list
        if(head == end) return null;
        ListNode p = head;
        while(head.next!=end && head.next.next!=end) {
            head = head.next.next;
            p = p.next;
        }
        return p;
    }

    private TreeNode helper(ListNode head, ListNode end) {
        ListNode mid = findMid(head,end);
        if(mid==null) return null;
        TreeNode root = new TreeNode(mid.val);
        root.left = helper(head, mid);
        root.right = helper(mid.next, end);
        return root;
    }
}
