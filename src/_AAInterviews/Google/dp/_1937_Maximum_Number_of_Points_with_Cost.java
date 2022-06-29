package _AAInterviews.Google.dp;

public class _1937_Maximum_Number_of_Points_with_Cost {
    public long maxPoints(int[][] points) {
        int m = points.length, n = points[0].length;
        long[] dp = new long[n];
        for (int c = 0; c < n; c++) {
            dp[c] = points[0][c];
        }
        for (int r = 1; r < m; r++) {
            long[] leftMax = new long[n], rightMax = new long[n];
            leftMax[0] = dp[0];
            for (int c = 1; c < n; c++) {
                leftMax[c] = Math.max(dp[c], leftMax[c - 1] - 1);
            }
            rightMax[n - 1] = dp[n - 1];
            for (int c = n - 2; c >= 0; c--) {
                rightMax[c] = Math.max(dp[c], rightMax[c + 1] - 1);
            }
            for (int c = 0; c < n; c++) {
                dp[c] = points[r][c] + Math.max(leftMax[c], rightMax[c]);
            }
        }
        long max = 0l;
        for (int c = 0; c < n; c++) {
            max = Math.max(max, dp[c]);
        }
        return max;
    }
}
