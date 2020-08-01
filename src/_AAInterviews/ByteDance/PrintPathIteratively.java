package _AAInterviews.ByteDance;
import sun.awt.image.ImageWatched;
import sun.security.x509.AttributeNameEnumeration;

import java.util.*;
//迭代方法
public class PrintPathIteratively {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode(int v) {
            this.val = v;
        }
    }
//    private List<String> paths;
    public static List<String> getPaths(TreeNode root) {
        List<String> paths = new ArrayList<>();
        Stack<String> pathStack = new Stack<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.add(root);
        pathStack.add(String.valueOf(root.val));
        while(!stack.isEmpty()) {
            TreeNode node = stack.pop();
            String cur_path = pathStack.pop();
            if(node.left==null && node.right==null) {
                paths.add(cur_path);
            }
            else {
                if(node.left!=null) {
                    stack.push(node.left);
                    pathStack.push(cur_path + " -> " + String.valueOf(node.left.val));
                }
                if(node.right != null) {
                    stack.push(node.right);
                    pathStack.push(cur_path +" -> "+ String.valueOf(node.right.val));
                }
            }
        }
        return paths;
    }
    public static void main(String[] args) {
        TreeNode root = new TreeNode(8);
        root.left = new TreeNode(3);
        root.right = new TreeNode(10);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(6);
        root.left.right.left = new TreeNode(4);
        root.left.right.right = new TreeNode(7);
        root.right.right = new TreeNode(14);
        root.right.right.left = new TreeNode(13);

        List<String> paths = getPaths(root);
        for(String str:paths) System.out.println(str);
    }


}
