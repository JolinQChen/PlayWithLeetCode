package tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class _863_All_Nodes_Distance_K_in_Binary_Tree {
    private Map<TreeNode, TreeNode> map;
    private List<Integer> list = new ArrayList<>();
    private TreeNode root;
    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        map = new HashMap<>();
        this.root = root;
        //TreeNode last = new TreeNode();
        helper(null, target, K);

        return list;
    }
    private void helper(TreeNode lastTarget, TreeNode curTarget, int K){

        if(curTarget!=null){

            if(K==0) {
                list.add(curTarget.val);
                return;
            }
            //向下找
            if(curTarget.left!=null && curTarget.left!=lastTarget){
                helper(curTarget, curTarget.left,K-1);
            }
            if(curTarget.right!=null && curTarget.right!=lastTarget){
                helper(curTarget,curTarget.right,K-1);
            }
            //向上找
            if(curTarget!=root){
                if(!map.containsKey(curTarget)) findParent(root, curTarget);
                TreeNode parent = map.get(curTarget);
                for(TreeNode tn:map.keySet()){
                    System.out.println(tn.val + " : "+map.get(tn).val);
                }
                if(parent!=lastTarget){
                    helper(curTarget, parent, K-1);
                }
            }
        }
    }
    private void findParent(TreeNode start, TreeNode target){
        if(map.containsKey(target)) return;
        if(target!=start && start!=null){
            if(start.left!=null){
                if(start.left==target) {
                    map.put(target, start);
                }
                else {
                    map.put(start.left, start);
                    findParent(start.left, target);
                }
            }
            if(start.right!=null) {
                if(start.right==target) {
                    map.put(target, start);
                }
                else {
                    map.put(start.right, start);
                    findParent(start.right, target);
                }
            }
        }
    }
}
