package _AAInterviews.Wish;

import java.util.*;
public class SearchInRotatedSortedArray {
    public static int search(int[] nums, int target){
        // first find the pivot and search in two parts
        // find the pivot
        int pivot = findPivot(nums);
        int firstRound = binary(nums, target, 0, pivot-1);
        if(firstRound!=-1) return firstRound;
        return binary(nums, target, pivot, nums.length-1);


    }

    private static int findPivot(int[] nums){
        if(nums.length==1) return 0;
        int left = 0, right = nums.length-1;
        while (left<right && nums[left]>=nums[right]) {
            int mid = left + (right-left)/2;
            if(mid>0 && nums[mid]<nums[mid-1]) return mid;
            if(mid<right && nums[mid]>nums[mid+1]) return mid+1;
            else if(nums[mid]>nums[right]) left=mid+1;
            else right = mid-1;

        }
        return left;
    }

    private static int binary(int[] nums, int target, int left, int right) {
        if(right<left) return -1;
        if(right == left){
            if(nums[right] == target) return right;
            return -1;
        }
        if(target<nums[left] || target > nums[right]) return -1;
        else if(target == nums[left]) return left;
        else if(target == nums[right]) return right;

        int mid = left + (right-left)/2;
        if(nums[mid]==target) return mid;
        else if(nums[mid]>target) return binary(nums, target, left, mid-1);
        else return binary(nums, target, mid+1, right);
    }
}
