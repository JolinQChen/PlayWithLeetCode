package _All;

public class _419_Battleships_in_a_Board {
    private int[] dc = {1,0};
    private int[] dr = {0,1};
    private int R;
    private int C;

    // 法一：union find
    private int[] parent;
    private int[] rank;
    private int find(int x){
        if(parent[x]!=x) parent[x] = find(parent[x]);
        return parent[x];
    }
    private boolean union(int x, int y){
        int xRoot = find(x);
        int yRoot = find(y);
        if(xRoot==yRoot) return false;
        int xRootRank = rank[xRoot];
        int yRootRank = rank[yRoot];
        if(xRootRank < yRootRank){
            parent[xRoot] = yRoot;
        }
        else if(xRootRank>yRootRank) parent[yRoot] = xRoot;
        else{
            parent[yRoot] = xRoot;
            rank[yRoot]++;
        }
        return true;
    }

    public int countBattleships(char[][] board) {
        R = board.length;
        if(R==0) return 0;
        C = board[0].length;
        parent = new int[R*C];
        for(int i=0; i<R*C; i++) parent[i]=i;
        int count = 0;
        rank = new int[R*C];
        for(int r=0; r<R; r++){
            for(int c=0; c<C; c++){
                if(board[r][c]=='X'){
                    if(parent[r*C+c]==r*C+c) count++;
                    for(int i=0; i<2; i++){
                        if(r+dr[i]<R && c+dc[i]<C){
                            if(board[r+dr[i]][c+dc[i]]=='X'){
                                union(r*C+c, (r+dr[i])*C+c+dc[i]);
                                break;
                            }
                        }
                    }
                }

            }
        }
        return count;
    }

    // 法二：one-pass, O(1) extra space，这题太狗了朋友
    /**这道题有一个限制条件：战舰不会相邻，所以我们通过判断X点的左边的点和上面的点是不是X就可以统计所有战舰最左上方的那个位置，从而完成进阶的要求。*/
    public int countBattleships_2(char[][] board) {
        int count = 0;
        int R = board.length;
        if(R == 0) return 0;
        int C = board[0].length;
        for(int r=0; r<R; r++){
            for(int c=0; c<C; c++){
                if(board[r][c]=='X' && isHead(board, R, C, r, c)) count++;
            }
        }
        return count;
    }
    private boolean isHead(char[][] board, int R, int C, int r, int c){
        if(r-1>=0) {
            if(board[r-1][c]=='X') return false;
        }
        if(c-1>=0) {
            if(board[r][c-1]=='X') return false;
        }
        return true;
    }
}
