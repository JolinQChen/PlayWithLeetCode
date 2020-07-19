package _All;

import java.util.*;

/**
 * Given the root of a binary tree, the level of its root is 1, the level of its children is 2, and so on.
 *
 * Return the smallest level X such that the sum of all the values of nodes at level X is maximal.
 * */


public class _1161_Maximum_Level_Sum_of_a_Binary_Tree {
     /* Definition for a binary tree node.*/
     class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) {
            this.val = val;
        }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
     }

    public int maxLevelSum(TreeNode root) {
        //中序遍历求和？
        //BFS?
        int sum = root.val;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int resLevel = 1;
        int level = 0;
        int curLevel = 1;
        int nextLevel = 0;
        while(!queue.isEmpty()){
            int curSum = 0;
            while(curLevel>0){
                TreeNode cur = queue.poll();
                curSum += cur.val;
                curLevel--;
                if(cur.left!=null) {
                    queue.offer(cur.left);
                    nextLevel++;
                }
                if(cur.right!=null){
                    queue.offer(cur.right);
                    nextLevel++;
                }
            }
            level++;
            if(sum<curSum) {
                sum = curSum;
                resLevel = level;
            }
            curLevel = nextLevel;
            nextLevel = 0;
        }
        return resLevel;

    }

}
