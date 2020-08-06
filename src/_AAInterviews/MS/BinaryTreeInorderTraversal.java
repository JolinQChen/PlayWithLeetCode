package _AAInterviews.MS;
import java.util.*;
public class BinaryTreeInorderTraversal {
    List<Integer> list;
    public List<Integer> inorderTraversal1(TreeNode root) {
        list = new ArrayList<>();
        helper(root);
        return list;
    }

    private void helper(TreeNode root) {
        if(root==null) return;
        helper(root.left);
        list.add(root.val);
        helper(root.right);
    }

    // 递归 recursively
    public static List<Integer> inorderTraversal2(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> res = new ArrayList<>();
        if(root==null) return res;
        //stack.push(root);
        TreeNode curr = root;
        while(curr!=null || !stack.isEmpty()) {
            while(curr!=null) {
                stack.push(curr);
                curr = curr.left;
            }
            curr = stack.pop();
            res.add(curr.val);
            curr = curr.right;
        }
        return res;
    }
}
