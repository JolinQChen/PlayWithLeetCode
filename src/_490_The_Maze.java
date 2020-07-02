import java.util.*;
public class _490_The_Maze {
    // Java DFS
    public int R, C;
    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        R = maze.length;
        C = maze[0].length;

        // corner cases
        if(destination[0]>0 && maze[destination[0]-1][destination[1]]==0 && destination[0]<R-1 && maze[destination[0]+1][destination[1]]==0) {
            if((destination[1]==0 || (destination[1]>0 && maze[destination[0]][destination[1]-1]==1)) && (destination[1]==C-1 || (destination[1]<C-1 && maze[destination[0]][destination[1]+1]==1)))
                return false;
        }
        if(destination[1]>0 && maze[destination[0]][destination[1]-1]==0 && destination[1]<C-1 && maze[destination[0]][destination[1]+1]==0) {
            if((destination[0]==0 || (destination[0]>0 && maze[destination[0]-1][destination[1]]==1)) && (destination[0]==R-1 || (destination[0]<R-1 && maze[destination[0]+1][destination[1]]==1)))
                return false;
        }

        boolean[][] visited = new boolean[R][C];
        return dfs(maze, start, destination, visited);


    }

    private boolean dfs(int[][] maze, int[] curStart, int[] destination, boolean[][] visited){
        if(curStart[0] == destination[0] && curStart[1] == destination[1]) return true;
        if(visited[curStart[0]][curStart[1]]) return false;
        visited[curStart[0]][curStart[1]] = true;
        int i,j;
        // up
        i = curStart[0];
        j = curStart[1];
        while(i > 0 && maze[i-1][j] == 0) i--;

        if(dfs(maze, new int[]{i,j}, destination, visited)) return true;

        // down

        i = curStart[0];
        j = curStart[1];
        while(i < R-1 && maze[i+1][j] == 0) i++;

        if(dfs(maze, new int[]{i,j}, destination, visited)) return true;

        // left

        i = curStart[0];
        j = curStart[1];
        while(j > 0 && maze[i][j-1] == 0) j--;

        if(dfs(maze, new int[]{i,j}, destination, visited)) return true;

        // right

        i = curStart[0];
        j = curStart[1];
        while(j < C-1 && maze[i][j+1] == 0) j++;

        if(dfs(maze, new int[]{i,j}, destination, visited)) return true;

        return false;
    }

}
