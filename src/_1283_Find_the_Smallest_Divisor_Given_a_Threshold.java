import java.util.*;
public class _1283_Find_the_Smallest_Divisor_Given_a_Threshold {
    public int smallestDivisor(int[] nums, int threshold) {
        Arrays.sort(nums);
        int len = nums.length;
        int left = divide(nums[len-1],threshold);
        int right = nums[len-1];
        while(left<right){
            int mid = left + (right-left)/2;
            int tmp = 0;
            for(int num:nums) tmp += divide(num, mid);
            if(tmp<=threshold) right = mid;
            else left = mid+1;
        }
        return left;
    }

    private int divide(int a, int b){
        return a%b==0?a/b:a/b+1;
    }
}
