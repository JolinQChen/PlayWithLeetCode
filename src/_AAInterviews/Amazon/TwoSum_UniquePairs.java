package _AAInterviews.Amazon;

import java.util.Arrays;

public class TwoSum_UniquePairs {
    public static int uniquePairs(int[] nums, int target){
        if(nums.length<=1) return 0;
        int res = 0;
        Arrays.sort(nums);
        int left = 0, right = nums.length-1;
        while (left<right) {
            int l = nums[left];
            int r = nums[right];
            if(l+r==target) {
                res++;
                while (left<right && (nums[left]==l || nums[right]==r)) {
                    if(nums[left]==l) left++;
                    if(nums[right]==r) right--;
                }
            }
            else if(l+r>target) r--;
            else l++;
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {1, 5, 1, 5};
        int target = 6;
        int res = uniquePairs(nums,target);
        System.out.println(res);
    }
}
