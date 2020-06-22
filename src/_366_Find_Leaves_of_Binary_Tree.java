import java.util.*;
public class _366_Find_Leaves_of_Binary_Tree {
    public List<List<Integer>> findLeaves(TreeNode root) {
        TreeNode head = new TreeNode();
        head.left = root;
        List<List<Integer>> res = new ArrayList<>();
        while(head.left!=null){
            List<Integer> tmp = new ArrayList<>();
            helper(head, head.left, true, tmp);
            res.add(tmp);
        }
        return res;
    }

    private void helper(TreeNode pre, TreeNode curr, boolean isLeft, List<Integer> list){
        if(isLeaf(curr)){
            list.add(curr.val);
            if(isLeft) pre.left=null;
            else pre.right = null;
        }
        else {
            if(curr.left!=null) helper(curr, curr.left, true, list);
            if(curr.right!=null) helper(curr, curr.right, false, list);
        }
    }

    private boolean isLeaf(TreeNode node){
        if(node!=null && node.left==null && node.right==null) return true;
        return false;
    }
}
