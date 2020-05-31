package tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * You are given a perfect binary tree where all leaves are on the same level,
 * and every parent has two children. The binary tree has the following definition:
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

/*
// Definition for a Node.
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
};
*/

public class _116_Populating_Next_Right_Pointers_in_Each_Node {
    public Node connect(Node root) {
        if(root==null || root.left==null) return root;
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        int curLevel = 1;
        int nextLevel = 0;
        Node prev = null;
        Node cur = null;
        while (!queue.isEmpty()){
            while (curLevel>0){
                prev = cur;
                cur = queue.poll();
                curLevel--;
                if(prev!=null) prev.next = cur;
                if(cur.left!=null) {
                    queue.offer(cur.left);
                    queue.offer(cur.right);
                    nextLevel+=2;
                }
            }
            cur = null;
            curLevel = nextLevel;
            nextLevel = 0;
        }
        return root;
    }
}
