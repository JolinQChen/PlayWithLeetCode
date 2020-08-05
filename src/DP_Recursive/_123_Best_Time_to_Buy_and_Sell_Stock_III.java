package DP_Recursive;

/**
 * 只允许最多两次交易
 * */
public class _123_Best_Time_to_Buy_and_Sell_Stock_III {
    // Approach 1: Bidirectional Dynamic Programming

    // The total profits would be the sum of profits from each subsequence.
    // If we enumerate all possible divisions (or we could consider them as
    // combinations of subsequences), we could find the maximum total profits
    // among them, which is also the desired result of the problem.

    // So we divide this problem into two subproblems, and each subproblem is
    // actually of the same problem of Best Time to Buy and Sell Stock

    /**
     * In dynamic programming algorithms, normally we create an array of one or two dimensions to
     keep the intermediate optimal results. In this problem though, we would use two arrays, with
     one array keeping the results of sequence from left to right and the other array keeping the
     results of sequence from right to left. For the sake of name, we could call it bidirectional
     dynamic programming.
     */
    public int maxProfit1(int[] prices) {
        int len = prices.length;
        if(len<=1) return 0;

        int[] leftDP = new int[len];
        int[] rightDP = new int[len];
        int leftMin = prices[0];
        leftDP[0] = 0;
        rightDP[len-1] = 0;
        int rightMax = prices[len-1];

        // prepare leftDP
        for(int i=1; i<len; i++) {
            leftMin = Math.min(leftMin, prices[i]);
            leftDP[i] = Math.max(leftDP[i-1],prices[i] - leftMin);
        }

        int res = 0;
        for(int i=len-2; i>0; i--) {
            rightMax = Math.max(rightMax, prices[i]);
            rightDP[i] = Math.max(rightMax - prices[i], rightDP[i+1]);
            res = Math.max(res, rightDP[i]+leftDP[i-1]);
        }
        rightMax = Math.max(rightMax, prices[0]);
        rightDP[0] = Math.max(rightMax - prices[0], rightDP[1]);
        res = Math.max(res, rightDP[0]);
        return res;
    }
}
