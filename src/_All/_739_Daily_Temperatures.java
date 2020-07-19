package _All;

import java.util.*;
/**
 * Given a list of daily temperatures T, return a list such that, for each day
 * in the input, tells you how many days you would have to wait until a warmer
 * temperature. If there is no future day for which this is possible, put 0 instead.
 *
 * For example, given the list of temperatures T = [73, 74, 75, 71, 69, 72, 76, 73],
 * your output should be [1, 1, 4, 2, 1, 1, 0, 0].
 *
 * Note: The length of temperatures will be in the range [1, 30000]. Each temperature
 * will be an integer in the range [30, 100].
 *
 * */
public class _739_Daily_Temperatures {
        /**
         * 题目要求我们找出下一次温度比当天高距离的天数。因为温度只能在 [30，100] 之内，
         * 如果现在的温度是 T[i]=50，我们只需要找到下一个出现的 51，52，…，100，然后取最
         * 快出现的那个位置。
         *
         * 我们按逆序遍历列表，对于每个 T[i]，我们要知道 (T[i],100] 温度所出现的位置，
         * 为此我们用一个 next 数组记录该数据，若当前位置出现 T[i]=100，则我们将该索引
         * 记录在 next[100]。
         *

         * warmer_index 记录比当前温度高的索引位置，它等于
                * next[T[i]+1], next[T[i]+2], ..., next[100] 的最小值。
         *
         * */

// 这波倒序遍历秀了我一脸，宛如智障
    public int[] dailyTemperatures(int[] T) {
        int[] ans = new int[T.length];
        int[] next = new int[101];
        Arrays.fill(next, Integer.MAX_VALUE);
        for (int i = T.length - 1; i >= 0; --i) {
            int warmer_index = Integer.MAX_VALUE;
            for (int t = T[i] + 1; t <= 100; ++t) {
                if (next[t] < warmer_index)
                    warmer_index = next[t];
            }
            if (warmer_index < Integer.MAX_VALUE)
                ans[i] = warmer_index - i;
            next[T[i]] = i;
        }
        return ans;
    }
}
