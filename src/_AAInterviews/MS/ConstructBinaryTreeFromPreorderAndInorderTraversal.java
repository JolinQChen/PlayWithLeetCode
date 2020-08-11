package _AAInterviews.MS;
import java.util.*;
public class ConstructBinaryTreeFromPreorderAndInorderTraversal {

    // start from the first preorder element
    int pre_idx = 0;
    int[] preorder;
    int[] inorder;
    HashMap<Integer, Integer> idx_map = new HashMap<Integer, Integer>();

    private TreeNode helper(int in_left, int in_right) {
        if(in_left==in_right)return null;
        int curRootVal = preorder[pre_idx++];
        TreeNode curRoot = new TreeNode(curRootVal);
        int cur_idx = idx_map.get(curRootVal);
        curRoot.left = helper(in_left,cur_idx);
        curRoot.right = helper(cur_idx+1,in_right);

        return curRoot;
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        this.preorder = preorder;
        this.inorder = inorder;
        int idx = 0;
        for(int val:inorder) idx_map.put(val,idx++);
        return helper(0, inorder.length);
    }


}
