package tree;
import java.util.*;
public class TraverseIterative {
    /**  PreOrder1  */
    public static List<Integer> preOrderTraversal1(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> res = new ArrayList<>();
        TreeNode curr = root;
        while (curr!=null || !stack.isEmpty()) {
            while (curr!=null) {
                res.add(curr.val);
                stack.push(curr);
                curr = curr.left;
            } // reach the left-most node
            while (!stack.isEmpty() && curr==null) curr = stack.pop().right; // search bottom-up
        }

        return res;
    }

    /**  preOrder2  */
    public static List<Integer> preOrderTraversal2(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        if(root==null) return res;
        stack.add(root);
        while (!stack.isEmpty()) {
            TreeNode curr =  stack.pop();
            res.add(curr.val);
            if(curr.right!=null) stack.add(curr.right);
            if(curr.left!=null) stack.add(curr.left);
        }
        return res;
    }

    /**  inOrder  */

    public static List<Integer> inorderTraversal(TreeNode root) {
        if(root==null) return new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> res = new ArrayList<>();
        // get to left-most node
        TreeNode cur = root;
        while (cur!=null || !stack.isEmpty()) {
            while (cur!=null) {
                stack.push(cur);
                cur = cur.left;
            }
            while (cur==null && !stack.isEmpty()) {
                cur = stack.pop();
                res.add(cur.val);
                cur = cur.right;
            }
        }
        return res;
    }



    /**  postOrder  */
    public static List<Integer> postorderTraversal(TreeNode root) {
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


    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.left.right.left = new TreeNode(7);
        root.left.right.right = new TreeNode(8);
        root.right = new TreeNode(3);
        root.right.right = new TreeNode(6);
        root.right.right.right = new TreeNode(9);
        List<Integer> res = postorderTraversal(root);
        for(int i=0; i<res.size(); i++) {
            System.out.println(res.get(i) + ", ");
        }
    }
}
