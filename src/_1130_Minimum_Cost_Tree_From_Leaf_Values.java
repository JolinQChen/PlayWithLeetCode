import java.util.*;
/**
 * Given an array arr of positive integers, consider all binary trees such that:
 *
 * Each node has either 0 or 2 children;
 *
 * The values of arr correspond to the values of each leaf in an in-order traversal of the tree.
 * (Recall that a node is a leaf if and only if it has 0 children.)
 *
 * The value of each non-leaf node is equal to the product of the largest leaf value in its
 * left and right subtree respectively.
 *
 * Among all possible binary trees considered, return the smallest possible sum of the values
 * of each non-leaf node.  It is guaranteed this sum fits into a 32-bit integer.
 *
 *
 * Input: arr = [6,2,4]
 * Output: 32
 * Explanation:
 * There are two possible trees.  The first has non-leaf node sum 36, and the second has non-leaf
 * node sum 32.
 *
 *     24            24
 *    /  \          /  \
 *   12   4        6    8
 *  /  \               / \
 * 6    2             2   4
 * */

public class _1130_Minimum_Cost_Tree_From_Leaf_Values {
    public int mctFromLeafValues(int[] arr) {
        int n = arr.length;
        // 这道题的核心是:
        // 要知道中序遍历就决定了arr数组（0...n-1）里的第k位元素的所有左边元素（包括它自己）都在左子树里，
        // 而其右边元素都在右子树里, 而此时左右两边子树分别选出最大值的乘积就是此时的根，也就是题目中说的非叶节点
        // 所以我们可以假定从i到j位，最小和可能是：
        // 此刻k位左右两边元素中最大值的乘积 + 子问题k左边(i,k)的最小值 + 子问题k位右边(k+1,j)的最小值
        //即：dp[i][j]=min(dp[i][j], dp[i][k] + dp[k+1][j] + max[i][k]*max[k+1][j])
        //这道题跟leetcode1039一个套路
        //求arr从i到j之间的元素最大值, 保存在max[i][j]中
        //这道题i和j是可以相等的
        int[][] max = new int[n][n];
        for (int j=0;j<n;j++) {
            int maxValue = arr[j];
            for (int i=j;i>=0;i--) {
                maxValue = Math.max(maxValue, arr[i]);
                max[i][j] = maxValue;
            }
        }
        int[][] dp = new int[n][n];
        /**
         * 这里的遍历顺序很重要！！！dp一定要保证后面覆盖前面的
         * */
        for (int j=0; j<n; j++) {
            for (int i=j; i>=0; i--) {
                //k是i到j之间的中间某个值,i<=k<j
                int min = Integer.MAX_VALUE;
                for (int k=i; k+1<=j; k++) {
                    min = Math.min(min,dp[i][k] + dp[k+1][j] + max[i][k]*max[k+1][j]);
                    dp[i][j] = min;
                }
            }
        }
        return dp[0][n-1];
    }
}
