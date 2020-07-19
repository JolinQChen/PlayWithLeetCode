package _All;

import java.util.*;
/**
 * 给定 N，想象一个凸 N 边多边形，其顶点按顺时针顺序依次标记为 A[0], A[i], ..., A[N-1]。
 *
 * 假设您将多边形剖分为 N-2 个三角形。对于每个三角形，该三角形的值是顶点标记的乘积，三角剖分的分数是进行三角剖分后所有 N-2 个三角形的值之和。
 *
 * 返回多边形进行三角剖分后可以得到的最低分。
 *
 * 输入：[1,2,3]
 * 输出：6
 * 解释：多边形已经三角化，唯一三角形的分数为 6。
 *
 * 输入：[3,7,4,5]
 * 输出：144
 * 解释：有两种三角剖分，可能得分分别为：3*7*5 + 4*5*7 = 245，或 3*4*5 + 3*4*7 = 144。最低分数为 144。
 *
 *
 * */

public class _1039_Minimum_Score_Triangulation_of_Polygon {
    public int minScoreTriangulation(int[] A) {
        int n = A.length;
        int res = Integer.MAX_VALUE;
        // dp[left][right]表示从left点到right点的最优值
        int[][] dp = new int[n][n];
        for(int right=2; right<n; right++){
            //左端点枚举
            for(int left=right-2; left>=0; left--){
                int cur_min = Integer.MAX_VALUE;
                for(int k=left+1;k<right;k++){
                    cur_min = Math.min(cur_min,dp[left][k] + dp[k][right] + A[k] * A[left] * A[right]);
                    dp[left][right] = cur_min;
                }

            }

        }
        return dp[0][n-1];
    }
}
