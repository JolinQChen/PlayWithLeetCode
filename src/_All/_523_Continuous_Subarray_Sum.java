package _All;

import java.util.*;
public class _523_Continuous_Subarray_Sum {
    /**
     * Given a list of non-negative numbers and a target integer k, write a function to
     * check if the array has a continuous subarray of size at least 2 that sums up to
     * a multiple of k, that is, sums up to n*k where n is also an integer.
     * */
    public boolean checkSubarraySum(int[] nums, int k) {
        // DP??
        int[] sum = new int[nums.length+1];
        sum[0] = 0;
        sum[1]= nums[0];
        for(int i=2; i<=nums.length; i++){
            sum[i] = sum[i-1]+nums[i-1];
            for(int j=0; j<i-1; j++){
                if((sum[i]-sum[j])%k==0) return true;
            }
        }
        return false;
    }
}
