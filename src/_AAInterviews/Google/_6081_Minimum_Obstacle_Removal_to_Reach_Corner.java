package _AAInterviews.Google;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class _6081_Minimum_Obstacle_Removal_to_Reach_Corner {
    int[][] moves = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public int minimumObstacles(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        int step = 0;
        visited[0][0] = true;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0});
        dfs(0, 0, queue, visited, grid);
        if (visited[m - 1][n - 1]) return step;
        while (!queue.isEmpty()) {
            step++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] loc = queue.poll();
                for (int[] move : moves) {
                    int newR = loc[0] + move[0];
                    int newC = loc[1] + move[1];
                    if (newR < 0 || newR >= m || newC < 0 || newC >= n || visited[newR][newC] || grid[newR][newC] == 0) {
                        continue;
                    } else {
                        grid[newR][newC] = 0;
                        visited[newR][newC] = true;
                        queue.offer(new int[]{newR, newC});
                        dfs(newR, newC, queue, visited, grid);
                    }
                }

                if (visited[m - 1][n - 1]) return step;
            }

        }
        return step;
    }

    private void dfs(int r, int c, Queue<int[]> queue, boolean[][] visited, int[][] grid) {
        int m = grid.length, n = grid[0].length;
        for (int[] move : moves) {
            int newR = r + move[0];
            int newC = c + move[1];
            if (newR < 0 || newR >= m || newC < 0 || newC >= n || visited[newR][newC] || grid[newR][newC] == 1) {
                continue;
            } else {
                visited[newR][newC] = true;
                queue.offer(new int[]{newR, newC});
                dfs(newR, newC, queue, visited, grid);
            }
        }
    }

}
