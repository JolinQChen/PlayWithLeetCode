package _AAInterviews.Citrix;
import java.util.*;
public class LotteryCoupons {
    public static long waysToChooseSum(long n){
        Map<Long, Long> map = new HashMap<>();
        long curMax = 0;
        long count = 0;
        for(long i=1; i<=n; i++){
            long sum = digitSum(i);
            if(map.containsKey(sum)) map.put(sum, map.get(sum)+1);
            else map.put(sum,(long)1);
        }
        for(long key:map.keySet()){
            if(map.get(key)>curMax) {
                count = 1;
                curMax = map.get(key);
                //System.out.println("curMax: "+curMax);
            }
            else if(map.get(key)==curMax) {
                count++;
                //System.out.println("curMax: "+curMax + "count "+count);
            }
        }

        return count;
    }

    private static long digitSum(long m){
        long res = 0;
        while (m > 0){
            res += m%10;
            m /= 10;
        }
        return res;
    }

    public static void main(String[] args){
        System.out.println(waysToChooseSum(12));
    }
}
