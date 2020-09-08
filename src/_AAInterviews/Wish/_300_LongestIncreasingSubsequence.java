package _AAInterviews.Wish;
/**
 *
 * Input: [10,9,2,5,3,7,101,18]
 * Output: 4
 * Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.
 *
 * */
public class _300_LongestIncreasingSubsequence {
    public int lengthOfLIS(int[] nums) {
        // use dp
        if(nums.length<=1) return nums.length;
        int[] dp = new int[nums.length];
        dp[0] = 1;
        int longest = 1;
        for(int i=0; i<nums.length; i++) {
            int preMax = 0;
            for(int j=0; j<i; j++) {
                if(nums[j]<nums[i]) preMax = Math.max(preMax, dp[j]);
            }
            dp[i] = 1+preMax;
            longest = Math.max(dp[i], longest);
        }
        return longest;
    }
}
