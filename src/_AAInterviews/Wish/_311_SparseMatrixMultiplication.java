package _AAInterviews.Wish;

public class _311_SparseMatrixMultiplication {
    public int[][] multiply(int[][] A, int[][] B) {
        int R = A.length;
        int C = B[0].length;
        int len = A[0].length;
        int[][] res = new int[R][C];
        for(int r=0; r<R; r++) {
            for(int c=0; c<C; c++) {
                int tmp = 0;
                for(int i=0; i<len; i++) tmp += A[r][i]*B[i][c];
                res[r][c] = tmp;
            }
        }
        return res;
    }
}
