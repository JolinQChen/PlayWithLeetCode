package _All;
import java.util.*;
public class _145_Binary_Tree_Postorder_Traversal {
    /**  recursive solution  */
    private List<Integer> list;

    public List<Integer> postorderTraversal_recursive(TreeNode root) {
        list = new ArrayList<>();
        helper(root);
        return list;
    }

    private void helper(TreeNode root) {
        if(root!=null) {
            helper(root.left);
            helper(root.right);
            list.add(root.val);
        }
    }

    /**  iterative solution  */

    public List<Integer> postorderTraversal_iterative(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if(root==null) return res;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        TreeNode pre = null; // record the last visited node
        while (cur!=null || !stack.isEmpty()) {
            while (cur!=null) {
                // get to left-most node
                stack.push(cur);
                cur = cur.left;
            }
            if(!stack.isEmpty()) {
                cur = stack.pop();
                if(cur.right==null || pre==cur.right) {
                    res.add(cur.val);
                    pre = cur;
                    cur = null;
                }
                else {
                    stack.push(cur);
                    cur = cur.right;
                }
            }
        }

        return res;
    }

}
