package _AAInterviews.Google;
import java.util.*;
public class _1055_Shortest_Way_to_Form_String {
    public int shortestWay(String source, String target) {
        int right = 0;
        int count = 0;

        int len1 = source.length();
        Map<Character, TreeSet<Integer>> map = new HashMap<>();

        for(int i=0; i<len1;i++) {
            char c = source.charAt(i);
            if(!map.containsKey(c)) map.put(c, new TreeSet<>());
            map.get(c).add(i);
        }
        int len2 =target.length();
        int pre = -1;
        while(right<len2) {
            char c = target.charAt(right);
            if(!map.containsKey(c)) return -1;
            Integer pos = map.get(c).higher(pre);
            if(pos==null) {
                // System.out.println(pre+", "+right);
                count++;
                pre = -1;
                continue;
            }
            pre = pos;
            right++;
        }
        if(pre!=-1) count++;
        return count;
    }
}
