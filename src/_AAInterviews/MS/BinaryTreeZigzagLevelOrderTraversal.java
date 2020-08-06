package _AAInterviews.MS;
import java.util.*;

/**
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 *
 * [
 *   [3],
 *   [20,9],
 *   [15,7]
 * ]
 *
 * */

public class BinaryTreeZigzagLevelOrderTraversal {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if(root == null) return res;
        Deque<TreeNode> queue = new LinkedList<>();
        queue.addLast(root);
        int flag = 1;
        while(!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> list = new ArrayList<>();
            while(size>0) {
                size--;
                if(flag>0) {
                    TreeNode cur = queue.poll();
                    list.add(cur.val);
                    if(cur.left!=null) queue.add(cur.left);
                    if(cur.right!=null) queue.add(cur.right);
                }
                else {
                    TreeNode cur = queue.pollLast();
                    list.add(cur.val);
                    if(cur.right!=null) queue.addFirst(cur.right);
                    if(cur.left!=null) queue.addFirst(cur.left);
                }
            }
            flag = -flag;
            res.add(list);
        }
        return res;
    }
}
