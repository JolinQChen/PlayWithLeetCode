package _AAInterviews.Wish;
/**
 * You are given coins of different denominations and a total amount of money. Write a function to
 * compute the number of combinations that make up that amount. You may assume that you have infinite
 * number of each kind of coin.
 * */
public class CoinChange2 {
    public int change(int amount, int[] coins) {
        int[] DP = new int[amount+1];
        DP[0] = 1;
        for(int coin:coins) {
            for(int idx = coin; idx<=amount; idx++) DP[idx] += DP[idx-coin];
        }
        return DP[amount];

    }
}
