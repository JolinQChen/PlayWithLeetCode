import sun.awt.image.ImageWatched;

import java.util.*;

/**
 * 给定一个包含 m × n 个格子的面板，每一个格子都可以看成是一个细胞。每个细胞都具有一个初始状态：1 即为活细胞（live），
 * 或 0 即为死细胞（dead）。每个细胞与其八个相邻位置（水平，垂直，对角线）的细胞都遵循以下四条生存定律：
 *
 * 如果活细胞周围八个位置的活细胞数少于两个，则该位置活细胞死亡；
 * 如果活细胞周围八个位置有两个或三个活细胞，则该位置活细胞仍然存活；
 * 如果活细胞周围八个位置有超过三个活细胞，则该位置活细胞死亡；
 * 如果死细胞周围正好有三个活细胞，则该位置死细胞复活；
 *
 * 根据当前状态，写一个函数来计算面板上所有细胞的下一个（一次更新后的）状态。下一个状态是通过将上述规则同时应用于当前
 * 状态下的每个细胞所形成的，其中细胞的出生和死亡是同时发生的。
 *
 * 输入：
 * [
 *   [0,1,0],
 *   [0,0,1],
 *   [1,1,1],
 *   [0,0,0]
 * ]
 * 输出：
 * [
 *   [0,0,0],
 *   [1,0,1],
 *   [0,1,1],
 *   [0,1,0]
 * ]
 *
 * 要求原地solve，空间复杂度O(1)
 * */

/** 有点像那个橘子问题 */

public class _289_Game_of_Life {
    /** 笨办法，把所有需要逆转的加入list里面*/
    private int[] delta = {0,1,-1};
    public void gameOfLife(int[][] board) {
        if(board==null || board.length==0) return;
        int m = board.length;
        int n = board[0].length;
        List<Integer> reverse = new LinkedList<>(); //store reversed position
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                int count_live = 0;
                for(int ii=0; ii<3; ii++){
                    if(i+delta[ii]>=0 && i+delta[ii]<m){
                        for(int jj=0; jj<3; jj++){
                            if(ii==0 && jj==0) continue;
                            if(j+delta[jj]>=0 && j+delta[jj]<n){
                                //找到合适的邻点
                                if(board[i+delta[ii]][j+delta[jj]]==1) count_live++;
                            }
                        }
                    }

                }
                if(count_live>3 || count_live<2){
                    if(board[i][j] == 1) reverse.add(i*n+j); //活的变死的
                }
                if(count_live==3 && board[i][j]==0) reverse.add(i*n+j); //死的变活的
            }
        }
        for(int pos:reverse){
            int r = pos / n;
            int c = pos % n;
            board[r][c] = board[r][c]==0?1:0;
        }
    }

    /** 除了使用list储存，还可以将其状态直接改成 2 表示活变死，-1表示死变活*/
}
