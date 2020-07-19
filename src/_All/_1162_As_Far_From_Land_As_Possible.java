package _All;

import java.util.*;

/**
 * Given an N x N grid containing only values 0 and 1, where 0 represents water and 1 represents land,
 * find a water cell such that its distance to the nearest land cell is maximized and return the distance.
 *
 * The distance used in this problem is the Manhattan distance: the distance between two cells (x0, y0) and
 * (x1, y1) is |x0 - x1| + |y0 - y1|.
 *
 * If no land or water exists in the grid, return -1.
 *
 * */

public class _1162_As_Far_From_Land_As_Possible {
    private int[] dc = {0,0,1,-1};
    private int[] dr = {1,-1,0,0};
    public int maxDistance(int[][] grid) {
        int res = -1;
        int N = grid.length;
        int[][] path = new int[N][N]; //记录每个点距离1的距离
        Queue<Integer> queue = new LinkedList<>(); //记录pos
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                if(grid[i][j]==1) queue.offer(i*N+j);
            }
        }
        if(queue.isEmpty() || queue.size()==N*N) return -1; //不存在water或land
        while (!queue.isEmpty()){
            int pos = queue.poll();
            int cur_r = pos / N;
            int cur_c = pos % N;
            for(int i=0; i<4; i++){
                for(int j=0; j<4; j++){
                    if(cur_c+dc[i]>=0 && cur_c+dc[i]<N && cur_r+dr[i]>=0 && cur_r+dr[i]<N){
                        if(path[cur_r+dr[i]][cur_c+dc[i]]>0 || grid[cur_r+dr[i]][cur_c+dc[i]]==1) continue;
                        res = path[cur_r+dr[i]][cur_c+dc[i]] = path[cur_r][cur_c]+1;
                        queue.offer((cur_r+dr[i])*N + cur_c+dc[i]);
                    }
                }
            }
        }
        return res;

    }
}
