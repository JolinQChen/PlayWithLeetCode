package tree;

import java.util.LinkedList;
import java.util.List;

/**
 * Given a binary tree and a sum, find all root-to-leaf paths where each
 * path's sum equals the given sum.
 *
 * Note: A leaf is a node with no children.
 * */
public class _113_Path_Sum_II {
    List<List<Integer>> storePath;
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        storePath = new LinkedList<>();
        if(root==null) return storePath;
        List<Integer> path = new LinkedList<>();
        path.add(root.val);
        pathTraverse(root, sum, path);
        return storePath;
    }
    private void pathTraverse(TreeNode root, int sum, List<Integer> path){
        if(root==null) return;
        if(root.left==null && root.right==null&& sum==root.val){
            storePath.add(new LinkedList<>(path));
            return;
        }
        if(root.left!=null){
            path.add(root.left.val);
            pathTraverse(root.left, sum-root.val, path);
            path.remove(path.size()-1);
        }
        if(root.right!=null){
            path.add(root.right.val);
            pathTraverse(root.right, sum-root.val, path);
            path.remove(path.size()-1);
        }
    }
}
