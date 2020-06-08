package tree;

/**
 * Given a binary search tree and a node in it, find the in-order successor of that node
 * in the BST.
 *
 * The successor of a node p is the node with the smallest key greater than p.val.
 *
 *
 * */

public class _285_Inorder_Successor_in_BST {
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if(root == null || p==null) return null;

        if(p.right != null){
            TreeNode min_node = p.right;
            while(min_node.left != null)
                min_node = min_node.left;
            return min_node;
        }


        //The root.right is null
        // succ始终保持比root大
        TreeNode succ = null;
        while(root != null){
            if(p.val < root.val){
                succ = root;
                root = root.left;
            }else if(p.val > root.val){
                root = root.right;
            }else
                break;
        }
        return succ;
    }
}
