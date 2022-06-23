package _AAInterviews.Google;

import org.junit.Test;
import tree.TreeNode;
import java.util.*;

public class _366_find_leaves_of_binary_tree {
    Map<TreeNode, Integer> dpMap;
    public List<List<Integer>> findLeaves(TreeNode root) {
        dpMap = new HashMap<>();
        TreeNode node = root;
        int N = sortLayer(node);
        List<List<Integer>> resList = new ArrayList<>();
        for(int i=0; i<=N; i++) resList.add(new ArrayList<>());
        for(TreeNode curNode:dpMap.keySet()) {
            resList.get(dpMap.get(curNode)).add(curNode.val);
        }
        return resList;
    }

    private int sortLayer(TreeNode node) {
        if(dpMap.containsKey(node)) return dpMap.get(node);
        else {
            if(node.left == null && node.right==null) {
                dpMap.put(node, 0);
                return 0;
            }
            if(node.left==null) {
                int cur = sortLayer(node.right)+1;
                dpMap.put(node, cur);
                return cur;
            }
            if(node.right==null) {
                int cur = sortLayer(node.left) + 1;
                dpMap.put(node, cur);
                return cur;
            }
            int cur = Math.max(sortLayer(node.left), sortLayer(node.right)) + 1;
            dpMap.put(node, cur);
            return cur;
        }
    }
    @Test
    public void test(){

    }
}
