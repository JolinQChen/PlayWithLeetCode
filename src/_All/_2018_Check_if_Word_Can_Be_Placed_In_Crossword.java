package _All;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

public class _2018_Check_if_Word_Can_Be_Placed_In_Crossword {
    public boolean placeWordInCrossword(char[][] board, String word) {
        return placeWordInCrosswordHelper(board, word)
                || placeWordInCrosswordHelper(board, reverse(word));
    }

    private String reverse(String str) {
        StringBuilder sb = new StringBuilder();
        for(int i=str.length()-1; i>=0; i--) sb.append(str.charAt(i));
        return sb.toString();
    }
    private boolean placeWordInCrosswordHelper(char[][] board, String word) {
        int R = board.length;
        int C = board[0].length;
        char[] word_list = word.toCharArray();
        int len = word_list.length;
        if(R<len && C<len) return false;
        if(C>=len) {
            // horizontally
            for(int r=0; r<R; r++) {
                int idx = 0;
                while (idx<C - len && board[r][idx]=='#') idx++;
                if(board[r][idx]=='#') continue;
                // try to place at this line
                for (int i=0; i<len; i++) {
                    if(board[r][idx+i]!=' ' && board[r][idx+i]!=word_list[i]) continue;
                }
                idx = idx+len;
                boolean flag = true;
                while (idx<C) {
                    if(board[r][idx]!='#') {
                        flag = false;
                        break;
                    }
                    idx++;
                }
                if(flag) return true;
            }
        }

        if(R>len) {
            // horizontally
            for(int c=0; c<C; c++) {
                int idx = 0;
                while (idx<R - len && board[idx][c]=='#') idx++;
                if(board[idx][c]=='#') continue;
                // try to place at this line
                for (int i=0; i<len; i++) {
                    if(board[idx+i][c]!=' ' && board[idx+i][c]!=word_list[i]) continue;
                }
                idx = idx+len;
                boolean flag = true;
                while (idx<R) {
                    if(board[idx][c]!='#') {
                        flag = false;
                        break;
                    }
                    idx++;
                }
                if(flag) return true;
            }
        }
        return false;
    }
}
