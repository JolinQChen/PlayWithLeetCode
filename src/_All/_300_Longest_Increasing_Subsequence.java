package _All;
import java.util.*;
/**
 * Given an integer array nums, return the length of the longest strictly increasing subsequence.
 *
 * A subsequence is a sequence that can be derived from an array by deleting some or no elements without changing
 * the order of the remaining elements. For example, [3,6,2,7] is a subsequence of the array [0,3,1,6,2,2,7].
 *
 * Input: nums = [10,9,2,5,3,7,101,18]
 * Output: 4
 * Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.
 * */

public class _300_Longest_Increasing_Subsequence {
    // 1. DP
    public int lengthOfLIS_dp(int[] nums) {
        int len = nums.length;
        if(len<=1) return len;
        int[] dp = new int[len];
        dp[0] = 1;
        int longest = 0;
        for(int i=1; i<len; i++) {
            int preMax = 0;
            for(int j=0;j <i; j++) {
                if(nums[j]<nums[i]) {
                    preMax = Math.max(preMax, dp[j]);
                }
            }
            dp[i] = preMax+1;
            longest = Math.max(longest, dp[i]);
        }
        return longest;
    }
}
