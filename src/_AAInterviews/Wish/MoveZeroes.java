package _AAInterviews.Wish;
/**
 * Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order
 * of the non-zero elements.
 *
 * Example:
 *
 * Input: [0,1,0,3,12]
 * Output: [1,3,12,0,0]
 * */
public class MoveZeroes {
    public void moveZeroes(int[] nums) {
        int idx = 0;
        int p = 0;
        while (p<nums.length) {
            if(nums[p]!=0) nums[idx++] = nums[p];
            p++;
        }
        while (idx<nums.length) nums[idx++]=0;
    }
}
