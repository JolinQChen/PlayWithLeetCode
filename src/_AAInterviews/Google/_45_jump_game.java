package _AAInterviews.Google;
import java.util.*;
public class _45_jump_game {
    public int jump(int[] nums) {
        if (nums.length<=1) return 0;
        int[] dp = new int[nums.length];
        dp[nums.length-1] = 0;
        for (int i=nums.length-2; i>=0; i--) {
            int nextMin = nums.length+1;
            // get where we can go at most
            for (int nextStep = Math.min(nums.length-1, i+nums[i]); nextStep>i; nextStep--) {
                nextMin = Math.min(nextMin, dp[nextStep]);
            }
            dp[i] = nextMin+1;
        }
        return dp[0];
    }
}
