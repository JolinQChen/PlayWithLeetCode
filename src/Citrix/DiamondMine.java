package Citrix;
import java.util.*;
/**
 * 见leetcode 741摘樱桃
 * 方法一：贪心算法[Wrong Answer]
 * 我们先找到一条樱桃最多的路径走到右下角，然后摘下樱桃，再从摘完后的樱桃地找到最多的樱桃路径返回，
 * 摘下樱桃。
 *
 * 但是在下面的情况，就会找不到最佳的答案：
 *
 * 11100
 * 00101
 * 10100
 * 00100
 * 00111
 * 算法：
 *
 * 我们可以使用动态规划来计算从任何位置 (i, j) 到右下角最多能摘到的樱桃数 dp[i][j]。
 * 这和最小路径和非常相似，如果不熟悉这类问题，请参阅链接 #64。
 *
 * 然后，我们可以找到从左上角到右下角可以摘到最多樱桃的路径，通过我们计算的 dp 来判断我们移动的方向，
 * 基于比较 dp[i+1][j] 和 dp[i][j+1]。
 *
 * 我们将樱桃摘下以后，再从右下角到左上角做相同的策略。最终能摘到的樱桃数量就是答案。
 *
 * */

public class DiamondMine {
    /** 这是错误方法！！！ */
    public static int diamondPickup_wrong(int[][] grid) {
        int ans = 0;
        int[][] path = bestPath(grid);
        if (path == null) return 0;
        for (int[] step: path) {
            ans += grid[step[0]][step[1]];
            grid[step[0]][step[1]] = 0;
        }

        for (int[] step: bestPath(grid))
            ans += grid[step[0]][step[1]];
        return ans;
    }

    public static int[][] bestPath(int[][] grid) {
        int N = grid.length;
        int[][] dp = new int[N][N];
        for (int[] row: dp) Arrays.fill(row, Integer.MIN_VALUE);
        dp[N-1][N-1] = grid[N-1][N-1];
        for (int i = N-1; i >= 0; --i) {
            for (int j = N-1; j >= 0; --j) {
                if (grid[i][j] >= 0 && (i != N-1 || j != N-1)) {
                    dp[i][j] = Math.max(i+1 < N ? dp[i+1][j] : Integer.MIN_VALUE,
                            j+1 < N ? dp[i][j+1] : Integer.MIN_VALUE);
                    dp[i][j] += grid[i][j];
                }
            }
        }
        if (dp[0][0] < 0) return null;
        int[][] ans = new int[2*N - 1][2];
        int i = 0, j = 0, t = 0;
        while (i != N-1 || j != N-1) {
            if (j+1 == N || i+1 < N && dp[i+1][j] >= dp[i][j+1]) i++;
            else j++;

            ans[t][0] = i;
            ans[t][1] = j;
            t++;
        }
        return ans;
    }
    /** 以上是错误解法！！！！
     * 以下为正确解法
     * */
    /**
    与其从左上角走到右下角，再从右下角走到左上角；不如直接考虑从左上角选两条路走到右下角。
    在走了 t 步之后，我们可能处于的位置 (r, c) 满足 r+c=t，所以如果我们在位置 (r1, c1) 和
     (r2, c2) 有两个人，那么 r2=r1+c1-c2。这意味着 r1，c1，c2 唯一地决定了两个走了 t 步数的人。
     这个条件让我们能够很好地进行动态规划。
    算法：

    定义 dp[r1][c1][c2] 是两个人从 (r1, c1) 和 (r2, c2) 开始，朝着 (N-1, N-1) 所能摘到最多
     的樱桃数量，其中 r2=r1+c1-c2。

     如果 grid[r1][c1] 和 grid[r2][c2] 不是荆棘，那么 dp[r1][c1][c2] 的值是
     (grid[r1][c1] + grid[r2][c2])，加上
     dp[r1+1][c1][c2]，dp[r1][c1+1][c2]，dp[r1+1][c1][c2+1]，dp[r1][c1+1][c2+1]
     的最大值。在 (r1, c1) == (r2, c2) 的情况下，我们要避免重复计数。

     为什么要加上 dp[r+1][c1][c2]，dp[r1][c1+1][c2]，dp[r1+1][c1][c2+1]，dp[r1][c1+1][c2+1]的最大值？
     它对应 1 号和人 2 号向下或向右移动的 4 种可能性：
            1 号向下和 2 号向下：dp[r1+1][c1][c2]；
            1 号向右和 2 号向下：dp[r1][c1+1][c2]
            1 号向下和 2 号向右：dp[r1+1][c1][c2+1]
            1 号向右和 2 号向右：dp[r1][c1+1][c2+1]
     */

    int[][][] memo;
    int[][] grid;
    int N;
    public int cherryPickup_right_1(int[][] grid) {
        this.grid = grid;
        N = grid.length;
        memo = new int[N][N][N];
        for (int[][] layer: memo)
            for (int[] row: layer)
                Arrays.fill(row, Integer.MIN_VALUE);
        return Math.max(0, dp(0, 0, 0));
    }
    public int dp(int r1, int c1, int c2) {
        int r2 = r1 + c1 - c2;
        if (N == r1 || N == r2 || N == c1 || N == c2 ||
                grid[r1][c1] == -1 || grid[r2][c2] == -1) {
            return -999999;
        } else if (r1 == N-1 && c1 == N-1) {
            return grid[r1][c1];
        } else if (memo[r1][c1][c2] != Integer.MIN_VALUE) {
            return memo[r1][c1][c2];
        } else {
            int ans = grid[r1][c1];
            if (c1 != c2) ans += grid[r2][c2];
            ans += Math.max(Math.max(dp(r1, c1+1, c2+1), dp(r1+1, c1, c2+1)),
                    Math.max(dp(r1, c1+1, c2), dp(r1+1, c1, c2)));
            memo[r1][c1][c2] = ans;
            return ans;
        }
    }

    /**或者自下而上*/
    public int diamondPickup_right_2(int[][] grid) {
        int N = grid.length;
        int[][] dp = new int[N][N];
        for (int[] row: dp) Arrays.fill(row, Integer.MIN_VALUE);
        dp[0][0] = grid[0][0];

        for (int t = 1; t <= 2*N - 2; ++t) {
            int[][] dp2 = new int[N][N];
            for (int[] row: dp2) Arrays.fill(row, Integer.MIN_VALUE);

            for (int i = Math.max(0, t-(N-1)); i <= Math.min(N-1, t); ++i) {
                for (int j = Math.max(0, t-(N-1)); j <= Math.min(N-1, t); ++j) {
                    if (grid[i][t-i] == -1 || grid[j][t-j] == -1) continue;
                    int val = grid[i][t-i];
                    if (i != j) val += grid[j][t-j];
                    for (int pi = i-1; pi <= i; ++pi)
                        for (int pj = j-1; pj <= j; ++pj)
                            if (pi >= 0 && pj >= 0)
                                dp2[i][j] = Math.max(dp2[i][j], dp[pi][pj] + val);
                }
            }
            dp = dp2;
        }
        return Math.max(0, dp[N-1][N-1]);
    }

    public static void main(String[] args){

    }
}
