package _AAInterviews.MS;
import java.util.*;
public class SetMatrixZeroes {
    public void setZeroes(int[][] matrix) {
        Set<Integer> row = new HashSet<>();
        Set<Integer> col = new HashSet<>();
        int R = matrix.length;
        if(R <= 0) return;
        int C = matrix[0].length;
        if(C <= 0) return;


        for(int r=0; r<R; r++) {
            for(int c=0; c<C; c++) {
                if(matrix[r][c] == 0) {
                    row.add(r);
                    col.add(c);
                }
            }
        }

        for(int r:row) {
            for(int c=0;c<C;c++){
                matrix[r][c] = 0;
            }
        }

        for(int c:col) {
            for(int r=0; r<R; r++) {
                matrix[r][c] = 0;
            }
        }
    }
}
