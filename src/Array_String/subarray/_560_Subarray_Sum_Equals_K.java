package Array_String.subarray;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an array of integers and an integer k, you need to find the total
 * number of continuous subarrays whose sum equals to k.
 *
 * Input:nums = [1,1,1], k = 2
 * Output: 2
 *
 * Constraints:
 * The length of the array is in range [1, 20,000].
 * The range of numbers in the array is [-1000, 1000] and the range of the integer k is [-1e7, 1e7].
 * */
public class _560_Subarray_Sum_Equals_K {
    //暴力枚举，借鉴分段dp，把结果存在dp[][]里面
    // O(n^2), O(n^2)
    public int subarraySum_1(int[] nums, int k) {
        int len = nums.length;
        if(len == 0) return 0;
        int res=0;

        /** 这种办法memory space exceed*/
        int[][] dp = new int[len][len]; //dp[start][end]代表从i到j的和，包括i,j
        for(int end = 0; end<len; end++){
            for(int start = end; start>=0; start--){
                if(start==end) dp[start][end] = nums[end];
                else dp[start][end] = dp[start][end-1]+dp[end][end];
                if(dp[start][end]==k) res++;
            }
        }
        return res;
    }
    //暴力枚举
    // O(n^2), O(1)
    public int subarraySum_2(int[] nums, int k) {

        int count = 0;
        for (int start = 0; start < nums.length; ++start) {
            int sum = 0;
            for (int end = start; end >= 0; --end) {
                sum += nums[end];
                if (sum == k) {
                    count++;
                }
            }
        }
        return count;

    }

    //前缀和 + 哈希表优化
    /**
     * 我们可以基于枚举法利用数据结构进行进一步的优化，我们知道瓶颈在于对每个i，我们需要枚举所有的j来判断是否符合条件，
     * 这一步是否可以优化呢？答案是可以的。
     *
     * 我们定义 pre[i] 为 [0..i][0..i] 里所有数的和
     * 考虑以 ii 结尾的和为 kk 的连续子数组个数时只要统计有多少个前缀和为 pre[i]−k 的 pre[j] 即可。
     * 我们建立哈希表 mp，以和为键，出现次数为对应的值，记录 pre[i] 出现的次数，从左往右边更新 mp 边计算答案，
     * 那么以i结尾的答案 mp[pre[i]−k] 即可在 O(1)时间内得到。最后的答案即为所有下标结尾的和为 k的子数组个数之和。
     *
     * 需要注意的是，从左往右边更新边计算的时候已经保证了mp[pre[i]−k] 里记录的 pre[j] 的下标范围是 0≤j≤i 。
     * 同时，由于pre[i] 的计算只与前一项的答案有关，因此我们可以不用建立 pre 数组，直接用 pre 变量来记录 pre[i−1] 的答案即可。
     *
     *
     *
     * */
    public int subarraySum_3(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>(); //key为nums[i]的值，value为出现次数
        int cur_sum = 0;
        int res = 0;
        map.put(0,1);
        for(int num:nums){
            cur_sum += num;
            int remain = cur_sum - k;
            if(map.containsKey(remain)) res+=map.get(remain);
            map.put(cur_sum, map.getOrDefault(cur_sum,0)+1);
        }
        return res;
    }


}
