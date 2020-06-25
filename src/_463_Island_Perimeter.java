public class _463_Island_Perimeter {
    public int islandPerimeter(int[][] grid) {
        int R = grid.length;
        if(R==0) return 0;
        int C = grid[0].length;
        if(C==0) return 0;
        int res = 0;
        for(int i=0; i<R; i++){
            for(int j=0; j<C; j++){
                if(grid[i][j]==1){
                    //上
                    if(i==0 || grid[i-1][j]==0) res++;
                    //下
                    if(i==R-1 || grid[i+1][j]==0) res++;
                    //left
                    if(j==0 || grid[i][j-1]==0) res++;
                    //右
                    if(j==C-1 || grid[i][j+1]==0) res++;
                }
            }
        }
        return res;
    }
}
