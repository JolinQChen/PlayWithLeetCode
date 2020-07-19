package _All;

import java.util.*;
/**
 * The thief has found himself a new place for his thievery again.
 * There is only one entrance to this area, called the "root." Besides
 * the root, each house has one and only one parent house. After a tour,
 * the smart thief realized that "all houses in this place forms a binary tree".
 * It will automatically contact the police if two directly-linked houses were
 * broken into on the same night.
 *
 * Determine the maximum amount of money the thief can rob tonight without
 * alerting the police.
 *
 * Example 1:
 *
 * Input: [3,2,3,null,3,null,1]
 *
 *      3
 *     / \
 *    2   3
 *     \   \
 *      3   1
 *
 * Output: 7
 * Explanation: Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.
 *
 * Example 2:
 *
 * Input: [3,4,5,1,3,null,1]
 *
 *      3
 *     / \
 *    4   5
 *   / \   \
 *  1   3   1
 *
 * Output: 9
 * Explanation: Maximum amount of money the thief can rob = 4 + 5 = 9.
 * */

public class _337_House_Robber_III {
    // 方法1：暴力递归
    public int rob_1(TreeNode root) {
        if (root == null) return 0;

        int money = root.val;
        if (root.left != null) {
            money += (rob_1(root.left.left) + rob_1(root.left.right));
        }

        if (root.right != null) {
            money += (rob_1(root.right.left) + rob_1(root.right.right));
        }

        return Math.max(money, rob_1(root.left) + rob_1(root.right));
    }

    //方法2：
    /**
     * 解法二、记忆化 - 解决重复子问题
     * 针对解法一种速度太慢的问题，经过分析其实现，我们发现爷爷在计算自己能偷多少钱的时候，
     * 同时计算了 4 个孙子能偷多少钱，也计算了 2 个儿子能偷多少钱。这样在儿子当爷爷时，
     * 就会产生重复计算一遍孙子节点。
     *
     * 于是乎我们发现了一个动态规划的关键优化点
     *
     * 重复子问题
     *
     * 我们这一步针对重复子问题进行优化，我们在做斐波那契数列时，使用的优化方案是记忆化，
     * 但是之前的问题都是使用数组解决的，把每次计算的结果都存起来，下次如果再来计算，
     * 就从缓存中取，不再计算了，这样就保证每个数字只计算一次。
     * 由于二叉树不适合拿数组当缓存，我们这次使用哈希表来存储结果，_All.TreeNode 当做 key，
     * 能偷的钱当做 value
     *
     * 解法一加上记忆化优化后代码如下：
     *
     * */
    public int rob_2(TreeNode root) {
        Map<TreeNode, Integer> map = new HashMap<>();
        return robHelper(root, map);
    }

    private int robHelper(TreeNode root, Map<TreeNode, Integer> memo){
        if(root == null) return 0;
        if(memo.containsKey(root)) return memo.get(root);
        int money = root.val;
        if(root.left!=null){
            money = money + robHelper(root.left.left, memo) + robHelper(root.left.right, memo);
        }
        if(root.right!=null) money = money + robHelper(root.right.left, memo) + robHelper(root.right.right, memo);
        memo.put(root, Math.max(money, robHelper(root.left,memo)+robHelper(root.right,memo)));
        return memo.get(root);
    }
}
