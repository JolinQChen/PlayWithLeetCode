package _AAInterviews.Google;
import org.junit.Test;

import java.util.*;
public class Longest_Increasing_subsequence {
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

    public int lengthOfLIS_2(int[] nums) {
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

    @Test
    public void test(){
        int[] nums = new int[]{2,5,6,3,7,8};
        System.out.println(lengthOfLIS(nums));
    }
}
