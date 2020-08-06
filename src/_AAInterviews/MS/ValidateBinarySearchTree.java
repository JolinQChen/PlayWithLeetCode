package _AAInterviews.MS;
import java.util.*;
public class ValidateBinarySearchTree {
    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    public boolean isValidBST(TreeNode root) {
        return helper(root,null, null);
    }
    private boolean helper(TreeNode node, Integer min, Integer max) {
        if(node == null) return true;
        if((min!=null && node.val<=min) || (max!=null && node.val >= max)) return false;
        return helper(node.left,min,node.val) && helper(node.right, node.val, max);
    }
}
