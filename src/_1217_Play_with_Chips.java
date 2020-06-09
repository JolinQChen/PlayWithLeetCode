import java.util.*;
/**
 * 数轴上放置了一些筹码，每个筹码的位置存在数组 chips 当中。
 *
 * 你可以对 任何筹码 执行下面两种操作之一（不限操作次数，0 次也可以）：
 * 将第 i 个筹码向左或者右移动 2 个单位，代价为 0。
 * 将第 i 个筹码向左或者右移动 1 个单位，代价为 1。
 * 最开始的时候，同一位置上也可能放着两个或者更多的筹码。
 *
 * 返回将所有筹码移动到同一位置（任意位置）上所需要的最小代价。
 *
 *
 * 输入：chips = [1,2,3]
 * 输出：1
 * 解释：第二个筹码移动到位置三的代价是 1，第一个筹码移动到位置三的代价是 0，总代价为 1。
 *
 * 输入：chips = [2,2,2,3,3]
 * 输出：2
 * 解释：第四和第五个筹码移动到位置二的代价都是 1，所以最小总代价为 2。
 * */

public class _1217_Play_with_Chips {
    public int minCostToMoveChips(int[] chips) {
        //找双数和单数位置上的chips数量
        int countDouble = 0;
        int countOdd = 0;
        for(int c:chips){
            if(c%2==0) countDouble++;
            else countOdd++;
        }
        return Math.min(countDouble, countOdd);
    }
}
