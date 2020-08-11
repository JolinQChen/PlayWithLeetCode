package _AAInterviews.MS;
import java.util.*;
public class WordSearchII {

    // 直接的backtracking
    /**
     * 标准答案是用了trie? 不想看了
     * */

    List<String> res;
    char[][] board;
    int[] dr = {0,0,-1,1};
    int[] dc = {1,-1,0,0};
    int R;
    int C;

    private boolean backTracking(String word, int idx, int r, int c, boolean[][] visited) {
        if(idx == word.length()-1) {
            res.add(word);
            return true;
        }
        visited[r][c] = true;
        for(int i=0; i<4; i++) {
            int tmpr = r+dr[i];
            int tmpc = c+dc[i];
            if(isValid(tmpr,tmpc) && board[tmpr][tmpc]==word.charAt(idx+1) && !visited[tmpr][tmpc]) {
                if(backTracking(word, idx+1, tmpr, tmpc, visited)) return true;
            }
        }
        visited[r][c] = false;
        return false;
    }

    private boolean isValid(int r, int c) {
        if(r>=0 && c>=0 && r<R && c<C) return true;
        return false;
    }

    public List<String> findWords(char[][] board, String[] words) {
        res = new LinkedList<>();
        this.board = board;
        this.R = this.board.length;
        this.C = this.board[0].length;

        for(String word:words) {
            char first = word.charAt(0);
            labelA:
            for(int i=0; i<R; i++) {
                for(int j=0; j<C; j++) {
                    if(board[i][j]==first) {
                        if(backTracking(word,0,i,j,new boolean[R][C])) break labelA;
                    }
                }
            }
        }
        return res;
    }
}
