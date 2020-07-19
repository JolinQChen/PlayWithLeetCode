package _All;

import java.util.*;

public class _105_Construct_Binary_Tree_from_Preorder_and_Inorder_Traversal {
    // start from the first preorder element
    private int pre_idx = 0;
    private int[] preorder;
    private int[] inorder;
    HashMap<Integer, Integer> idx_map = new HashMap<Integer, Integer>();

    private TreeNode helper(int in_left, int in_right) {
        if(in_left == in_right) return null;
        // pick preorder pre_idxth element as root
        int rootVal = preorder[pre_idx];
        TreeNode cur_root = new TreeNode(rootVal);
        // splite inorder list to left and right based on root value
        int cur_idx = idx_map.get(rootVal);
        pre_idx++;
        //left
        cur_root.left = helper(in_left,cur_idx);
        cur_root.right = helper(cur_idx+1, in_right);
        return cur_root;
    }


    public TreeNode buildTree(int[] preorder, int[] inorder) {
        this.preorder = preorder;
        this.inorder = inorder;
        int idx = 0;
        for(int val:inorder) idx_map.put(val,idx++);
        return helper(0, inorder.length);
    }
}
