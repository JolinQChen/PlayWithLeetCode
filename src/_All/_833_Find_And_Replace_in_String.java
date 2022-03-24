package _All;
import java.util.*;
public class _833_Find_And_Replace_in_String {
    public String findReplaceString(String s, int[] indices, String[] sources, String[] targets) {
//        Map<Integer, String> map = new HashMap<>();
//        int len = s.length();
//        for(int i=0; i<len; i++) {
//            map.put(i, s.substring(i));
//        }
        Map<Integer, String> sourceMap = new HashMap<>();
        Map<Integer, String> targetMap = new HashMap<>();

        int len = indices.length;
        for(int i=0; i<len; i++) {
            sourceMap.put(indices[i], sources[i]);
            targetMap.put(indices[i], targets[i]);
        }
        Arrays.sort(indices);

        for(int i=len-1; i>=0; i--) {
            int idx = indices[i];
            String source = sourceMap.get(idx);
            String target = targetMap.get(idx);
            if(idx+source.length()<=s.length() && s.substring(idx, idx+source.length()).equals(source)) {
                s = s.substring(0,idx) + target + s.substring(idx+source.length());
            }
        }
        return s;
    }
}
