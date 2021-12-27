package DFS_BFS_BackTracking;
import java.util.*;

public class _200_Number_of_Islands {
    public static int[] dr = {0,0,1,-1};
    public static int[] dc = {1,-1,0,0};
    static int R;
    static int C;
    static char[][] graph;

    private static void dfs(int r, int c){
        graph[r][c] = '2';
        for(int i=0; i<4; i++) {
            if(r+dr[i]>=0 && r+dr[i]<R && c+dc[i]>=0 && c+dc[i]<C && graph[r+dr[i]][c+dc[i]] == '1') {
                dfs(r+dr[i], c+dc[i]);
            }
        }
    }
    public int numIslands(char[][] grid) {
        R = grid.length;
        C = grid[0].length;
        graph = grid;
        int count = 0;
        for(int r=0; r<R; r++) {
            for(int c=0; c<C; c++) {
                if(graph[r][c]=='1') {
                    count++;
                    dfs(r,c);
                }
            }
        }
        return count;
    }
}
