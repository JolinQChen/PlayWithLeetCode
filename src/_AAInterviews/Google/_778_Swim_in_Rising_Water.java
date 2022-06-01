package _AAInterviews.Google;

import java.util.*;
public class _778_Swim_in_Rising_Water {

    int[] dr = {0,0,1,-1};
    int[] dc = {1,-1,0,0};

    public int swimInWater(int[][] grid) {
        int n = grid.length;
        if(n==1) return 0;
        boolean[][] visited = new boolean[n][n];
        visited[0][0] = true;
        int[][] dp = new int[n][n];
        dp[0][0] = grid[0][0];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        int target = pos(n-1,n-1,n);
        while (!queue.isEmpty()) {
            int[] curPos = revertPos(queue.poll(), n);
            for(int i=0; i<4; i++) {
                if(curPos[0]+dr[i]>=0 && curPos[0]+dr[i]<n && curPos[1]+dc[i]>=0 && curPos[1]+dc[i]<n) {
                    // valid
                    if(!visited[curPos[0]+dr[i]][curPos[1]+dc[i]]) {
                        visited[curPos[0]+dr[i]][curPos[1]+dc[i]] = true;
                        dp[curPos[0]+dr[i]][curPos[1]+dc[i]] = Math.max(grid[curPos[0]+dr[i]][curPos[1]+dc[i]], dp[curPos[0]][curPos[1]]);
                        queue.add(pos(curPos[0]+dr[i], curPos[1]+dc[i], n));
                    } else {
                        int curPathValue = Math.max(grid[curPos[0]+dr[i]][curPos[1]+dc[i]], Math.min(dp[curPos[0]+dr[i]][curPos[1]+dc[i]], dp[curPos[0]][curPos[1]]));
                        if(curPathValue<dp[curPos[0]+dr[i]][curPos[1]+dc[i]]) {
                            dp[curPos[0]+dr[i]][curPos[1]+dc[i]] = curPathValue;
                            queue.add(pos(curPos[0]+dr[i], curPos[1]+dc[i], n));
                        }
                    }
                }
            }
        }
        return dp[n-1][n-1];
    }

    private int pos(int r, int c, int n) {
        return r*n+c;
    }

    private int[] revertPos(int x, int n) {
        int r = x/n;
        int c = x%n;
        return new int[]{r,c};
    }
}
