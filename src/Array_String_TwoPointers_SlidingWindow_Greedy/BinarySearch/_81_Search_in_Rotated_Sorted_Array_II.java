package Array_String_TwoPointers_SlidingWindow_Greedy.BinarySearch;
// Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
//
//(i.e., [0,0,1,2,2,5,6] might become [2,5,6,0,0,1,2]).
//
//You are given a target value to search. If found in the array return true, otherwise return false.
public class _81_Search_in_Rotated_Sorted_Array_II {
    private int findPivot(int[] nums) {
        int index = 0;
        while(index<nums.length-1 && nums[index]<=nums[index+1]) {
            index++;
        }
        return index; // the end of the first block
    }
    private boolean binarySearch(int[] arr, int l, int r, int target) {
        if(r>=l) {
            int mid = l + (r-l)/2;
            if(arr[mid]==target) return true;
            if(arr[mid]>target) return binarySearch(arr,l,mid-1,target);
            return binarySearch(arr,mid+1,r,target);
        }
        return false;
    }
    public boolean search(int[] nums, int target) {
        int len = nums.length;
        if(len == 0) return false;
        if(len == 1) return target == nums[0];
        // find the end of original nums
        int pivot = findPivot(nums);
        if(target > nums[pivot]) return false;
        else if(pivot<len-1 && target<nums[pivot+1]) return false;
        if(target>=nums[0]) return binarySearch(nums,0,pivot, target);
        else return binarySearch(nums, pivot+1, len-1, target);

    }
}
