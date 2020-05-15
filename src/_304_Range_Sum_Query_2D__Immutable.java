import java.util.*;
/**
 * 给定一个二维矩阵，计算其子矩形范围内元素的总和，该子矩阵的左上角为 (row1, col1) ，右下角为 (row2, col2)。
 * Note:
 * You may assume that the matrix does not change.
 * There are many calls to sumRegion function.
 * You may assume that row1 ≤ row2 and col1 ≤ col2.
 * */

public class _304_Range_Sum_Query_2D__Immutable {

    class NumMatrix {
        private int[][] prefex;
        public NumMatrix(int[][] matrix) {
            if(matrix==null || matrix.length==0 || matrix[0].length==0) {
                prefex = new int[1][1];
            }
            else {
                int raw = matrix.length;
                int col = matrix[0].length;
                prefex = new int[raw+1][col+1];
                //int sum = 0;
                for(int r=0; r<raw; r++){
                    for(int c=0; c<col; c++){
                        //sum += matrix[r][c];
                        prefex[r+1][c+1] = prefex[r][c+1]+prefex[r+1][c]-prefex[r][c]+matrix[r][c];
                    }
                }
            }

        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            if(prefex.length==1 && prefex[0].length==1) return 0;
            int res = prefex[row2+1][col2+1] + prefex[row1][col1] - prefex[row1][col2+1] - prefex[row2+1][col1];
            return res;
        }
    }
}
