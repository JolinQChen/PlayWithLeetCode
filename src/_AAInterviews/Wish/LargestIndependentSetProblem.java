package _AAInterviews.Wish;
import java.util.*;


// https://www.geeksforgeeks.org/largest-independent-set-problem-dp-26/
// DP / recursive
/**
 * Sub-problem:
 * Can we find largest independent set size (LISS) for a node X if we know LISS for all descendants of X?
 *
 * If a node is considered as part of LIS, then its children cannot be part of LIS,
 * but its grandchildren can be. Following is optimal substructure property.
 * LISS(X) = MAX { (1 + sum of LISS for all grandchildren of X),(sum of LISS for all children of X) }
 * */

public class LargestIndependentSetProblem {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode(int v) {
            this.val = v;
        }
    }
    // Recursive
    static int LISS_rec(TreeNode root){
        if(root==null) return 0;
        int size_incl = 1; // calculate size including current node
        if(root.left !=null) size_incl += LISS_rec(root.left.left) + LISS_rec(root.left.right);
        if(root.right!=null) size_incl += LISS_rec(root.right.left) + LISS_rec(root.right.right);

        int size_excl = LISS_rec(root.left) + LISS_rec(root.right);
        return Math.max(size_excl, size_incl);
    }

    /**Time complexity of the above naive recursive approach is exponential. It should be noted that
     * the above function computes the same subproblems again and again.
     * For example, LISS of node with value 50 is evaluated for node with values 10 and 20 as 50 is
     * grandchild of 10 and child of 20.
     */

    // DP
    private static Map<TreeNode, Integer> memo = new HashMap<>();
    static int LISS_dp(TreeNode root){
        if(root == null) return 0;
        if(memo.containsKey(root)) return memo.get(root);
        int size_incl = 1;
        if(root.left != null) size_incl += LISS_dp(root.left.left) + LISS_dp(root.left.right);
        if(root.right!=null) size_incl += LISS_dp(root.right.left) + LISS_dp(root.right.right);
        int size_excl = LISS_dp(root.left) + LISS_dp(root.right);
        int res = Math.max(size_excl, size_incl);
        memo.put(root, res);
        return res;
    }

    public static void main(String[] args)
    {
        // Let us construct the tree given
        // in the above diagram

        TreeNode root = new TreeNode(20);
        root.left = new TreeNode(8);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(12);
        root.left.right.left = new TreeNode(10);
        root.left.right.right = new TreeNode(14);
        root.right = new TreeNode(22);
        root.right.right = new TreeNode(25);
        System.out.println("Size of the Largest Independent Set is " + LISS_dp(root));
    }
}
