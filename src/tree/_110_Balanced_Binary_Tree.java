package tree;

/**
 * Given a binary tree, determine if it is height-balanced.
 *
 * For this problem, a height-balanced binary tree is defined as:
 * a binary tree in which the left and right subtrees of every node differ in height by no more than 1.
 *
 * Example 1:
 *
 * Given the following tree [3,9,20,null,null,15,7]:
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * Return true.
 * */

public class _110_Balanced_Binary_Tree {
    public boolean isBalanced(TreeNode root) {
        if(root == null) return true;
        return Math.abs(height(root.left)-height(root.right))<=1 && isBalanced(root.left) && isBalanced(root.right);
    }

    private int height(TreeNode root){
        if(root == null) return 0;
        else return Math.max(height(root.left),height(root.right))+1;
    }


}
