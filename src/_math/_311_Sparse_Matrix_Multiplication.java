package _math;

public class _311_Sparse_Matrix_Multiplication {
    public int[][] multiply(int[][] A, int[][] B) {
        int R = A.length;
        int C = B[0].length;
        int len = A[0].length;
        int[][] res = new int[R][C];
        for(int i=0; i<R; i++){
            for(int j=0; j<C; j++){
                int sum = 0;
                for(int k=0; k<len; k++){
                    sum += A[i][k] * B[k][j];
                }
                res[i][j]=sum;
            }
        }
        return res;
    }
}
