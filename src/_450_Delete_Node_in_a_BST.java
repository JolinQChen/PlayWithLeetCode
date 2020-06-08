import java.util.*;

/**
 * Given a root node reference of a BST and a key, delete the node with the
 * given key in the BST. Return the root node reference (possibly updated)
 * of the BST.
 *
 * Basically, the deletion can be divided into two stages:
 *
 * Search for a node to remove.
 * If the node is found, delete the node.
 * Note: Time complexity should be O(height of tree).
 *
 * Example:
 *
 * root = [5,3,6,2,4,null,7]
 * key = 3
 *
 *     5
 *    / \
 *   3   6
 *  / \   \
 * 2   4   7
 *
 * Given key to delete is 3. So we find the node with value 3 and delete it.
 *
 * One valid answer is [5,4,6,2,null,null,7], shown in the following BST.
 *
 *     5
 *    / \
 *   4   6
 *  /     \
 * 2       7
 *
 * Another valid answer is [5,2,6,null,4,null,7].
 *
 *     5
 *    / \
 *   2   6
 *    \   \
 *     4   7
 * */

public class _450_Delete_Node_in_a_BST {
    public TreeNode deleteNode(TreeNode root, int key) {
        if(root==null) return null;
        // first find key
        TreeNode head = new TreeNode(-1);
        head.left = root;
        TreeNode prev = head;
        boolean isLeft = true;
        TreeNode search = root;
        while(search!=null){
            if(search.val < key){
                prev = search;
                search = search.right;
                isLeft = false;
            }
            else if(search.val>key){
                prev = search;
                search = search.left;
                isLeft = true;
            }
            else break;
        }
        if(search == null) return root;
        if(isLeft){
            prev.left = deleteRoot(search);
        }
        else prev.right = deleteRoot(search);
        return head.left;
    }

    private TreeNode deleteRoot(TreeNode root){
        // no left child
        if(root.left==null) return root.right;
        // no right child
        if(root.right == null) return root.left;
        // have two children, choose left-most child
        TreeNode replace = root.left;
        TreeNode prev = root;
        while(replace.right!=null) {
            prev = replace;
            replace = replace.right;
        }
        if(prev.val==root.val) prev.left = deleteRoot(replace);
        else prev.right = deleteRoot(replace);
        root.val = replace.val;
        return root;
    }
}
