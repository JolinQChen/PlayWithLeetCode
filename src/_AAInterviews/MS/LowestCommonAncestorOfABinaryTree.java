package _AAInterviews.MS;
import java.util.*;
public class LowestCommonAncestorOfABinaryTree {
    //不同之处在于这里是搜索二叉树
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(p.val>q.val) {
            TreeNode tmp = p;
            p = q;
            q = tmp;
        }
        // 保证p.val < q.val
        if(root==p || root==q) return root;
        if(root.val>=p.val && root.val<=q.val) return root;
        else if(root.val > q.val) return lowestCommonAncestor(root.left, p, q);
        else return lowestCommonAncestor(root.right, p, q);
    }
}
