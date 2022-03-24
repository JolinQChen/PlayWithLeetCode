package tree;
import java.util.*;
public class _366_Find_Leaves_of_Binary_Tree {
    public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if(root==null) return res;
        TreeNode dummyHead = new TreeNode();
        dummyHead.left = root;
        while (dummyHead.left!= null) {
            List<Integer> tmp = new ArrayList<>();
            helper(dummyHead, dummyHead.left, true, tmp);
            res.add(tmp);
        }
        return res;
    }

    public void helper(TreeNode pre, TreeNode cur, boolean isLeft, List<Integer> curList) {
        if(isLeaf(cur)) {
            curList.add(cur.val);
            if(isLeft) pre.left = null;
            else pre.right = null;
        }
        else {
            if(cur.left!=null) helper(cur, cur.left, true, curList);
            if(cur.right!= null) helper(cur, cur.right, false, curList);
        }
    }



    private boolean isLeaf(TreeNode node) {
        return node!=null && node.left==null && node.right==null;
    }
}
