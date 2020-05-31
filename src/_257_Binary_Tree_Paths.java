import java.util.*;
/**
 * Given a binary tree, return all root-to-leaf paths.
 *
 * Note: A leaf is a node with no children.
 *
 * Example:
 *
 * Input:
 *
 *    1
 *  /   \
 * 2     3
 *  \
 *   5
 *
 * Output: ["1->2->5", "1->3"]
 * */

public class _257_Binary_Tree_Paths {
    private List<String> res;
    public List<String> binaryTreePaths(TreeNode root) {
        res = new ArrayList<>();
        if(root==null) return res;
        dfs(String.valueOf(root.val),root);
        return res;
    }
    private void dfs(String str, TreeNode cur){
        if(cur.left==null && cur.right==null) res.add(str);
        else {
            if(cur.left!=null) dfs(str + "->" + String.valueOf(cur.left.val),cur.left);
            if(cur.right!=null) dfs((str +"->" + String.valueOf(cur.right.val)),cur.right);
        }
    }
}
