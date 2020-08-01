package _AAInterviews.ByteDance;


import java.util.*;

public class Binary_Tree_Maximum_Path_Sum {
    class TreeNode {
        TreeNode left;
        TreeNode right;
        int val;
        TreeNode(int v){
            this.val = v;
        }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    private int maxSum = Integer.MIN_VALUE;
    private List<TreeNode> list = new ArrayList<>();
    public int maxPathSum(TreeNode root) {
        search(root);
        return maxSum;
    }
    private List<TreeNode> list_tmp = new ArrayList<>();
    private int search(TreeNode root) {
        if(root == null) return 0;
//        curNode.add(root);
        int left = Math.max(search(root.left),0);
        if(left>0) list_tmp.add(root.left);
        int right = Math.max(search(root.right),0);
        if(right>0) list_tmp.add(root.right);
        int curSum = root.val + left + right;
        list_tmp.add(root);
        maxSum = Math.max(maxSum, curSum);

        return root.val + Math.max(left,right);
    }
}
