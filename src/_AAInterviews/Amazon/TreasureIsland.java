package _AAInterviews.Amazon;

import java.util.*;
/**
 * Input:
 * [['O', 'O', 'O', 'O'],
 *  ['D', 'O', 'D', 'O'],
 *  ['O', 'O', 'O', 'O'],
 *  ['X', 'D', 'D', 'O']]
 *
 * Output: 5
 * Explanation: Route is (0, 0), (0, 1), (1, 1), (2, 1), (2, 0), (3, 0)
 * The minimum route takes 5 steps.
 * */
public class TreasureIsland {
    private static int[] dr = {0,0,1,-1};
    private static int[] dc = {1,-1,0,0};
    // use bfs
    private static int countSteps(char[][] graph) {
        int R = graph.length;
        int C = graph[0].length;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0,0});
        graph[0][0] = 'V';
        int count = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            System.out.println("count: " +count);
            while (size>0) {
                size--;
                int[] pos = queue.poll();
                System.out.println(pos[0]+" , "+pos[1]);
                for(int i=0; i<4; i++) {
                    int r = pos[0]+dr[i];
                    int c = pos[1]+dc[i];
                    if(r>=0 && r<R && c>=0 && c<C && graph[r][c] != 'D' && graph[r][c] != 'V') {
                        if(graph[r][c] == 'X') return count+1;
                        queue.add(new int[]{r,c});
                        graph[r][c] = 'V';
                    }
                }
            }
            count++;
        }
        return 0;
    }

    public static void main(String[] args) {
        char[][] graph = {{'O', 'O', 'O', 'O'},{'D', 'O', 'D', 'O'},{'O', 'O', 'O', 'O'},{'X', 'D', 'D', 'O'}};
        int steps = countSteps(graph);
        System.out.println(steps);
    }
}
