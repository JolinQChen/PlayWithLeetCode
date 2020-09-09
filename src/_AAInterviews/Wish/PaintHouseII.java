package _AAInterviews.Wish;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class PaintHouseII {
    public int minCostII(int[][] costs) {
        if(costs==null || costs.length==0) return 0;
        int N = costs.length;
        int K = costs[0].length;

        // dp[i][j] : minimum cost if paint i house with j color
        int[][] dp = new int[N][K];

        for(int i=0; i<K; i++) dp[0][i] = costs[0][i];
        for(int i=1; i<N; i++){
            int[][] tmp = new int[K][2];

            for (int j=0; j<K; j++) {
                tmp[j][0] = dp[i-1][j];
                tmp[j][1] = j;
            }
            Arrays.sort(tmp, new Comparator<int[]>() {
                @Override
                public int compare(int[] o1, int[] o2) {
                    return o1[0]-o2[0];
                }
            });

            for(int j=0; j<K; j++) {
                // update dp[i][j]
                if(j == tmp[0][1]) dp[i][j] = tmp[1][0] + costs[i][j];// conflict, 取次小值
                else dp[i][j] = tmp[0][0] + costs[i][j];
            }
        }
        int res = Integer.MAX_VALUE;
        for(int i=0; i<K; i++) {
            res = Math.min(res, dp[N-1][i]);
        }
        return res;
    }
}
