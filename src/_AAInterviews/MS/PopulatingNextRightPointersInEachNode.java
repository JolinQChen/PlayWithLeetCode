package _AAInterviews.MS;

import java.util.*;
public class PopulatingNextRightPointersInEachNode {
    private class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }

    public Node connect(Node root) {
        if(root==null || root.left==null) return root;
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        Node prev = null;
        Node cur = null;

        while (!queue.isEmpty()) {
            int count = queue.size();
            while (count>0) {
                prev = cur;
                count--;
                cur = queue.poll();
                if(prev!=null) {
                    prev.next = cur;
                }
                if(cur.left!=null) {
                    queue.add(cur.left);
                    queue.add(cur.right);
                }
            }
            cur = null;
        }
        return root;
    }
}
