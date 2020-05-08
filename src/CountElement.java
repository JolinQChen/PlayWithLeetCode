import java.util.*;
public class CountElement {
    public int countElements(int[] arr) {
        int res = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for(int num:arr){
            map.put(num, map.getOrDefault(num, 0)+1);
        }
        for(int num:map.keySet()){
            if(map.containsKey(num+1)){
                res+=map.get(num);
            }
        }
        return res;
    }
}
