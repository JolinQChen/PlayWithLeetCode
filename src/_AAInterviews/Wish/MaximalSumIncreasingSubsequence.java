package _AAInterviews.Wish;
import java.util.*;
// DP

public class MaximalSumIncreasingSubsequence {
    public static int maxSumIS(int[] arr) {
        int largest = arr[0];
        int[] dp = new int[arr.length];
        dp[0] = arr[0];
        for(int i=1; i<arr.length; i++){
            for(int j=0; j<i; j++) {
                if(arr[i]>arr[j]) {
                    dp[i] = Math.max(dp[i], dp[j]+arr[i]);
                    largest = Math.max(largest, dp[i]);
                }
            }
        }
        return largest;
    }
    public static void main(String[] args) {
        int[] arr = {1,101,2,3,100,4,5};
        System.out.println(maxSumIS(arr));
    }
}
