package _All;

import java.util.*;
/**
 * You are given coins of different denominations and a total amount of money amount.
 * Write a function to compute the fewest number of coins that you need to make up
 * that amount. If that amount of money cannot be made up by any combination of the
 * coins, return -1.
 *
 * Input: coins = [1, 2, 5], amount = 11
 * Output: 3
 * Explanation: 11 = 5 + 5 + 1
 * */

public class _322_Coin_Change {
    //Map<Integer, Integer> map = new HashMap<>();
    public int coinChange(int[] coins, int amount) {
        //贪心一下？从大到小试下去
        // DP?
        Arrays.sort(coins);
        int len = coins.length;
        int[] dp = new int[amount+1];
        for(int i=1; i<=amount; i++) {
            if(i<coins[0]) dp[i]=-1;
            else {
                int count = Integer.MAX_VALUE;
                for(int j=0; j<len; j++){
                    if(i<coins[j]) break;
                    if(dp[i-coins[j]]>=0) {
                        count = Math.min(count, dp[i-coins[j]]);
                    }
                }
                dp[i] = count==Integer.MAX_VALUE?-1:count+1;
            }
        }
        return dp[amount];
    }
}
