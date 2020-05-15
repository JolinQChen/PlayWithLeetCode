package tree;

/**
 * Given a non-empty binary search tree and a target value,
 * find the value in the BST that is closest to the target.
 *
 * Notice:
 * Given target value is a floating point.
 * You are guaranteed to have only one unique value in the BST that is closest
 * to the target.
 *
 * Input: root = [4,2,5,1,3], target = 3.714286
 *
 *     4
 *    / \
 *   2   5
 *  / \
 * 1   3
 *
 * Output: 4
 * */


public class _270_Closest_Binary_Search_Tree_Value {

     /** Definition for a binary tree node.*/
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

    private int res;
    public int closestValue(TreeNode root, double target) {
        res = root.val;
        traverse(root, target);
        return res;
    }

    private void traverse(TreeNode root, double target){
        if(root!=null){
            res = Math.abs(root.val-target)>Math.abs((double)res-target)?res:root.val;
            traverse(root.left, target);
            traverse(root.right,target);
        }
    }

}
