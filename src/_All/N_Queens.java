package _All;

import java.util.*;
public class N_Queens {
    private List<List<String>> res;
    private boolean[] diag_visit1;
    private boolean[] diag_visit2;
    private boolean[] col_visit;
    private char[][] board;
    private int n_all;

    public List<List<String>> solveNQueens(int n) {
        res = new ArrayList<>();
        diag_visit1 = new boolean[2*n-1];
        diag_visit2 = new boolean[2*n-1];
        col_visit = new boolean[n];
        board = new char[n][n];
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++) board[i][j]='.';
        }
        this.n_all = n;
        queenBackTrack(0);
        return res;
    }

    private boolean isAvailable(int r, int c) {
        if(diag_visit1[r+c] || diag_visit2[c-r+n_all-1] || col_visit[c]) return false;
        return true;
    }

    private void update(int c, int r, boolean put) {
        diag_visit1[r+c] = put;
        diag_visit2[c-r+n_all-1] = put;
        col_visit[c] = put;
        board[r][c] = put?'Q':'.';
    }
    private void queenBackTrack(int r){
        if(r == n_all) {
            // reach one possible _All.solution, add to result
            List<String> tmp = new ArrayList<>();
            for(char[] arr:board){
                tmp.add(String.valueOf(arr));
            }
            res.add(tmp);
            return;
        }
        for(int c=0; c<n_all;c++){
            if(!isAvailable(r,c)) continue;
            update(c,r,true);
            queenBackTrack(r+1);
            update(c,r,false);
        }
    }
}
