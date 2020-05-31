package Citrix;
import java.util.*;
public class maze {
    private static int[][][] dist = new int[101][101][11];
    private static int[][] dp = new int[11][1024];
    private static List<int[]> coins = new LinkedList<>();
    private static int[][] dir = {{1,0},{-1,0},{0,1},{0,-1}};
    private static int limit;
    private static int len;
    private static int INF = 99999999;


    private static boolean isSafe(int x, int y, int r, int c){
        return(x>=0 && x<r && y>=0 && y<c);
    }

    private static void getDist(List<List<Integer>> maze, int idx){
        int n = maze.size(), m = maze.get(0).size();
        boolean[][] vis = new boolean[101][101];
        int x=coins.get(idx)[0], y = coins.get(idx)[1];
        Deque<int[]> q = new LinkedList<>();
        q.add(new int[]{x,y});
        for(int i=0; i<101; i++) {
            for(int j=0; j<101; j++) dist[i][j][idx] = INF;

        }
        vis[x][y] = true;
        dist[x][y][idx]=0;
        while(!q.isEmpty()){
            int[] p = q.poll();
            for(int i=0; i<4; i++){
                int r=p[0]+dir[i][0], c = p[1]+dir[i][1];
                if(isSafe(r, c, n, m) && maze.get(r).get(c)!=1 && !vis[r][c]){
                    vis[r][c] = true;
                    dist[r][c][idx] = dist[p[0]][p[1]][idx]+1;
                    q.add(new int[]{r,r});
                }
            }
        }
    }
    private static int solve(int idx, int mask, int ax, int ay){
        if(mask == limit) return dist[ax][ay][idx];
        if(dp[idx][mask]!=-1) return dp[idx][mask];
        int res = Integer.MAX_VALUE;
        for(int i=0; i<len; i++){
            if((mask & (1<<i))==0){
                int newmask=mask|(1<<i);
                res = Math.min(res,solve(i,newmask,ax,ay)+dist[coins.get(i)[0]][coins.get(i)[1]][idx]);
            }
        }
        return dp[idx][mask]=res;
    }

    public static int minMoves(List<List<Integer>> maze, int x, int y) {
        // Write your code here
        int N = maze.size();
        int M = maze.get(0).size();
        for(int i=0; i<N;i++){
            for(int j=0; j<M; j++) {
                if(maze.get(i).get(j)==2) coins.add(new int[]{i,j});
            }
        }
        int bx = 0,by=0;
        for(int[] ii:dp) Arrays.fill(ii, -1);

        coins.add(0,new int[]{0,0});
        len = coins.size();
        limit = (1<<len)-1;
        for(int i=0; i<len;i++) getDist(maze, i);
        int ans = solve(0,1,x,y);
        if(ans>=INF) return -1;
        else return ans;


    }

    public static void main(String[] args){
        List<List<Integer>> maze = new LinkedList<>();
        List<Integer> tmp_1 = new LinkedList<>();
        tmp_1.add(0);
        tmp_1.add(2);
        tmp_1.add(0);
        maze.add(tmp_1);
        List<Integer> tmp_2 = new LinkedList<>();
        tmp_2.add(0);
        tmp_2.add(0);
        tmp_2.add(1);
        maze.add(tmp_2);
        List<Integer> tmp_3 = new LinkedList<>();
        tmp_3.add(1);
        tmp_3.add(1);
        tmp_3.add(1);
        maze.add(tmp_3);
        System.out.println(minMoves(maze,1,1));

    }
}
