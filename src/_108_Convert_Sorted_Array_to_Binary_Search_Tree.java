import java.util.*;

/**
 *
 * 将排序数组改写为二叉搜索树而且实现高度平衡
 *
 * 要点：二叉树的中序遍历结果是一个升序数组，因此相当于根据中序遍历结果反推二叉搜索树
 * 下面是一些关于 BST 的知识。
 * 中序遍历不能唯一确定一棵二叉搜索树。
 * 先序和后序遍历不能唯一确定一棵二叉搜索树。
 * 先序/后序遍历和中序遍历的关系：
 * inorder = sorted(postorder) = sorted(preorder)，
 * 中序+后序、中序+先序可以唯一确定一棵二叉树。
 *
 *
 * Given an array where elements are sorted in ascending order, convert it to a height balanced BST.
 *
 * For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the
 * two subtrees of every node never differ by more than 1.
 *
 * Example:
 * Given the sorted array: [-10,-3,0,5,9],
 *
 * One possible answer is: [0,-3,9,-10,null,5], which represents the following height balanced BST:
 *
 *       0
 *      / \
 *    -3   9
 *    /   /
 *  -10  5
 * */

public class _108_Convert_Sorted_Array_to_Binary_Search_Tree {
    /**
     * 高度平衡意味着每次必须选择中间数字作为根节点。这对于奇数个数的数组是有用的，但对偶数个数的数组没有预定义的选择方案。
     * 对于偶数个数的数组，要么选择中间位置左边的元素作为根节点，要么选择中间位置右边的元素作为根节点，
     * 不同的选择方案会创建不同的平衡二叉搜索树。这里始终选择中间位置左边的元素作为根节点。
     *
     * 方法 helper(left, right) 使用数组 nums 中索引从 left 到 right 的元素创建 BST：
     * 如果 left > right，子树中不存在元素，返回空。
     * 找出中间元素：p = (left + right) / 2。
     * 创建根节点：root = TreeNode(nums[p])。
     * 递归创建左子树 root.left = helper(left, p - 1) 和右子树 root.right = helper(p + 1, right)。
     * 返回 helper(0, len(nums) - 1)。
     * */
    public TreeNode sortedArrayToBST(int[] nums) {
        return helper(nums,0,nums.length-1);
    }

    private TreeNode helper(int[] nums, int left, int right){
        if(left>right) return null;
        int middle = (left+right)/2;
        TreeNode root = new TreeNode(nums[middle]);
        System.out.println(nums[middle]);
        root.left = helper(nums,left,middle-1);
        root.right = helper(nums, middle+1, right);
        return root;
    }

}
