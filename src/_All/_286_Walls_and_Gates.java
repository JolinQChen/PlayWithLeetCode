package _All;

import java.util.*;

/**
 * input
 * INF  -1  0  INF
 * INF INF INF  -1
 * INF  -1 INF  -1
 *   0  -1 INF INF
 *
 * after
 *   3  -1   0   1
 *   2   2   1  -1
 *   1  -1   2  -1
 *   0  -1   3   4
 * */

public class _286_Walls_and_Gates {
    private final int INF = 2147483647;
    private int[] dr = {0,0,1,-1};
    private int[] dc = {1,-1,0,0};
    public void wallsAndGates(int[][] rooms) {
        Queue<int[]> queue = new LinkedList<>();
        int R = rooms.length;
        if(R==0) return;
        int C = rooms[0].length;
        for(int i=0; i<R; i++){
            for(int j=0; j<C; j++) {
                if(rooms[i][j]==0) queue.add(new int[]{i,j});
            }
        }
        while (!queue.isEmpty()){
            int[] cur = queue.poll();
            for(int i=0; i<4; i++){
                if(cur[0]+dr[i]>=0 && cur[0]+dr[i]<R && cur[1]+dc[i]>=0 && cur[1]+dc[i]<C && rooms[cur[0]+dr[i]][cur[1]+dc[i]]==INF){
                    rooms[cur[0]+dr[i]][cur[1]+dc[i]] = rooms[cur[0]][cur[1]]+1;
                    queue.add(new int[]{cur[0]+dr[i],cur[1]+dc[i]});
                }
            }
        }
    }
}
