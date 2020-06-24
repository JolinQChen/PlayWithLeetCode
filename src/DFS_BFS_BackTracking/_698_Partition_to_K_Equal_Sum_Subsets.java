package DFS_BFS_BackTracking;

import java.util.Arrays;

/**
 *
 * Given an array of integers nums and a positive integer k,
 * find whether it's possible to divide this array into k
 * non-empty subsets whose sums are all equal.
 *
 * */
public class _698_Partition_to_K_Equal_Sum_Subsets {
    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = 0;
        for(int i:nums) sum+=i;
        if(sum%k > 0) return false;
        int target = sum/k;
        Arrays.sort(nums);
        int searchPoint = nums.length-1;
        if(nums[searchPoint]>target) return false;
        while(searchPoint>=0 && nums[searchPoint]==target){
            searchPoint--;
            k--;
        }
        if(k==0) return true;
        return helper(nums, new int[k], k, searchPoint, target);
    }

    private boolean helper(int[] nums, int[] buckets, int k, int searchPoint, int target){
        if(searchPoint<0) return true;
        int cur = nums[searchPoint];
        for(int i=0; i<k; i++){
            if(cur+buckets[i]<=target){
                // put in this bucket
                buckets[i] += cur;
                if(helper(nums, buckets, k, searchPoint-1, target)) return true;
                buckets[i] -= cur;
            }
            if(buckets[i] == 0) break;
        }
        return false;
    }
}
