package _AAInterviews.Citrix.FullTime;
import java.util.*;
public class Anagrams {
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> res = new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();
        for(String str:strs) {
            char[] tmp = str.toCharArray();
            Arrays.sort(tmp);
            String curr = Arrays.toString(tmp);
            if(!map.containsKey(curr)) map.put(curr,new ArrayList<>());
            map.get(curr).add(str);
        }
        for(String key:map.keySet()) {
            res.add(map.get(key));
        }
        return res;
    }
}
