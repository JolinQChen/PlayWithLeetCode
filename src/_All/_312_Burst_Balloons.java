package _All;

/**
 * Input: nums = [3,1,5,8]
 * Output: 167
 * Explanation:
 * nums = [3,1,5,8] --> [3,5,8] --> [3,8] --> [8] --> []
 * coins =  3*1*5    +   3*5*8   +  1*3*8  + 1*8*1 = 167
 *
 * */

public class _312_Burst_Balloons {
    public int maxCoins(int[] nums) {
        int len = nums.length;
        int[] balloonRecords = new int[len+2];
        balloonRecords[0] = balloonRecords[len+1] = 1;
        for(int i=1; i<=len; i++) {
            balloonRecords[i] = nums[i-1];
        }
        int[][] memo = new int[len+2][len+2]; //memo[i][j]: maxCoins starting at i and end at j
        return dpHelper(balloonRecords, memo, 0, len+1);
    }

    private int dpHelper(int[] balloonRecords, int[][] memo, int start, int end){
        if(start+1==end) return 0; // break condition
        if(memo[start][end]>0) return memo[start][end];
        int maxCoin = 0;
        // suppose ith balloon is the last one to burst
        for(int i=start+1; i<end; i++) {
            maxCoin = Math.max(maxCoin, balloonRecords[start] * balloonRecords[i] * balloonRecords[end] + dpHelper(balloonRecords, memo, start,i) + dpHelper(balloonRecords, memo, i, end));
        }
        memo[start][end] = maxCoin;
        return maxCoin;
    }
}
