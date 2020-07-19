package _All;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 给定一棵二叉树，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
 *
 * 输入: [1,2,3,null,5,null,4]
 * 输出: [1, 3, 4]
 * 解释:
 *
 *    1            <---
 *  /   \
 * 2     3         <---
 *  \     \
 *   5     4       <---
 *
 *
 * */

 // Definition for a binary tree node.

public class _199_Binary_Tree_Right_Side_View {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if(root == null) return res;
        //res.add(root.val);
        // 广度优先遍历，但是只将每一层最右边的节点值加入结果中
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int cur_level_remain = 1;
        int next_level_num = 0;
        while(!queue.isEmpty()){
            while(cur_level_remain!=1){
                TreeNode tmp = queue.poll();
                cur_level_remain--;
                if(tmp.left!=null) {
                    queue.add(tmp.left);
                    next_level_num++;
                }
                if(tmp.right!=null){
                    queue.add(tmp.right);
                    next_level_num++;
                }
            }
            //一层结束，将最后一个排出的数加入结果中
            TreeNode tmp = queue.poll();
            res.add(tmp.val);
            if(tmp.left!=null) {
                queue.add(tmp.left);
                next_level_num++;
            }
            if(tmp.right!=null){
                queue.add(tmp.right);
                next_level_num++;
            }
            cur_level_remain = next_level_num;
            next_level_num = 0;
        }
        return res;
    }
}
