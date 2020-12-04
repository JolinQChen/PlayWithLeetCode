package _AAInterviews.Amazon;

public class MaxOfMinAltitudes {
    private static int findMaxPathScore(int[][] graph) {
        int R = graph.length;
        int C = graph[0].length;
        int[][] dp = new int[R][C];
        graph[R-1][C-1] = Integer.MAX_VALUE;
        dp[0][0] = Integer.MAX_VALUE;
        for(int r=0; r<R; r++) {
            for(int c=0; c<C; c++) {
                int last = Integer.MIN_VALUE;
                if(r-1>=0) last = Math.max(last,dp[r-1][c]);
                if(c-1>=0) last = Math.max(last,dp[r][c-1]);
                if(r!=0 || c!=0) dp[r][c] = Math.min(last,graph[r][c]);
            }
        }
        return dp[R-1][C-1];
    }
    public static void main(String[] args) {
        int[][] graph = {{1, 2, 3,5},{4,6,5,2},{3,4,5,7},{7,2,4,1}};
        int res = findMaxPathScore(graph);
        System.out.println(res);
    }
}
