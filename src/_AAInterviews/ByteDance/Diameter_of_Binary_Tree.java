package _AAInterviews.ByteDance;

public class Diameter_of_Binary_Tree {


    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    private int maxDepth;
    public int diameterOfBinaryTree(TreeNode root) {
        depthHelper(root);
        return maxDepth==0?0:maxDepth-1;
    }

    private int depthHelper(TreeNode root) {
        if(root==null) return 0;
        int l = depthHelper(root.left);
        int r = depthHelper(root.right);
        maxDepth = Math.max(maxDepth, l+r+1);
        return Math.max(l,r)+1;
    }

}
