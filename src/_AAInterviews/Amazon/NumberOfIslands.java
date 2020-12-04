package _AAInterviews.Amazon;

import java.util.*;
/**
 * Given a 2d grid map of '1's (land) and '0's (water), count the number of islands.
 * An island is surrounded by water and is formed by connecting adjacent lands
 * horizontally or vertically. You may assume all four edges of the grid are all
 * surrounded by water.
 * */
public class NumberOfIslands {
    static char[][] overAllGrid;
    static int R;
    static int C;
    static int[] dr = {0,0,1,-1};
    static int[] dc = {1,-1,0,0};

    private static void dfsChange(int r, int c) {
        if(overAllGrid[r][c]=='1') {
            overAllGrid[r][c] = '0';
            for(int i=0; i<4; i++) {
                if(isValid(r+dr[i], c+dc[i])) dfsChange(r+dr[i], c+dc[i]);
            }
        }
    }

    private static boolean isValid(int r, int c) {
        if(r>=0 && c>=0 && r<R && c<C) return true;
        return false;
    }

    private static int numIslands(char[][] grid) {
        int res = 0;
        overAllGrid = grid;
        R = grid.length;
        if(R==0) return 0;
        C = grid[0].length;
        for(int i=0; i<R; i++) {
            for(int j=0; j<C; j++) {
                if(overAllGrid[i][j]=='1') {
                    res++;
                    dfsChange(i,j);
                }
            }
        }
        return res;
    }

}
