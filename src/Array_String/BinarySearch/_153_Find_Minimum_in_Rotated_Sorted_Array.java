package Array_String.BinarySearch;
/*
Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

(i.e.,  [0,1,2,4,5,6,7] might become  [4,5,6,7,0,1,2]).

Find the minimum element.

You may assume no duplicate exists in the array.
*/

public class _153_Find_Minimum_in_Rotated_Sorted_Array {
    public int findMin(int[] nums) {
        // binary search
        if(nums.length == 0) return -1;
        if(nums.length==1) return nums[0];
        int left = 0;
        int right =nums.length-1;
        int min = nums[0];
        while(left<right){
            if(right-left==1) return Math.min(Math.min(nums[left],nums[right]),min);
            int middle = (left+right)/2;
            if(nums[middle]<nums[left]) right = middle;
            else left = middle;
        }
        return nums[left];

    }


}
