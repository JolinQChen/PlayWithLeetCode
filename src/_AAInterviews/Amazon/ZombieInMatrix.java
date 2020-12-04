package _AAInterviews.Amazon;

import java.util.LinkedList;
import java.util.Queue;

public class ZombieInMatrix {
    static int R;
    static int C;
    static int[] dr = {0,0,1,-1};
    static int[] dc = {1,-1,0,0};

    private static int infectTime(int[][] grid) {
        R = grid.length;
        if(R==0) return 0;
        C = grid[0].length;
        int res = 0;
        Queue<int[]> queue = new LinkedList<>();
        for(int i=0; i<R; i++) {
            for(int j=0; j<C; j++) {
                if(grid[i][j]==1) queue.add(new int[]{i,j});
            }
        }
        while (!queue.isEmpty()) {

            int size = queue.size();
//            boolean flag = false;
            while (size>0) {
                size--;
                int[] curr = queue.poll();
                for(int i=0; i<4; i++){
                    int r = curr[0]+dr[i];
                    int c = curr[1]+dc[i];
                    if(isValid(r, c) && grid[r][c] == 0) {
//                        flag = true;
                        grid[r][c] = 1;
                        queue.add(new int[]{r,c});
                    }
                }
            }
            res++;
        }
        return res-1;
    }
    private static boolean isValid(int r, int c) {
        if(r<R && r>=0 && c>=0 && c<C) return true;
        return false;
    }

    public static void main(String[] args) {
        int[][] grid = {{0, 1, 1, 0, 1},{0, 1, 0, 1, 0},{0, 0, 0, 0, 1},{0, 1, 0, 0, 0}};
        int res = infectTime(grid);
        System.out.println(res);
    }
}
