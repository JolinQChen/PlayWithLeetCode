package _AAInterviews.MS;

public class SearchInRotatedSortedArray {
    public static int search(int[] nums, int target){
        //先找到旋转的节点pivot
        int pivot = 0;
        int len = nums.length;
        if(len == 0) return -1;
        while(pivot<len-1 && nums[pivot]<nums[pivot+1]) pivot++;
        //0-pivot(包括pivot)为前半截，pivot-len-1为后半截，分别进行binary search
        return binary(nums, target, 0, pivot)==-1?binary(nums, target, pivot+1, len-1) : binary(nums, target, 0, pivot);
    }

    private static int binary(int[] nums, int target, int start, int end){
        if(end<start) return -1;
        if(end ==start && target != nums[start]) return -1;
        if(target<nums[start] || target > nums[end]) return -1;
        else if(target == nums[start]) return start;
        else if(target == nums[end]) return end;
        int middle = (start+end)/2;
        if(target == nums[middle]) return middle;
        else if(target<nums[middle]) return binary(nums, target, start, middle-1);
        else return binary(nums, target, middle+1, end);
    }
}
