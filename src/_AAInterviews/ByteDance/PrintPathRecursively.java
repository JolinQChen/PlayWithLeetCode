package _AAInterviews.ByteDance;
import java.util.*;
public class PrintPathRecursively {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode(int v) {
            this.val = v;
        }
    }

    static private List<TreeNode> list;
    static public void printPath(TreeNode root, int n) {
        list = new LinkedList<>();
        printer(root);
    }

    static private void printer(TreeNode node) {
        if(node == null) return;
        list.add(node);
        if(node.left==null && node.right==null) printList();
        else {
            if(node.left!=null) {
                printer(node.left);
                list.remove(list.size()-1);
            }
            if(node.right!=null) {
                printer(node.right);
                list.remove(list.size()-1);
            }
        }
    }
    static private void printList(){
        for(int i=0; i<list.size()-1; i++) {
            System.out.print(list.get(i).val + " -> ");
        }
        System.out.println(list.get(list.size()-1).val);
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

        printPath(root,4);
    }

}
