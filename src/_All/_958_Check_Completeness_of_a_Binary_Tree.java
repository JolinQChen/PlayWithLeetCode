package _All;

import java.util.*;

/**
 * Given a binary tree, determine if it is a complete binary tree.
 *
 * Definition of a complete binary tree from Wikipedia:
 * In a complete binary tree every level, except possibly the last, is completely filled,
 * and all nodes in the last level are as far left as possible. It can have between 1 and 2h
 * nodes inclusive at the last level h.
 *
 * 两个要求：
 * 1. 除最后一层以外，其他层全部fill
 * 2. 最后一层尽量靠左
 *
 * Input: [1,2,3,4,5,6]
 * Output: true
 * Explanation: Every level before the last is full (ie. levels with node-values {1} and {2, 3}),
 * and all nodes in the last level ({4, 5, 6}) are as far left as possible.
 * */


public class _958_Check_Completeness_of_a_Binary_Tree {

    public boolean isCompleteTree(TreeNode root) {
        // bfs\
        if(root==null) return true;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
       // _All.TreeNode cur = null;
        while (!queue.isEmpty()){
            TreeNode cur = queue.peek();
            if(cur.left!=null && cur.right!=null) {
                queue.poll();
                queue.add(cur.left);
                queue.add(cur.right);
            }
            else {
                if(cur.right!=null) return false;
                if(cur.left!=null) queue.add(cur.left);
                break;
            }
        }
        if(queue.isEmpty()) return true;
        queue.poll();
        while (!queue.isEmpty()){
            TreeNode tmp = queue.poll();
            if(tmp.left!=null || tmp.right!=null) return false;
        }
        return true;
    }
}

