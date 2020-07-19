package _All;

import java.util.*;
public class _1329_Sort_the_Matrix_Diagonally {
    public int[][] diagonalSort(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        for(int r = m-1; r>=0; r--){
            int[] tmp = new int[Math.min(m-r,n)];
            for(int i=0; i<tmp.length; i++){
                tmp[i] = mat[r+i][i];
            }
            Arrays.sort(tmp);
            for(int i=0; i<tmp.length; i++){
                mat[r+i][i] = tmp[i];
            }
        }

        for(int c=1; c<n; c++) {
            int[] tmp = new int[Math.min(m,n-c)];
            for(int i=0; i<tmp.length; i++){
                tmp[i] = mat[i][c+i];
            }
            Arrays.sort(tmp);
            for(int i=0; i<tmp.length; i++){
                mat[i][c+i] = tmp[i];
            }
        }
        return mat;
    }
}
