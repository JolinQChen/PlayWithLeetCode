package _AAInterviews.ByteDance;
import java.util.*;
public class Binary_Tree_Right_Side_View {
    class TreeNode {
        TreeNode left;
        TreeNode right;
        int val;
        TreeNode(int v){
            this.val = v;
        }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if(root==null) return list;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()) {
            int size = queue.size();
            while(size>0) {
                TreeNode curr = queue.poll();
                if(curr.left != null) queue.add(curr.left);
                if(curr.right != null) queue.add(curr.right);
                size--;
                if(size == 0) list.add(curr.val);
            }
        }
        return list;
    }
}
