package _AAInterviews.MS;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class LowestCommonAncestorofaBinarySearchTree {
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
