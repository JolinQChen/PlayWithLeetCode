package tree;

/**
 * Given two binary trees and imagine that when you put one of them to cover the other,
 * some nodes of the two trees are overlapped while the others are not.
 *
 * You need to merge them into a new binary tree. The merge rule is that if two nodes overlap,
 * then sum node values up as the new value of the merged node. Otherwise, the NOT null node will
 * be used as the node of new tree.
 *
 *
 * */

public class _617_Merge_Two_BInary_Trees {
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        //直接merge到t2上去
        if(t2 == null) return t1;
        mergeHelper(t1, t2);
        return t2;
    }

    private void mergeHelper(TreeNode t1, TreeNode t2){
        if(t1!=null && t2!=null) {
            t2.val += t1.val;
            if(t1.left!=null){
                if(t2.left==null) t2.left = new TreeNode(0);
                mergeHelper(t1.left, t2.left);
            }
            if(t1.right!=null){
                if(t2.right==null) t2.right = new TreeNode(0);
                mergeHelper(t1.right, t2.right);
            }

        }
    }
}
