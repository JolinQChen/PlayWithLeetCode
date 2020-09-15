package _AAInterviews.Roblox;
import java.util.*;
public class MaxOccurringCharacter {
    public static char find(String str) {
        Map<Character, int[]> map = new HashMap<>(); // idx,count
        int max = 1;
        char res = str.charAt(0);
        int len = str.length();
        for(int i=0; i<len; i++) {
            char c = str.charAt(i);
            if(!map.containsKey(c)) map.put(c, new int[]{i,1});
            else map.get(c)[1]++;
        }
        for(char c:map.keySet()) {
            if(map.get(c)[1]>max) {
                max = map.get(c)[1];
                res = c;
            }
            else if(map.get(c)[1]==max && map.get(c)[0]<map.get(res)[0]) res = c;
        }
        return res;
    }

    public static void main(String[] args) {
        String test = "hhellh0Lloworld";
        System.out.println(find(test));
    }
}
