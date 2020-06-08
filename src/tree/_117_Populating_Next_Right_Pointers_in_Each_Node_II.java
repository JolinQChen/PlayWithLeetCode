package tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given a binary tree
 *
 * struct Node {
 *   int val;
 *   Node *left;
 *   Node *right;
 *   Node *next;
 * }
 * Populate each next pointer to point to its next right node. If there is no next right
 * node, the next pointer should be set to NULL.
 *
 * Initially, all next pointers are set to NULL.
 * */

public class _117_Populating_Next_Right_Pointers_in_Each_Node_II {
    class Node {
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
        // bfs, obviously
        if(root==null || (root.left==null && root.right==null)) return root;
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            Node prev = queue.poll();
            int count = queue.size();
            if(prev.left!=null) queue.add(prev.left);
            if(prev.right!=null) queue.add(prev.right);
            while(count>0){
                count--;
                Node cur = queue.poll();
                if(cur.left!=null) queue.add(cur.left);
                if(cur.right!=null) queue.add(cur.right);
                prev.next = cur;
                prev = cur;
            }
        }
        return root;
    }


}
