package tree;

public class _938_Range_Sum_of_BST {
    private int res;
    public int rangeSumBST(TreeNode root, int L, int R) {
        //遍历即可
        res = 0;
        dfs(root, L, R);
        return res;
    }
    private void dfs(TreeNode root, int L, int R){
        if(root!=null){
            if(root.val<L) dfs(root.right, L, R);
            else if(root.val > R) dfs(root.left, L, R);
            else {
                res += root.val;
                dfs(root.left, L, R);
                dfs(root.right, L, R);
            }
        }
    }
}
