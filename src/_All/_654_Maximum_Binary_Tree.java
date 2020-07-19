package _All;

/**
 * Given an integer array with no duplicates. A maximum tree building on this array is defined as follow:
 *
 * 1. The root is the maximum number in the array.
 * 2. The left subtree is the maximum tree constructed from left part subarray divided by the maximum number.
 * 3. The right subtree is the maximum tree constructed from right part subarray divided by the maximum number.
 *
 * Construct the maximum tree by the given array and output the root node of this tree.
 *
 * Input: [3,2,1,6,0,5]
 * Output: return the tree root node representing the following tree:
 *
 *       6
 *     /   \
 *    3     5
 *     \    /
 *      2  0
 *        \
 *         1
 * The size of the given array will be in the range [1,1000].
 * */

public class _654_Maximum_Binary_Tree {
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        TreeNode res = null;
        //preorder(res);
        for(int i=0; i<nums.length;i++){
            res = constructHelper(nums,i,res);
            System.out.println("current pos: "+i);
            preorder(res);
        }
        return res;
    }

    private TreeNode constructHelper(int[] num, int pos, TreeNode curRoot){
        // pos表示下一个需要加入的节点位置
        //System.out.println("pos: "+pos);
        if(pos == num.length) return curRoot;
        if(curRoot == null){
            TreeNode newRoot = new TreeNode(num[pos]);
            return newRoot;
        }
        else if(num[pos]>curRoot.val){
            TreeNode newRoot = new TreeNode(num[pos]);
            newRoot.left = curRoot;
            return newRoot;
        }
        else{
            curRoot.right = constructHelper(num, pos, curRoot.right);
            return curRoot;
        }

    }

    private void preorder(TreeNode root){
        System.out.println(root.val);
        if(root.left!=null) preorder(root.left);
        if(root.right!=null) preorder(root.right);

    }


    public static void main(String[] args){
        int[] nums = {3,2,1,6,0,5};
        _654_Maximum_Binary_Tree test = new _654_Maximum_Binary_Tree();
        test.constructMaximumBinaryTree(nums);
    }
}
