package _All;

import java.util.LinkedList;
import java.util.Queue;

public class _934_Shortest_Bridge
{
    private int[][] data;
    private Queue<Integer> island2;
    private int R;
    private int C;
    private boolean[][] visited;
    public int shortestBridge(int[][] A)
    {
        this.data = A;
        R = A.length;
        this.C = A[0].length;
        this.visited = new boolean[R][C];
        island2 = new LinkedList<>();
        // DFS find both islands
        labelA:
        for(int r=0; r<R; r++)
        {
            for(int c=0; c<C; c++)
            {
                if(data[r][c]==1 && !visited[r][c])
                {
                    //System.out.println("1");
                    findIsland(r,c,2);
                    break labelA;
                }
            }
        }

        // ==== test ====
        for(int[] t:data)
        {
            for(int i:t) System.out.print(i + " , ");
            System.out.println();
        }
        // ==== test ====

        // BFS expand
        int count = 0;
        while (!island2.isEmpty())
        {
            int remain = island2.size();
            while(remain>0)
            {
                remain--;
                int pos = island2.poll();
                int r = pos/C;
                int c = pos%C;

                // =====
                if(r-1>=0)
                {
                    if(data[r-1][c] == 1) return count;
                    else if(data[r-1][c] == 0)
                    {
                        data[r-1][c] = 2;
                        island2.add((r-1)*C+c);
                    }
                }
                if(r+1<R)
                {
                    if(data[r+1][c]==1) return count;
                    else if(data[r+1][c]==0)
                    {
                        data[r+1][c] = 2;
                        island2.add((r+1)*C+c);
                    }
                }
                if(c+1<C)
                {
                    if(data[r][c+1]==1) return count;
                    else if(data[r][c+1]==0)
                    {
                        data[r][c+1] = 2;
                        island2.add(r*C+c+1);
                    }
                }
                if(c-1>=0)
                {
                    if(data[r][c-1]==1) return count;
                    else if(data[r][c-1]==0)
                    {
                        data[r][c-1] = 2;
                        island2.add(r*C+c-1);
                    }
                }
                // =====

            }
            count++;

        }
        return 0;
    }

    private void findIsland(int r, int c, int count)
    {
        if(r>=0 && r<R && c>=0 && c<C && data[r][c]==1 && !visited[r][c])
        {
            island2.add(r*C+c);
            visited[r][c] = true;
            data[r][c] = count;
            findIsland(r+1, c, count);
            findIsland(r-1, c, count);
            findIsland(r,c+1,count);
            findIsland(r,c-1, count);
        }
    }


    public static void main(String[] args)
    {
        _934_Shortest_Bridge test = new _934_Shortest_Bridge();
        int[][] AA = {{0,1,0},{0,0,0},{0,0,1}};
        int res = test.shortestBridge(AA);
        System.out.println("res = " + res);
    }
}
