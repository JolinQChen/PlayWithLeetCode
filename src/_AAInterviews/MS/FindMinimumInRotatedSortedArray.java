package _AAInterviews.MS;
import java.util.*;
// Input: [4,5,6,7,0,1,2]
// Output: 0
public class FindMinimumInRotatedSortedArray {
    public int findMin(int[] nums) {
        int left = 0, right = nums.length-1;
        while (left<right) {
            if(nums[left]<nums[right]) return nums[left];
            int mid = left+(right-left)/2;
            if(mid>0 && nums[mid]<nums[mid-1]) return nums[mid];
            if(nums[mid]>nums[right]) left = mid+1;
            else if(nums[mid]<nums[left]) right = mid-1;
        }
        return nums[left];
    }
}
