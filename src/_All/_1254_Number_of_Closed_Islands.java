package _All;

public class _1254_Number_of_Closed_Islands {

    private int[] dr = {0,0,1,-1};
    private int[] dc = {1,-1,0,0};

    private int[] parent;
    private int find(int x) {
        if(parent[x]!=x) parent[x] = find(parent[x]);
        return parent[x];
    }

    private boolean union(int x, int y) {
        int root_x = find(x);
        int root_y = find(y);
        if(root_x==root_y) return false;
        parent[root_x] = root_y;
        return true;
    }

    public int closedIsland(int[][] grid) {

        int m = grid.length;
        int n = grid[0].length;

        parent = new int[m*n+1];
        for(int i=0; i<=m*n; i++) parent[i]=i;

        for(int i=0; i<m; i++) {
            for(int j=0; j<n; j++) {
                if(grid[i][j]==0) {
                    int pos = i*n+j;
                    if(i==0 || j==0 || i==m-1 || j==n-1) {
                        union(pos, m * n);
                        continue;
                    }
                    for(int k=0; k<4; k++) {
                        if(grid[i+dr[k]][j+dc[j]] == 0) union(pos, (i+dr[k])*n+j+dc[k]);
                    }
                }
            }
        }

        int count = 0;
        for(int i=0; i<m*n; i++) {
            if(parent[i]==i) count++;
        }
        return count;
    }
}
