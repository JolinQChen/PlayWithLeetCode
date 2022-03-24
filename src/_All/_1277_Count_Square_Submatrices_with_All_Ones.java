package _All;

import java.util.Arrays;

public class _1277_Count_Square_Submatrices_with_All_Ones {
    public int countSquares(int[][] matrix) {
        // use each 1 as right down point of square
        int res = 0;
        int[][] dp = new int[matrix.length][matrix[0].length];
        for(int r=0; r<matrix.length; r++) {
            for(int c=0; c<matrix[0].length; c++) {
                if(matrix[r][c] == 0) {
                    dp[r][c] = 0;
                } else {
                    if(r==0 || c==0) dp[r][c] = 1;
                    else {
                        dp[r][c] = Math.min(Math.min(dp[r-1][c-1], dp[r-1][c]), dp[r][c-1])+1;
                    }
                    res += dp[r][c];
                }
            }
        }
        return res;
    }
}
