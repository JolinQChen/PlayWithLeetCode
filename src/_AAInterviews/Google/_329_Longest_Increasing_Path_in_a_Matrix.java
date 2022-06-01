package _AAInterviews.Google;

import org.junit.Test;

public class _329_Longest_Increasing_Path_in_a_Matrix {
    int[][] dp;
    int longest;
    int[][] matrix;
    int[] dr = {1,-1,0,0};
    int[] dc = {0,0,1,-1};
    public int longestIncreasingPath(int[][] matrix) {
        dp = new int[matrix.length][matrix[0].length];
        longest = 1;
        this.matrix = matrix;
        for(int i=0; i<matrix.length; i++) {
            for(int j=0; j<matrix[0].length; j++) {
                getCurPath(i,j);
            }
        }
        return longest;
    }

    private int getCurPath(int r, int c) {
        int curDP = 1;
        for(int i=0; i<4; i++) {
            if(r+dr[i]>=0 && r+dr[i]<matrix.length && c+dc[i]>=0 && c+dc[i]<matrix[0].length) {
                if(matrix[r+dr[i]][c+dc[i]]<matrix[r][c]){
                    if(dp[r+dr[i]][c+dc[i]]==0) {
                        dp[r+dr[i]][c+dc[i]] = getCurPath(r+dr[i], c+dc[i]);
                    }
                    curDP = Math.max(curDP, dp[r+dr[i]][c+dc[i]]+1);
                }
            }
        }
        dp[r][c] = curDP;
        longest = Math.max(longest, curDP);
        return curDP;
    }
    @Test
    public void test(){
        int[][] matrix = new int[3][3];
        matrix[0] = new int[]{9,9,4};
        matrix[1] = new int[]{6,6,8};
        matrix[2] = new int[]{2,1,1};
        System.out.println(longestIncreasingPath(matrix));
    }
}
