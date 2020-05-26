package tree;

import java.util.*;

/**
 * Given a binary tree, return the vertical order traversal of its nodes' values.
 * (ie, from top to bottom, column by column).
 *
 * If two nodes are in the same row and column, the order should be from left to right.
 *
 * Input: [3,9,8,4,0,1,7]
 *
 *      3
 *     /\
 *    /  \
 *    9   8
 *   /\  /\
 *  /  \/  \
 *  4  01   7
 *
 * Output:
 *
 * [
 *   [4],
 *   [9],
 *   [3,0,1],
 *   [8],
 *   [7]
 * ]
 *
 * Input: [3,9,8,4,0,1,7,null,null,null,2,5] (0's right child is 2 and 1's left child is 5)
 *
 *      3
 *     /\
 *    /  \
 *    9   8
 *   /\  /\
 *  /  \/  \
 *  4  01   7
 *     /\
 *    /  \
 *    5   2
 *
 * Output:
 *
 * [
 *   [4],
 *   [9,5],
 *   [3,0,1],
 *   [8,2],
 *   [7]
 * ]
 * */

public class _314_Binary_Tree_Vertical_Order_Traversal {

    class Pair{
        public TreeNode node;
        public int index;
        public Pair(TreeNode node, int index){
            this.node = node;
            this.index = index;
        }
    }
    // BFS
    public List<List<Integer>> verticalOrder(TreeNode root) {
        if(root == null) return new ArrayList<>();
        Map<Integer, List<Integer>> map = new HashMap<>();
        int left_index = 0;
        int right_index = 0;
        Queue<Pair> queue = new LinkedList<>();
        queue.add(new Pair(root,0));
        while(!queue.isEmpty()){
            Pair cur = queue.poll();
            left_index = Math.min(left_index, cur.index);
            right_index = Math.max(right_index, cur.index);
            List<Integer> curList = map.getOrDefault(cur.index, new ArrayList<>());
            curList.add(cur.node.val);
            map.put(cur.index, curList);
            if(cur.node.left!=null){
                queue.add(new Pair(cur.node.left,cur.index-1));
            }
            if(cur.node.right!=null){
                queue.add(new Pair(cur.node.right,cur.index+1));
            }
        }
        List<List<Integer>> res = new ArrayList<>();
        for(int i=left_index; i<=right_index; i++){
            res.add(map.get(i));
        }
        return res;
    }


}
