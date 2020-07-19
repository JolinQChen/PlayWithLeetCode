package _All;

import java.util.*;
public class _209_Minimum_Size_Subarray_Sum {
    //法一：优化的暴力法 O(n^2)
    public int minSubArrayLen_1(int s, int[] nums) {
        int len = nums.length;
        if(len==0) return 0;
        int[] sum = new int[len];
        sum[0] = nums[0];
        for(int i=1; i<len; i++) sum[i] = sum[i-1]+nums[i];
        if(sum[len-1]<s) return 0;
        int gap = len;

        for(int i=0; i<len; i++){
            for(int j=i; j<len; j++){
                // 从i到j
                if(sum[j]-sum[i]+nums[i]>=s && j-i+1<gap) {
                    gap = j-i+1;
                    break;
                }
            }
        }
        return gap;
    }

    //法二：sliding window
    /**
     * 找一个范围使得其值满足某个条件，然后就会想到滑动窗口，也就是用双指针的方法。
     *
     * 用双指针 left 和 right 表示一个窗口。
     *
     * right 向右移增大窗口，直到窗口内的数字和大于等于了 s。进行第 2 步。
     * 记录此时的长度，left 向右移动，开始减少长度，每减少一次，就更新最小长度。直到当前窗口内的数字和小于了 s，回到第 1 步。
     * */
    public int minSubArrayLen_2(int s, int[] nums) {
        int len = nums.length;
        if(len == 0) return 0;
        int left = 0, right = 0;
        int sum = nums[0];
        int gap = 0;
        while(right<len){
            if(sum<s) {
                right++;
                if(right<len) sum += nums[right];
            }
            else {
                while (left<=right && sum >=s){
                    sum -= nums[left];
                    left++;
                }
                if(gap==0) gap = right-left+2;
                else gap = Math.min(gap, right-left+2);
            }
        }
        return gap;
    }

}
