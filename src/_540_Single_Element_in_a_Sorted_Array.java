import java.util.*;
public class _540_Single_Element_in_a_Sorted_Array {
    /**
     * Follow up: Your solution should run in O(log n) time and O(1) space.
     * */
    public int singleNonDuplicate_1(int[] nums) {
        //异或方法，与sorted条件无关
        int res = 0;
        for(int num:nums) res ^= num;
        return res;
    }

    public int singleNonDuplicate_2(int[] nums) {
        // 如果用到sorted条件，而且O(logn)则应该是binary search
        if(nums.length==1) return nums[0];
        int left = 0, right = nums.length-1;
        while (left<right){
            int mid = left + (right-left)/2;
            if(mid==0 || (nums[mid]!=nums[mid-1] && nums[mid]!=nums[mid+1])) return nums[mid];
            if(nums[mid]==nums[mid-1]) {
                // archive to left part
                if((mid-left+1)%2==1) right = mid;
                else left = mid+1;
            }
            else {
                // archive to right part
                if((right-mid+1)%2==1) left = mid;
                else right = mid-1;
            }
        }
        return nums[left];
    }
}
