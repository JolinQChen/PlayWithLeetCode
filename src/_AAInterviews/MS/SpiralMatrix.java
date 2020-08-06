package _AAInterviews.MS;

import java.util.*;

public class SpiralMatrix {
    public List<Integer> spiralOrder(int[][] matrix) {
        int R = matrix.length;
        List<Integer> res = new ArrayList<>();

        // rule out corner cases
        if(R==0) return res;
        int C = matrix[0].length;
        if(R==1) {
            for(int i=0; i<C; i++) res.add(matrix[0][i]);
            return res;
        }
        if(C==1) {
            for (int i=0; i<R; i++) res.add(matrix[i][0]);
            return res;
        }



        boolean[][] visit = new boolean[R][C];
        int r=0,c=0;
        visit[0][0] = true;
        res.add(matrix[0][0]);
        while(r<R && c<C) {
            boolean flag = false;
            while(c+1<C && !visit[r][c+1]) {
                flag = true;
                c++;
                res.add(matrix[r][c]);
                visit[r][c] = true;
            }
            while(r+1<R && !visit[r+1][c]) {
                flag = true;
                r++;
                res.add((matrix[r][c]));
                visit[r][c] = true;
            }
            while(c-1>=0 && !visit[r][c-1]) {
                flag = true;
                c--;
                res.add(matrix[r][c]);
                visit[r][c] = true;
            }
            while(r-1>=0 && !visit[r-1][c]) {
                flag = true;
                r--;
                res.add(matrix[r][c]);
                visit[r][c] = true;
            }
            if(!flag) break;
        }
        return res;
    }
}
