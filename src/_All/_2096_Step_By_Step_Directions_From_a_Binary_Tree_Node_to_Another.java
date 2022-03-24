package _All;
import apple.laf.JRSUIUtils;

import java.util.*;
public class _2096_Step_By_Step_Directions_From_a_Binary_Tree_Node_to_Another {
    /** DFS parent memory exceed */
    class MyTreeNode{
        MyTreeNode left;
        MyTreeNode right;
        MyTreeNode parent;
        int val;
        public MyTreeNode(int val){
            this.val = val;
        }
    }
    private MyTreeNode startMyNode;
    private MyTreeNode targetMyNode;
    private int startValue;
    private int destValue;
    private Set<Integer> visited;
    private String path;
    public String getDirections(TreeNode root, int startValue, int destValue) {
        // BFS find next
        // retrieve tree and create treeNode with pointer to parent
        this.startValue = startValue;
        this.destValue = destValue;
        MyTreeNode newRoot = transformNode(root);
        if(startMyNode==null || targetMyNode==null) return "";
        // DFS starting from start node
        visited = new HashSet<>();
        findPath(startMyNode, "");
        return path;
    }
    private MyTreeNode transformNode(TreeNode node) {
        if(node==null) return null;
        MyTreeNode res = new MyTreeNode(node.val);
        res.left = transformNode(node.left);
        res.right = transformNode(node.right);
        if(res.left!=null) {
            res.left.parent = res;
        }
        if(res.right!=null) {
            res.right.parent = res;
        }
        if(res.val == startValue) startMyNode = res;
        else if(res.val == destValue) targetMyNode = res;
        return res;
    }
    private boolean findPath(MyTreeNode curNode, String curPath){
        if(curNode == null) return false;
        if(curNode.val== targetMyNode.val) {
            path = curPath;
            return true;
        }
        if(visited.contains(curNode.val)) return false;
        visited.add(curNode.val);
        boolean upFind = findPath(curNode.parent, curPath+"U");
        if(upFind) return true;
        boolean leftFind = findPath(curNode.left, curPath+"L");
        if(leftFind) return true;
        return findPath(curNode.right, curPath+"R");
    }
    /////////////////////////////////////////////////////////////
    /** Method 2: LCA: find lowest common ancestor*/
    private TreeNode lca;
    public String getDirections2(TreeNode root, int startValue, int destValue) {
        findLCA(root, startValue, destValue);
        StringBuilder startPath = new StringBuilder();
        StringBuilder destPath = new StringBuilder();
        findStartNode(lca, startValue, startPath);
        findDestNode(lca, destValue, destPath);
        return startPath.append(destPath).toString();
    }

    private Boolean findStartNode(TreeNode node, int val, StringBuilder path) {
        if(node==null) return false;
        if(node.val == val)
            return true;
        Boolean res = false;
        path.append("U");
        res = findStartNode(node.left, val, path);
        if(res) return true;
        path.deleteCharAt(path.length()-1);
        path.append("U");
        res = findStartNode(node.right, val, path);
        if(res) return true;
        path.deleteCharAt(path.length()-1);
        return false;
    }

    private Boolean findDestNode(TreeNode node, int val, StringBuilder path) {
        if(node==null) return false;
        if(node.val == val)
            return true;
        path.append("L");
        Boolean res = findDestNode(node.left, val, path);
        if(res) return true;
        path.deleteCharAt(path.length()-1);
        path.append("R");
        res = findDestNode(node.right, val, path);
        if(res) return true;
        path.deleteCharAt(path.length()-1);
        return false;
    }

    private boolean findLCA(TreeNode node, int start, int end) {
        if(node==null) return false;
        boolean sameNode = node.val == start || node.val == end;
        boolean findLeft = findLCA(node.left, start, end);
        boolean findRight = findLCA(node.right, start, end);
        if(findLeft && findRight || (findLeft || findRight)&&sameNode) {
            lca = node;
            return true;
        }
        return findLeft || findRight || sameNode;
    }



}


