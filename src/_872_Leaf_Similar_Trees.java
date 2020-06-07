import java.util.*;
public class _872_Leaf_Similar_Trees {
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        List<Integer> l1 = dfs(root1, new ArrayList<>());
        List<Integer> l2 = dfs(root2, new ArrayList<>());
        return l1.equals(l2);
    }
    private List<Integer> dfs(TreeNode root, List<Integer> cur){
        if(root==null) return cur;
        if(root!=null && root.left==null && root.right==null) {
            cur.add(root.val);
            return cur;
        }
        else {
            if(root.left!=null) cur=dfs(root.left, cur);
            if(root.right!=null) cur=dfs(root.right, cur);
        }
        return cur;
    }
}
