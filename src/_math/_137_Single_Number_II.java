package _math;

import java.util.HashMap;
import java.util.Map;

public class _137_Single_Number_II {
    public int singleNumber(int[] nums) {
        Map<Integer, Integer> map = new HashMap();
        int res = 0;
        for(int i:nums) {
            if(!map.containsKey(i) || map.get(i)==1) {
                map.put(i, map.getOrDefault(i,0)+1);
                res ^= i;
            }
        }
        return res;
    }
}
