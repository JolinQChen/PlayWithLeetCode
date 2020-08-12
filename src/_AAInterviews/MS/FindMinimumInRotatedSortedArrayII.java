package _AAInterviews.MS;

/** 区别在于有没有duplicates*/
public class FindMinimumInRotatedSortedArrayII {
    public int findMin(int[] nums) {
        return findMinHelper(nums, 0, nums.length-1);

    }

    private int findMinHelper(int[] nums, int left, int right) {
        while (left<right) {
            if(nums[left]<nums[right]) return nums[left];
            int mid = left + (right-left)/2;
            if(mid>0 && nums[mid]<nums[mid-1]) return nums[mid];
            if(nums[mid]==nums[right]) return Math.min(findMinHelper(nums, left, mid-1), findMinHelper(nums, mid+1, right));
            if(nums[mid]>nums[right]) left = mid+1;
            else right = mid-1;
        }
        return nums[left];
    }
}
