package _AAInterviews.VMware;
import java.util.*;
public class _53_MaximumSubarray {
    public int maxSubArray(int[] nums) {
        if(nums.length == 0) return 0;

        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int res = nums[0];
        for(int i =1;i<nums.length; i++) {
            dp[i] = Math.max(dp[i-1]+nums[i],nums[i]);
            res = Math.max(res, dp[i]);
        }
        return res;
    }
}
