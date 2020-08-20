package _AAInterviews.MS;
/**
 * Input: [10,9,2,5,3,7,101,18]
 * Output: 4
 * Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.
 * */
// Note: Arrays.binarySearch() method returns index of the search key, if it is contained in the array,
// else it returns (-(insertion point) - 1).
import java.util.Arrays;
public class LongestIncreasingSubsequence {
    // DP法1
    private int length1(int[] nums) {
        int len = nums.length;
        if(len<=1) return len;
        int[] dp = new int[len];
        int longest = 0;
        dp[0] = 1;
        for(int i=1; i<len; i++) {
            int preMax = 0;
            for(int j=0; j<i; j++) {
                if(nums[j]<nums[i]) {
                    preMax = Math.max(preMax,dp[j]);
                }
            }
            dp[i] = preMax+1;
            longest = Math.max(longest,dp[i]);
        }
        return longest;
    }

    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        int len = 0;
        for (int num : nums) {
            int i = Arrays.binarySearch(dp, 0, len, num);
            if (i < 0) {
                i = -(i + 1);
            }
            dp[i] = num;
            if (i == len) {
                len++;
            }
        }
        return len;
    }
}
