package _AAInterviews.Amazon;

public class SpiralMatrix_II {
    public int[][] generateMatrix(int n) {
        int[][] res = new int[n][n];
        res[0][0] = 1;
        if(n == 1){
            return res;
        }
        int r=0, c=0;
        int cur = 1;
        boolean flag = true;
        while (flag) {
            flag = false;
            while(c+1<n && res[r][c+1]==0) {
                // head right
                flag = true;
                c++;
                res[r][c] = ++cur;
            }
            while(r+1<n && res[r+1][c]==0) {
                // head down
                flag = true;
                r++;
                res[r][c] = ++cur;
            }
            while(c-1>=0 && res[r][c-1]==0) {

                c--;
                flag = true;
                res[r][c] = ++cur;
            }
            while(r-1>=0 && res[r-1][c]==0) {
                r--;
                flag = true;
                res[r][c] = ++cur;
            }
        }
        return res;
    }
}
