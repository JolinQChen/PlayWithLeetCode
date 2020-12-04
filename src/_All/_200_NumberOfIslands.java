package _All;
/**
 Input: grid = [
 ["1","1","1","1","0"],
 ["1","1","0","1","0"],
 ["1","1","0","0","0"],
 ["0","0","0","0","0"]
 ]
 Output: 1
 * */
public class _200_NumberOfIslands {
    static int[] dr = {0,0,1,-1};
    static int[] dc = {1,-1,0,0};
    static int R;
    static int C;
    static char[][] graph;

    private static void dfs(int r, int c) {
        graph[r][c]='2';
        for(int i=0; i<4; i++) {
            if(r+dr[i]>=0 && r+dr[i]<R && c+dc[i]>=0 && c+dc[i]<C && graph[r+dr[i]][c+dc[i]]=='1') {
                dfs(r+dr[i],c+dc[i]);
            }
        }
    }

    public static int numIslands(char[][] grid) {
        if(grid == null || grid.length==0 || grid[0].length==0) return 0;
        R = grid.length;
        C = grid[0].length;
        graph = grid;
        int res = 0;

        for(int i=0; i<R; i++) {
            for(int j=0; j<C; j++) {
                if(grid[i][j]=='1') {
                    dfs(i,j);
                    res++;
                }
            }
        }
        return res;
    }
}
