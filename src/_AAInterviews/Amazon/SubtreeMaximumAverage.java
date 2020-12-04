package _AAInterviews.Amazon;

public class SubtreeMaximumAverage {
    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    private class Couple {
        int count;
        double total;
        public Couple(int count, double total) {
            this.count = count;
            this.total = total;
        }
    }

    double max;
    public double maximumAverageSubtree(TreeNode root) {
        max = Integer.MIN_VALUE;
        helper(root);
        return max;
    }
    private Couple helper(TreeNode root) {
        // return {totalValue, count}
        if(root == null) return new Couple(0,0);
        Couple l = helper(root.left);
        Couple r = helper(root.right);
        Couple cur = new Couple(l.count+r.count+1, l.total+r.total+root.val);
        max= Math.max(cur.total/cur.count, max);
        return cur;
    }
}
