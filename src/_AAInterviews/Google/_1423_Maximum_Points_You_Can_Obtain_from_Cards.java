package _AAInterviews.Google;

public class _1423_Maximum_Points_You_Can_Obtain_from_Cards {
    public int maxScore(int[] cardPoints, int k) {
        // dp
        // two dp arrays, left_dp[i]: i as left-most card
        int res = 0;
        int[] leftDP = new int[k+1];
        int[] rightDP = new int[k+1];
        leftDP[0]=rightDP[0]=0;
        leftDP[1] = cardPoints[0];
        rightDP[1] = cardPoints[cardPoints.length - 1];
        for(int i=2; i<=k; i++) {
            leftDP[i] = cardPoints[i-1] + leftDP[i-1];
            rightDP[i] = cardPoints[cardPoints.length-i] + rightDP[i-1];
        }

        for(int i=0; i<=k; i++) {
            res = Math.max(res, leftDP[i]+rightDP[k-i]);
        }
        return res;
    }
}
