package _All;

import java.util.*;
/**
 * Given a binary tree containing digits from 0-9 only, each root-to-leaf path
 * could represent a number.
 *
 * An example is the root-to-leaf path 1->2->3 which represents the number 123.
 *
 * Find the total sum of all root-to-leaf numbers.
 *
 * Note: A leaf is a node with no children.
 *
 * Input: [1,2,3]
 *     1
 *    / \
 *   2   3
 * Output: 25
 * Explanation:
 * The root-to-leaf path 1->2 represents the number 12.
 * The root-to-leaf path 1->3 represents the number 13.
 * Therefore, sum = 12 + 13 = 25.
 * */
public class _129_Sum_Root_to_Leaf_Numbers {
    List<Integer> store = new LinkedList<>();
    public int sumNumbers(TreeNode root) {
        if(root == null) return 0;
        traverse(root, root.val);
        int res = 0;
        for(int i:store){
            res+=i;
        }
        return res;
    }
    private void traverse(TreeNode root, int cur){
        //cur包括root.val
        if(root.left==null && root.right==null) store.add(cur);
        if(root.left!=null){
            traverse(root.left, cur*10+root.left.val);
        }
        if(root.right!=null) traverse(root.right, cur*10+root.right.val);
    }
}
