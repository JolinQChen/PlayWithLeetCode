package _All;

import java.util.*;
public class _529_Minesweeper {
    private int[] dr = {0,0,1,-1,1,1,-1,-1};
    private int[] dc = {1,-1,0,0,1,-1,1,-1};

    public char[][] updateBoard(char[][] board, int[] click) {
        char tmp = board[click[0]][click[1]];

        if(tmp=='M') {
            board[click[0]][click[1]] = 'X';
            //return board;
        }
        else if(tmp=='E'){
            int flag = 0;
            for(int i=0; i<8; i++){
                if(isValidPoint(click[0]+dr[i], click[1]+dc[i], board.length, board[0].length)){
                    if(board[click[0]+dr[i]][click[1]+dc[i]]=='M') flag++;
                }
            }
            if(flag>0){
                board[click[0]][click[1]] = (char)('0'+flag);
                return board;
            }
            else{
                // blank, recursive
                board[click[0]][click[1]] = 'B';
                for(int i=0; i<8; i++){
                    if(isValidPoint(click[0]+dr[i], click[1]+dc[i], board.length, board[0].length)){
                        board = updateBoard(board, new int[]{click[0]+dr[i], click[1]+dc[i]});
                    }
                }
                return board;
            }
        }
        return board;

    }
    private boolean isValidPoint(int r, int c, int R, int C){
        if(r>=0 && c>=0 && r<R && c<C) return true;
        return false;
    }
}
