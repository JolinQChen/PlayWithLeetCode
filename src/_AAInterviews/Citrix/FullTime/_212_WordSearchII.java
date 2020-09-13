package _AAInterviews.Citrix.FullTime;
import java.util.*;
public class _212_WordSearchII {
    List<String> res;
    char[][] board;
    int[] dr = {0,0,-1,1};
    int[] dc = {1,-1,0,0};
    int R;
    int C;

    private boolean backTracking(String word, int idx, int r, int c, boolean[][] visited) {
        // idx, r, c indicate already found character and its position, continue from here
        if(idx == word.length()-1) {
            res.add(word);
            return true;
        }
        visited[r][c] = true;
        for(int i=0; i<4; i++) {
            int tmpr = r+dr[i];
            int tmpc = c+dc[i];
            if(isValid(tmpr, tmpc) && !visited[tmpr][tmpc] && board[tmpr][tmpc]==word.charAt(idx+1)) {
                if(backTracking(word,idx+1,tmpr,tmpc,visited)) return true;
            }
        }
        visited[r][c] = false;
        return false;
    }



    private boolean isValid(int r, int c) {
        return r>=0 && r<R && c>=0 && c<C;
    }


    public List<String> findWords(char[][] board, String[] words) {
        this.board = board;
        R = board.length;
        if(R==0) return new ArrayList<>();
        C = board[0].length;

        res = new ArrayList<>();

        for(String word:words) {
            char first = word.charAt(0);
            boolean find = false;
            for(int i=0; i<R; i++){
                if(find) break;
                for(int j=0; j<C; j++) {
                    if(find) break;
                    if(board[i][j]==first) {
                        if(backTracking(word,0,i,j,new boolean[R][C])) find = true;
                    }
                }
            }

        }
        return res;
    }

    /** Trie & Backtracking*/
    private Trie root = new Trie();
    private int[] rowDirs = {0, 0, -1, 1};
    private int[] colDirs = {-1, 1, 0, 0};

    public List<String> findWords2(char[][] board, String[] words) {

        final boolean[][] visited = new boolean[board.length][board[0].length];
        final List<String> result = new ArrayList<>();

        for(String word : words) {
            insert(word);
        }

        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[0].length; j++) {
                visited[i][j] = true;
                dfs(board, i, j, visited, result, new StringBuilder(board[i][j] + ""));
                visited[i][j] = false;
            }
        }

        return result;
    }

    private void dfs(char[][] board, int r, int c, boolean[][] visited,
                     List<String> result, StringBuilder sb) {

        String word = sb.toString();
        if(isWord(word) && ! result.contains(word)) {
            result.add(word);
        }

        for(int i = 0; i < 4; i++) {
            int row = r + rowDirs[i];
            int col = c + colDirs[i];

            if(isValid(visited, row, col)) {

                sb.append(board[row][col]);
                visited[row][col] = true;

                if(isPrefix(sb.toString())) {
                    dfs(board, row, col, visited, result, sb);
                }

                visited[row][col] = false;
                sb.deleteCharAt(sb.length() - 1);
            }
        }
    }

    private boolean isValid(boolean[][] visited, int r, int c) {
        return r >= 0 && r < visited.length && c >= 0 && c < visited[0].length && !visited[r][c];
    }

    private void insert(String word) {
        Trie current = root;
        for(int i = 0; i < word.length(); i++) {
            if(current.chars[word.charAt(i) - 'a'] == null) {
                current.chars[word.charAt(i) - 'a'] = new Trie();
            }

            current = current.chars[word.charAt(i) - 'a'];
        }

        current.word = true;
    }

    private boolean isPrefix(String prefix) {
        Trie current = root;
        for(int i = 0; i < prefix.length(); i++) {
            current = current.chars[prefix.charAt(i) - 'a'];
            if(current == null) {
                return false;
            }
        }
        return true;
    }

    private boolean isWord(String word) {
        Trie current = root;
        for(int i = 0; i < word.length(); i++) {
            current = current.chars[word.charAt(i) - 'a'];
            if(current == null) {
                return false;
            }
        }
        return current.word;
    }

    class Trie {
        boolean word;
        Trie[] chars;

        Trie() {
            this.word = false;
            this.chars = new Trie[26];
        }
    }
}
