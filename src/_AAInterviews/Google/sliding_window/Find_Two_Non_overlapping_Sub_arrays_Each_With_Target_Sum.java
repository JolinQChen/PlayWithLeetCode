package _AAInterviews.Google.sliding_window;

import java.util.Arrays;

public class Find_Two_Non_overlapping_Sub_arrays_Each_With_Target_Sum {
    public int minSumOfLengths(int[] arr, int target) {
        // sliding window
        int res = arr.length * 2;
        int[] lenArr = new int[arr.length];
        Arrays.fill(lenArr, arr.length + 1);
        int curSum = 0;
        int left = 0;
        for(int i=0; i<arr.length; i++) {
            curSum += arr[i];
            while(curSum>target) {
                curSum -= arr[left];
                left++;
            }
            if(curSum == target) {
                // found one, check before
                lenArr[i] = i - left + 1;
                if(left>0 && lenArr[left-1]<arr.length+1) {
                    res = Math.min(res, lenArr[i] + lenArr[left-1]);
                }
            }
            if(i>0) lenArr[i] = Math.min(lenArr[i], lenArr[i-1]);

        }
        return res < 2 * arr.length ? res : -1;
    }
}
