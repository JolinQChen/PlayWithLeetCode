package _All;

import java.util.*;
/**
 * A 2d grid map of m rows and n columns is initially filled with water.
 * We may perform an addLand operation which turns the water at position
 * (row, col) into a land. Given a list of positions to operate, count the
 * number of islands after each addLand operation. An island is surrounded
 * by water and is formed by connecting adjacent lands horizontally or vertically.
 * You may assume all four edges of the grid are all surrounded by water.
 *
 * Input: m = 3, n = 3, positions = [[0,0], [0,1], [1,2], [2,1]]
 * Output: [1,1,2,3]
 * */

public class _305_Number_of_Islands_II {
    private int[] parent;
    private int[] rank;

    private int find(int i){
        if(parent[i]!=i) parent[i] = find(parent[i]);
        return parent[i];
    }

    private boolean union(int x, int y){
        int xRoot = find(x);
        int yRoot = find(y);
        if(xRoot == yRoot) return false;
        int xRank = rank[x];
        int yRank = rank[y];
        if(xRank < yRank) parent[xRoot] = yRoot;
        else if(xRank > yRank) parent[yRoot] = xRoot;
        else {
            parent[xRoot] = yRoot;
            rank[xRoot]++;
        }
        return true;
    }

    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        List<Integer> res = new LinkedList<>();
        if(positions==null || positions.length==0) return res;
        int count = 0;
        // initialization
        parent = new int[m*n];
        rank = new int[m*n];
        for(int i=0; i<m*n; i++) parent[i]=i;
        int[][] data = new int[m][n];
        for(int[] cur : positions){
            // cur为现在这一步操作
            int r = cur[0];
            int c = cur[1];

            if(data[r][c] != 1) {
                count++;
                data[r][c] = 1;
            }

            if(r>0 && data[r-1][c]==1){
                // 融入上
                if(union(r*n+c,(r-1)*n+c)) count--;

            }
            if(r<m-1 && data[r+1][c]==1){
                // 融入下
                if(union(r*n+c,(r+1)*n+c)) count--;
            }
            if(c>0 && data[r][c-1]==1){
                // 融入左
                if(union(r*n+c,r*n+c-1)) count--;
            }
            if(c<n-1 && data[r][c+1]==1){
                // 融入右
                if(union(r*n+c,r*n+c+1)) count--;
            }
            res.add(count);
        }
        return res;
    }
}
