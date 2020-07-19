package _All;

import java.util.*;

public class _106_Construct_Binary_Tree_from_Postorder_and_Inorder_Traversal {
    private int post_index;
    private int[] inorder;
    private int[] postorder;
    private Map<Integer, Integer> map;

    private TreeNode helper(int in_left, int in_right){
        if(in_left == in_right) return null;
        int rootVal = postorder[post_index];
        TreeNode cur = new TreeNode(rootVal);
        int cur_index = map.get(rootVal);

        post_index--;
        cur.right = helper(cur_index, in_right);
        cur.left = helper(in_left, cur_index-1);
        return cur;
    }

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        this.postorder = postorder;
        this.inorder = inorder;
        this.post_index = postorder.length-1;
        this.map = new HashMap<>();
        for(int i=0; i<inorder.length; i++) map.put(inorder[i],i);
        return helper(-1, inorder.length-1);
    }
}
