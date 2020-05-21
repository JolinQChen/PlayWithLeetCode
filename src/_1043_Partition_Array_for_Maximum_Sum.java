import java.util.*;

/**
 * Given an integer array A, you partition the array into (contiguous) subarrays of length at most K.
 * After partitioning, each subarray has their values changed to become the maximum value of that subarray.
 *
 * Return the largest sum of the given array after partitioning.
 *
 * Example 1:
 *
 * Input: A = [1,15,7,9,2,5,10], K = 3
 * Output: 84
 * Explanation: A becomes [15,15,15,9,10,10,10]
 *
 * Note:
 *
 * 1 <= K <= A.length <= 500
 * 0 <= A[i] <= 10^6
 * */

/** DP动态规划 */

public class _1043_Partition_Array_for_Maximum_Sum {
    public int maxSumAfterPartitioning(int[] A, int K) {
        if(A == null || A.length==0) return 0;
        int len = A.length;
        int[] dp = new int[len+1];
        for(int i=1; i<=len; i++){
            int j = i-1;
            int max = dp[i];
            while((i-j)<=K && j>=0){
                max = Math.max(max, A[j]);
                dp[i] = Math.max(dp[i],dp[j] + max*(i-j));
                j--;
            }
        }
        return dp[len];
    }
}
