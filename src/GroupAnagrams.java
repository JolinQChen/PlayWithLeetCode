import java.util.*;

public class GroupAnagrams {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for(String str:strs){
            char[] tmp = str.toCharArray();
            Arrays.sort(tmp);
            String mark = Arrays.toString(tmp);
            if(!map.containsKey(mark)) map.put(mark,new LinkedList<>());
            map.get(mark).add(str);
        }
        List<List<String>> res = new LinkedList<>();
        for(String key:map.keySet()){
            res.add(map.get(key));
        }
        return res;
    }
}
