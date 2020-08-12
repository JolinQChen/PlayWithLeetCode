package _AAInterviews.MS;
import java.util.*;
/**
 * Input: [2,0,2,1,1,0]
 * Output: [0,0,1,1,2,2]
 *
 * One Pass, 一共只有3种颜色
 * */
public class SortColors {
    public void sortColors(int[] nums) {
        int left = 0, right = nums.length-1, cur = 0;
        while (cur<=right) {
            if(nums[cur]==0) {
                swap(nums, left, cur);
                left++;
                cur++;
            }
            else if(nums[cur]==2) {
                swap(nums, right, cur);
                right--;
            }
            else cur++;
        }
    }
    private void swap(int[] nums, int a, int b){
        int tmp = nums[a];
        nums[a] = nums[b];
        nums[b] = tmp;
    }
}
