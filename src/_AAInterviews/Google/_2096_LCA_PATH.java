package _AAInterviews.Google;

import tree.TreeNode;

public class _2096_LCA_PATH {
    TreeNode lca;
    public String getDirections(TreeNode root, int startValue, int destValue) {
        StringBuilder sb = new StringBuilder();
        find(root, startValue, destValue);
        System.out.println(lca.val);
        if(lca == null) return sb.toString();
        // retrieve startValue
        retrieveStart(sb, lca, startValue);
        retrieveDest(sb, lca, destValue);
        return sb.toString();
    }

    private boolean find(TreeNode node, int start, int dest) {
        if(node==null) return false;
        boolean sameNode = node.val == start || node.val == dest;
        boolean leftFind = find(node.left, start, dest);
        boolean rightFind = find(node.right, start, dest);
        if(sameNode && (leftFind || rightFind) || leftFind && rightFind) {
            lca = node;
            return true;
        }
        return leftFind || rightFind || sameNode;
    }

    private boolean retrieveStart(StringBuilder sb, TreeNode node, int start) {
        if(node==null) return false;
        if(node.val == start) return true;
        sb.append("U");
        if(retrieveStart(sb, node.left, start)) {
            return true;
        }
        sb.deleteCharAt(sb.length()-1);
        sb.append("U");
        if(retrieveStart(sb, node.right, start)) return true;
        sb.deleteCharAt(sb.length()-1);
        return false;
    }

    private boolean retrieveDest(StringBuilder sb, TreeNode node, int dest) {
        if(node==null) return false;
        if(node.val == dest) return true;
        sb.append("L");
        if(retrieveDest(sb, node.left, dest)) {
            return true;
        }
        sb.deleteCharAt(sb.length()-1);
        sb.append("R");
        if(retrieveDest(sb, node.right, dest)) return true;
        sb.deleteCharAt(sb.length()-1);
        return false;
    }
}
