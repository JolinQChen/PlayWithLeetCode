import java.util.*;
/**
 * 上一道题coin change在#322
 * You are given coins of different denominations and a total amount of money.
 * Write a function to compute the number of combinations that make up that amount.
 * You may assume that you have infinite number of each kind of coin.
 *
 * Input: amount = 5, coins = [1, 2, 5]
 * Output: 4
 * Explanation: there are four ways to make up the amount:
 * 5=5
 * 5=2+2+1
 * 5=2+1+1+1
 * 5=1+1+1+1+1
 *
 * */

public class _518_Coin_Change_2 {
    /**这是错误答案，例如[1,2,5]组合中，计算dp[3]的时候：
     * dp[3]=dp[1]+dp[2]，有重复：1+2=2+1
     * */
    public int change_wrong(int amount, int[] coins) {
        Arrays.sort(coins);
        int[] dp = new int[amount+1];
        dp[0] = 1;
        int len = coins.length;
        for(int i=1; i<=amount; i++){
            if(i<coins[0]) dp[i]=0;
            else {
                for(int j=0; j<len; j++){
                    if(i<coins[j]) break;
                    dp[i] += dp[i-coins[j]];
                }
            }
            System.out.printf("dp[%d] = %d\n",i,dp[i]);
        }
        return dp[amount];
    }

    /**可以当模板记下来，这样不会重复*/
    public int change(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;

        for (int coin : coins) {
            for (int x = coin; x < amount + 1; ++x) {
                dp[x] += dp[x - coin];
            }
        }
        return dp[amount];
    }
}
