package _AAInterviews.Wish;
import java.util.*;
public class _781_RabbitsInForest {
    public int numRabbits(int[] answers) {
        Map<Integer, Integer> map = new HashMap<>();
        int res = 0;
        for(int cur:answers) {
            if(!map.containsKey(cur) || map.get(cur)==0) {
                map.put(cur,cur);
                res += cur+1;
            }
            else {
                map.put(cur, map.get(cur)-1);
            }
        }
        return res;
    }
}
