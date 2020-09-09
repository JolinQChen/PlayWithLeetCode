package _AAInterviews.Wish;

import java.util.LinkedList;
import java.util.Queue;

/**
 * You are given a m x n 2D grid initialized with these three possible values.
 *
 * -1 - A wall or an obstacle.
 * 0 - A gate.
 * INF - Infinity means an empty room. We use the value 231 - 1 = 2147483647 to represent INF as you may assume that
 * the distance to a gate is less than 2147483647.
 * Fill each empty room with the distance to its nearest gate. If it is impossible to reach a gate, it
 * should be filled with INF.
 * */
public class WallsAndGates {
    private final int INF = 2147483647;
    private int[] dr = {0,0,-1,1};
    private int[] dc = {1,-1,0,0};

    public void wallsAndGates(int[][] rooms) {
        Queue<int[]> queue = new LinkedList<>();
        int R = rooms.length;
        int C = rooms[0].length;
        for(int i=0; i<R; i++) {
            for(int j=0; j<C; j++) {
                if(rooms[i][j]==0) queue.add(new int[]{i,j});
            }
        }
        int round = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size>0) {
                size--;
                int[] pos = queue.poll();
                for(int i=0; i<4; i++) {
                    if(isValid(pos[0]+dr[i], pos[1]+dc[i], R,C) && rooms[pos[0]+dr[i]][pos[1]+dc[i]]==INF) {
                        rooms[pos[0]+dr[i]][pos[1]+dc[i]] = round;
                        queue.add(new int[]{pos[0]+dr[i], pos[1]+dc[i]});
                    }
                }
            }
            round++;
        }
    }

    private boolean isValid(int r, int c, int R, int C) {
        if(r>=0 && r<R && c>=0 && c<C) return true;
        return false;
    }

}
