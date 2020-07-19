package _All;
import java.util.*;
public class _249_Group_Shifted_Strings {
    public List<List<String>> groupStrings(String[] strings) {
        Map<String, List<String>> map = new HashMap<>();
        for(String str:strings) {
            boolean flag = false;
            for(String first:map.keySet()) {
                if(isSequence(first, str)) {
                    map.get(first).add(str);
                    flag = true;
                }
            }
            if(!flag) {
                map.put(str, new ArrayList<>());
                map.get(str).add(str);
            }
        }
        List<List<String>> res = new ArrayList<>();
        for(String s:map.keySet()){
            res.add(map.get(s));
        }
        return res;
    }
    private boolean isSequence(String target, String s) {
        int len = target.length();
        if(s.length() != len) return false;
        int gap = dist(target.charAt(0), s.charAt(0));
        for(int i=1; i<len; i++) {
            if(dist(target.charAt(i), s.charAt(i)) != gap) return false;
        }
        return true;
    }

    private int dist(char c1, char c2) {
        if(c2>=c1) return (int)(c2-c1);
        else {
            int i1 = (int)('z' - c1);
            int i2 = (int)(c2-'a') + 1;
            return i1+i2;
        }
    }
}
