package tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given a binary tree, write a function to get the maximum width of the given tree.
 * The width of a tree is the maximum width among all levels.
 * The binary tree has the same structure as a full binary tree, but some nodes are null.
 *
 * The width of one level is defined as the length between the end-nodes
 * (the leftmost and right most non-null nodes in the level, where the null nodes between
 * the end-nodes are also counted into the length calculation.
 *
 * Input:
 *
 *            1
 *          /   \
 *         3     2
 *        / \     \
 *       5   3     9
 *
 * Output: 4
 * Explanation: The maximum width existing in the third level with the length 4 (5,3,null,9).
 *
 * Input:
 *
 *           1
 *          /
 *         3
 *        / \
 *       5   3
 *
 * Output: 2
 * Explanation: The maximum width existing in the third level with the length 2 (5,3).
 * */

public class _662_Maximum_Width_of_Binary_Tree {
    private class NodeAnnotation{
        private int index;
        private TreeNode node;
        public NodeAnnotation(int _index, TreeNode _node){
            this.index = _index;
            this.node = _node;
        }
    }

    public int widthOfBinaryTree(TreeNode root) {
        if(root == null) return 0;
        Queue<NodeAnnotation> queue = new LinkedList<>();
        queue.add(new NodeAnnotation(0,root));
        int res = 0;
        while(!queue.isEmpty()){
            //get the left-most node and add its children to queue
            NodeAnnotation left = queue.poll();
            NodeAnnotation right = left;
            int size = queue.size();
            if(left.node.left!=null) queue.add(new NodeAnnotation(left.index*2, left.node.left));
            if(left.node.right!=null) queue.add(new NodeAnnotation(left.index*2+1, left.node.right));
            while(size>0){
                size--;
                right = queue.poll();
                if(right.node.left!=null) queue.add(new NodeAnnotation(right.index*2, right.node.left));
                if(right.node.right!=null) queue.add(new NodeAnnotation(right.index*2+1, right.node.right));
            }
            res = Math.max(res, right.index-left.index+1);
        }
        return res;
    }

}
