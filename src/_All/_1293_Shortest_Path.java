package _All;

import java.util.*;
public class _1293_Shortest_Path {
    int[] rd = {-1,1,0,0};
    int[] cd = {0,0,1,-1};
    public int shortestPath(int[][] grid, int k) {

        int m = grid.length;
        int n = grid[0].length;
        boolean[][][] vis = new boolean[m][n][k+1];

        Queue<int[]> queue = new LinkedList<>();

        vis[0][0][k] = true;
        if(grid[0][0] == 1){
            k--;
            if(k<0)return -1;
        }
        queue.add(new int[]{0,0,k});

        int ans = 0;
        while(!queue.isEmpty()){

            int size = queue.size();

            while(size-- > 0){
                int[] poll = queue.poll();


                if(poll[0] == m-1 && poll[1] == n-1) return ans;

                exploreNeighbours(grid,queue,vis,poll);
            }
            ans++;
        }
        return -1;

    }
    private void exploreNeighbours(int[][] grid,Queue<int[]> queue, boolean[][][] vis, int[] ijk )     {

        for(int i = 0; i<4; i++){
            int row = ijk[0]+rd[i];
            int col = ijk[1] + cd[i];
            int k = ijk[2];

            if(row <0 || col <0 || row >=vis.length || col >=vis[0].length)continue;


            if(grid[row][col] == 1){
                if(k == 0)continue;
                k--;
            }
            if(!vis[row][col][k]){
                queue.add(new int[]{row,col,k});
                vis[row][col][k] = true;
            }

        }

    }
}
