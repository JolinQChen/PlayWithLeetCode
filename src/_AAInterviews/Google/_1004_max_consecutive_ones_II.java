package _AAInterviews.Google;

public class _1004_max_consecutive_ones_II {
    public int longestOnes(int[] nums, int k) {
        int left = 0;
        int right = 0;
        int remain = k;
        int max = 0;
        while(right<nums.length) {
            while(right<nums.length) {
                if(nums[right]==0) {
                    remain--;
                    if(remain<0) break;
                }
                right++;
            }
            max = Math.max(max, right-left);

            while(left<right && nums[left]==1) left++;
            left++;
            remain = 1;
        }
        return max;
    }
}
