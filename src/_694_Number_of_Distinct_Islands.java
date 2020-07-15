import java.util.*;
public class _694_Number_of_Distinct_Islands {
    /**
     * Given a non-empty 2D array grid of 0's and 1's, an island is a group of 1's (representing land)
     * connected 4-directionally (horizontal or vertical.) You may assume all four edges of the grid
     * are surrounded by water.
     *
     * Count the number of distinct islands. An island is considered to be the same as another if and
     * only if one island can be translated (and not rotated or reflected) to equal the other.
     *
     * Example 1:
     * 11000
     * 11000
     * 00011
     * 00011
     *
     * */
    private int[][] grid;
    private boolean[][] visited;
    private int R;
    private int C;

    public void explore(Set shape, int r, int c, int r0, int c0)
    {
        if(r>=0 && r<R && c>=0 && c<C && grid[r][c]==1 && !visited[r][c])
        {
            visited[r][c] = true;
            shape.add((r-r0)*C*2 + c-c0);
            explore(shape, r+1,c,r0,c0);
            explore(shape, r-1,c,r0,c0);
            explore(shape, r,c+1,r0,c0);
            explore(shape, r,c-1,r0,c0);
        }
    }
    public int numDistinctIslands(int[][] grid)
    {
        this.grid = grid;
        R = grid.length;
        C = grid[0].length;
        this.visited = new boolean[R][C];
        HashSet<HashSet<Integer>> shapes = new HashSet<>();
        for(int r=0; r<R; r++)
        {
            for(int c=0; c<C; c++)
            {
                HashSet<Integer> shape = new HashSet<>();
                explore(shape,r,c,r,c);
                if(!shape.isEmpty()) shapes.add(shape);

            }
        }
        return shapes.size();
    }
}
