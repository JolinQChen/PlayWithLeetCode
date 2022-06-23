package _AAInterviews.Google;
import java.util.*;
public class _1711_Count_Good_Meals {
    public int countPairs(int[] deliciousness) {
        int mod = 1000000007;
        Map<Integer, Integer> map = new HashMap<>();
        int res = 0;
        for(int n:deliciousness) {
            int sum = 1;
            if(map.containsKey(sum-n)) res = (res % mod + map.get(sum-n) % mod) % mod;
            for(int i=0; i<=20; i++) {
                sum*=2;
                if(map.containsKey(sum-n)) res = (res % mod + map.get(sum-n) % mod) % mod;
            }
            map.put(n, map.getOrDefault(n,0)+1);
        }
        return res;
    }
}
