import java.util.*;
/**
 * Given an array with n objects colored red, white or blue, Array_String_TwoPointers_SlidingWindow_Greedy.sort them "in-place"
 * so that objects of the same color are adjacent, with the colors in the order red, white and blue.
 *
 * Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.
 *
 * Note: You are not suppose to use the library's Array_String_TwoPointers_SlidingWindow_Greedy.sort function for this problem.
 *
 * Input: [2,0,2,1,1,0]
 * Output: [0,0,1,1,2,2]
 *
 * Follow up:
 *
 * A rather straight forward solution is a two-pass algorithm using counting Array_String_TwoPointers_SlidingWindow_Greedy.sort.
 * First, iterate the array counting number of 0's, 1's, and 2's, then overwrite array with total
 * number of 0's, then 1's and followed by 2's.
 *
 * Could you come up with a one-pass algorithm using only constant space?
 * */

public class _75_Sort_Colors {
    // 法一：two pass, count Array_String_TwoPointers_SlidingWindow_Greedy.sort，略
    // 法二：one pass
    // 用三个指针（p0, p2 和curr）来分别追踪0的最右边界，2的最左边界和当前考虑的元素。
    /*
  荷兰三色旗问题解
  */
    public void sortColors(int[] nums) {
        // 对于所有 idx < i : nums[idx < i] = 0
        // j是当前考虑元素的下标
        int p0 = 0, curr = 0;
        // 对于所有 idx > k : nums[idx > k] = 2
        int p2 = nums.length - 1;

        int tmp;
        while (curr <= p2) {
            if (nums[curr] == 0) {
                // 交换第 p0个和第curr个元素
                // i++，j++
                tmp = nums[p0];
                nums[p0++] = nums[curr];
                nums[curr++] = tmp;
            }
            else if (nums[curr] == 2) {
                // 交换第k个和第curr个元素
                // p2--
                tmp = nums[curr];
                nums[curr] = nums[p2];
                nums[p2--] = tmp;
            }
            else curr++;
        }
    }
}
