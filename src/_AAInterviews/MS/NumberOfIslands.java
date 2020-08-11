package _AAInterviews.MS;
import java.util.*;
public class NumberOfIslands {
    char[][] grid;
    int R;
    int C;
    int[] dr = {0,0,1,-1};
    int[] dc = {1,-1,0,0};
    //boolean[][] visited;
    private void dfsChange(int r, int c) {
        grid[r][c] = '0';
        for(int i=0;i<4; i++) {
            if(isValid(r+dr[i],c+dc[i]) && grid[r+dr[i]][c+dc[i]] == '1') dfsChange(r+dr[i],c+dc[i]);
        }
    }

    private boolean isValid(int r, int c) {
        if(r>=0 && c>=0 && r<R && c<C) return true;
        return false;
    }

    public int numIslands(char[][] grid) {
        this.grid = grid;
        this.R = grid.length;
        if(R==0) return 0;
        this.C = grid[0].length;
        int count = 0;
        for(int i=0; i<R; i++) {
            for(int j=0; j<C; j++) {
                if(this.grid[i][j]=='1') {
                    count++;
                    dfsChange(i,j);
                }
            }
        }
        return count;
    }

}
