package DivideConquer;

import java.util.HashMap;
import java.util.Map;

/**
 * 分治算法，左边放奇数，右边放偶数
 * 左边包括（N+1）/2个奇数，右边包括N/2个偶数
 * 左侧对于1～(N+1)/2的数做2n-1的变换，右边做2n的变换映射
 * */


public class _932_Beautiful_Array {
    Map<Integer, int[]> memo;
    public int[] beautifulArray(int N) {
        // 阿里巴巴的原题，这是什么鬼变态题
        memo = new HashMap<>();
        return divideConquer(N);
    }
    private int[] divideConquer(int N) {
        if(memo.containsKey(N)) return memo.get(N);
        int[] ans = new int[N];
        if(N==1) return new int[]{1};
        else {
            int t = 0;
            for(int x:divideConquer((N+1)/2)) ans[t++]=2*x-1;
            for(int x:divideConquer(N/2)) ans[t++] = 2*x;
        }
        memo.put(N, ans);
        return ans;
    }
}
