package _AAInterviews.Amazon;

/**
 * Given a list of unique integers nums, construct a BST from it (you need to insert nodes
 * one-by-one with the given order to get the BST) and find the distance between two nodes node1
 * and node2. Distance is the number of edges between two nodes. If any of the given nodes does
 * not appear in the BST, return -1.
 *
 * Input: nums = [2, 1, 3], node1 = 1, node2 = 3
 * Output: 2
 * Explanation:
 *      2
 *    /   \
 *   1     3
 * */
public class DistanceBetweenNodesInBST {
    private static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x){
            this.val = x;
        }
    }

    private static TreeNode buildBST(TreeNode root, int node) {
        if(root == null){
            root = new TreeNode(node);
        }
        else if(root.val < node) {
            if(root.right == null) {
                root.right = new TreeNode(node);
            }
            else buildBST(root.right, node);
        }
        else {
            if(root.left==null) root.left = new TreeNode(node);
            else buildBST(root.left, node);
        }
        return root;
    }

    private static int distance(TreeNode lca, int num) {
        int res = 0;
        while (lca!=null && lca.val!=num) {
            res++;
            if(lca.val>num) lca = lca.left;
            else lca = lca.right;
        }
        if(lca == null) return -1;
        else return res;
    }

    public static int findDistance(int[] nums, int num1, int num2){
        TreeNode root = null;
        for(int num:nums) {
            root = buildBST(root, num);
        }
        // find common ancestor
        TreeNode p = root;
        while (p!=null) {
            if(p.val>num1 && p.val>num2) p = p.left;
            else if(p.val<num1 && p.val<num2) p = p.right;
            else break;
        }
        if(p==null) return 0;
        int d1 = distance(p,num1);
        int d2 = distance(p,num2);
        if(d1<0 || d2<0) return 0;
        return d1+d2;
    }

    public static void main(String[] args) {
        int[] nums = {3,1,6,-1,2,4,10,5,9};
        int res = findDistance(nums, -1,9);
        System.out.println(res);
    }

}
