package _AAInterviews.Verkada;
import java.util.*;
public class _1044_Longest_Duplicate_Substring {
    public String longestDupSubstring(String S) {
        int len = S.length();
        if(len<=1) return "";
        int maxLen = 0;
        String res = "";
        Map<Character, List<Integer>> map = new HashMap<>();
        char[] cc = S.toCharArray();
        for(int i=0; i<len; i++){
            if(!map.containsKey(cc[i])) map.put(cc[i], new ArrayList<>());
            map.get(cc[i]).add(i);
        }

        // BFS

        for(Character c:map.keySet()) {
            if(map.get(c).size()<=1) continue;
            Set<String> set = new HashSet<>();
            for(Integer idx:map.get(c)) {
                for(int j=idx+maxLen+1; j<len; j++) {
                    if(set.contains(S.substring(idx, j))){
                        maxLen = j-idx;
                        res = S.substring(idx, j);
                    } else set.add(S.substring(idx,j));
                }
            }
        }
        return res;
    }
}
