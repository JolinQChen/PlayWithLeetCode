import java.util.*;
/**
 * Given a non-empty 2D array grid of 0's and 1's, an island is a group of 1's (representing land)
 * connected 4-directionally (horizontal or vertical.) You may assume all four edges of the grid are
 * surrounded by water.
 *
 * Find the maximum area of an island in the given 2D array. (If there is no island, the maximum area is 0.)
 *
 * [[0,0,1,0,0,0,0,1,0,0,0,0,0],
 *  [0,0,0,0,0,0,0,1,1,1,0,0,0],
 *  [0,1,1,0,1,0,0,0,0,0,0,0,0],
 *  [0,1,0,0,1,1,0,0,1,0,1,0,0],
 *  [0,1,0,0,1,1,0,0,1,1,1,0,0],
 *  [0,0,0,0,0,0,0,0,0,0,1,0,0],
 *  [0,0,0,0,0,0,0,1,1,1,0,0,0],
 *  [0,0,0,0,0,0,0,1,1,0,0,0,0]]
 *
 * Given the above grid, return 6. Note the answer is not 11,
 * because the island must be connected 4-directionally.
 * */
public class _695_Max_Area_of_Island
{
    //union find?
    private int[] parent;
    private int[] rank;
    private int[] dc = {0,0,1,-1};
    private int[] dr = {1,-1,0,0};
    private int[] area;

    private int find(int i){
        if(parent[i]!=i) {
            parent[i] = find(parent[i]);

        }
        return parent[i];
    }
    private boolean union(int x, int y){
        int xRoot = find(x);
        int yRoot = find(y);
        if(xRoot == yRoot) return false;
        int xRootRank = rank[xRoot];
        int yRootRank = rank[yRoot];

        if(xRootRank<yRootRank) {
            parent[xRoot] = yRoot;
            area[yRoot] = area[xRoot]+area[yRoot];
        }
        else if(xRootRank>yRootRank) {
            parent[yRoot] = xRoot;
            area[xRoot] = area[xRoot]+area[yRoot];
        }
        else{
            parent[xRoot] = yRoot;
            area[yRoot] = area[xRoot]+area[yRoot];
            rank[yRoot]++;
        }
        return true;
    }
    public int maxAreaOfIsland(int[][] grid) {
        int res = 0;
        int N = grid.length;
        int M = grid[0].length;
        parent = new int[N*M];
        rank = new int[N*M];
        area = new int[N*M];

        for(int i=0; i<N;i++) {
            for (int j = 0; j < M; j++) {
                if (grid[i][j] == 1) {
                    area[i*M+j]=1;
                }
            }
        }

        for(int i=0; i<M*N; i++) parent[i] = i;
        for(int i=0; i<N;i++){
            for(int j=0; j<M; j++){
                if(grid[i][j]==1){
                    for(int k=0; k<4; k++){
                        if(i+dr[k]>=0 && i+dr[k]<N && j+dc[k]>=0 && j+dc[k]<M && grid[i+dr[k]][j+dc[k]]==1 ){
                            int pos1 = i*M+j;
                            int pos2 = (i+dr[k])*M+j+dc[k];
                            union(pos1, pos2);
                        }
                        res = Math.max(res,area[find(i*M+j)]);
                    }
                }
            }
        }
        return res;
    }
}
