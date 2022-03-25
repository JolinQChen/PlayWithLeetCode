package unionFind;

import java.util.*;

/**
 * Given a 2D board containing 'X' and 'O' (the letter O), capture all regions surrounded by 'X'.
 *
 * A region is captured by flipping all 'O's into 'X's in that surrounded region.
 *
 * X X X X
 * X O O X
 * X X O X
 * X O X X
 * After running your function, the board should be:
 *
 * X X X X
 * X X X X
 * X X X X
 * X O X X
 * */
public class _130_Surrounded_Regions {
    // BFS做法
    class Solution_1 {
        public void solve(char[][] board) {
        //BFS
        int[] dr = {0,0,1,-1};
        int[] dc = {1,-1,0,0};
        
        int R = board.length;
        int C = board[0].length;
        boolean[][] visited = new boolean[R][C];

        for(int r=0; r<R; r++) {
            for(int c=0; c<C; c++) {
                if(board[r][c] == 'O' && !visited[r][c]) {
                    Queue<int[]> queue = new LinkedList<>();
                    List<int[]> toBeTurned = new ArrayList<>();
                    queue.add(new int[]{r,c});
                    visited[r][c] = true;
                    boolean isIsland = true;
                    while(!queue.isEmpty()) {
                        int[] pos = queue.poll();
                        toBeTurned.add(pos);
                        for(int i=0; i<4; i++) {
                            if(pos[0]+dr[i]>=0 && 
                                pos[0]+dr[i]<R && 
                                pos[1]+dc[i]>=0 && 
                                pos[1]+dc[i]<C) {
                                if(board[pos[0]+dr[i]][pos[1]+dc[i]] == 'O' &&
                                !visited[pos[0]+dr[i]][pos[1]+dc[i]]) {
                                    queue.add(new int[]{pos[0]+dr[i], pos[1]+dc[i]});
                                    visited[pos[0]+dr[i]][pos[1]+dc[i]] = true;
                                }

                            } else {
                                // reached boarder
                                isIsland = false;
                            }
                        }
                    }
                    if(isIsland) {
                        for(int[] pos:toBeTurned) {
                            board[pos[0]][pos[1]] = 'X';
                        }
                    }
                }
            }
        }

    }
    }

    // Union Find标准做法
    class Solution_UF {
        private int[] parent;
        private int[] rank; //rank[x]表示x所在的树的最大深度

        public Solution_UF(int n) {
            this.parent = new int[n];
            this.rank = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }
        private int find(int i){
            if(parent[i]!=i) parent[i] = find(parent[i]);
            return parent[i];
        }

        private void union(int x, int y){
            int xRoot = find(x);
            int yRoot = find(y);
            int xRank = rank[x];
            int yRank = rank[y];
            if(xRank<yRank){
                parent[xRoot] = yRoot;
            }
            else if(xRank>yRank){
                parent[yRoot] = xRoot;
            }
            else{
                parent[yRoot] = xRoot;
                rank[yRoot]++;
            }
        }
        public void solve(char[][] board){
            if(board==null || board.length==0 || board[0].length==0) return;
            int rows = board.length;
            int cols = board[0].length;
            //初始化
            // 用一个虚拟节点, 边界上的O 的父节点都是这个虚拟节点
            Solution_UF uf = new Solution_UF(rows * cols + 1);
            int dummyNode = rows * cols;
            for(int i=0; i<rows; i++){
                for(int j=0; j<cols; j++){
                    // 遇到O进行并查集操作合并
                    if(board[i][j]=='O'){
                        if (i == 0 || i == rows - 1 || j == 0 || j == cols - 1) {
                            // 边界上的O,把它和dummyNode 合并成一个连通区域.
                            uf.union(i*cols + j, dummyNode);
                        }
                        else{
                            // 和上下左右合并成一个连通区域.
                            if (i > 0 && board[i - 1][j] == 'O')
                                uf.union(i*cols + j, (i-1)*cols + j);
                            if (i < rows - 1 && board[i + 1][j] == 'O')
                                uf.union(i*cols + j, (i+1)*cols + j);
                            if (j > 0 && board[i][j - 1] == 'O')
                                uf.union(i*cols + j, i*cols + j-1);
                            if (j < cols - 1 && board[i][j + 1] == 'O')
                                uf.union(i*cols + j, i*cols + j+1);
                        }
                    }
                }
            }

            // 遍历board中的内容，如果不与dummy连接的则为X，其他为O
            for(int i=0; i<rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if(find(i*cols+j) == find(dummyNode)) board[i][j] = 'O';
                    else board[i][j] = 'X';
                }
            }
        }
    }
}
