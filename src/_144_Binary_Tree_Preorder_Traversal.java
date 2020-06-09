import java.util.*;

/**
 * Given a binary tree, return the preorder traversal of its nodes' values.
 *
 * Follow up: Recursive solution is trivial, could you do it iteratively?
 *
 * 不就是常规的stack操作吗...
 * */

public class _144_Binary_Tree_Preorder_Traversal {
    public List<Integer> preorderTraversal(TreeNode root) {

        Stack<TreeNode> stack = new Stack<>();
        List<Integer> res = new ArrayList<>();
        if(root==null) return res;
        stack.push(root);
        while (!stack.isEmpty()){
            TreeNode cur = stack.pop();
            res.add(cur.val);
            if(cur.right!=null) stack.push(cur.right);
            if(cur.left!=null) stack.push(cur.left);
        }
        return res;
    }
}
