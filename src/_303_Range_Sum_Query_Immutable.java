import java.util.*;
// prefix sum

/**
 * Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.
 * Given nums = [-2, 0, 3, -5, 2, -1]
 *
 * sumRange(0, 2) -> 1
 * sumRange(2, 5) -> -1
 * sumRange(0, 5) -> -3
 * */

public class _303_Range_Sum_Query_Immutable {
    class NumArray {
        private int[] prefix;
        //private int[] data;
        public NumArray(int[] nums) {
            prefix = new int[nums.length+1];
            //prefix[0] = 0;
            //data = nums;
            int count = 0;
            for(int i=0; i<nums.length; i++){
                count += nums[i];
                prefix[i+1] = count;
            }
        }

        public int sumRange(int i, int j) {
            return prefix[j+1]-prefix[i];

        }
    }

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * int param_1 = obj.sumRange(i,j);
 */
}
