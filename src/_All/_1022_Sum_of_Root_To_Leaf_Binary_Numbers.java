package _All;

public class _1022_Sum_of_Root_To_Leaf_Binary_Numbers {
    public int count;

    public int sumRootToLeaf(TreeNode root) {
        // dfs回退
        if(root==null) return 0;
        count = 0;
        dfsHelper(0,root);
        return count;
    }

    private void dfsHelper(int prev_val, TreeNode node) {
        int cur_val = prev_val * 2 + node.val;
        if(node.left==null && node.right ==null) {
            // out
            count += cur_val;
        }
        if(node.left != null) dfsHelper(cur_val, node.left);
        if(node.right != null) dfsHelper(cur_val, node.right);
    }
}
