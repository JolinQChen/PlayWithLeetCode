package _AAInterviews.Wish;

public class LargestSumContiguousSubarray {
    public static int findLargestSum(int[] nums) {
        int[] DP = new int[nums.length];
        DP[0] = nums[0];
        int res = DP[0];
        for(int i=1; i<nums.length; i++) {
            DP[i] = DP[i-1]<0?nums[i]:nums[i]+DP[i-1];
            res = Math.max(res, DP[i]);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {-2,-3,4,-1,-2,1,5,-3};
        System.out.println(findLargestSum(nums));
    }

}
