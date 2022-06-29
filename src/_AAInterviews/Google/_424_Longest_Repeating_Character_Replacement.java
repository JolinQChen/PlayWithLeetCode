package _AAInterviews.Google;
import java.util.*;
public class _424_Longest_Repeating_Character_Replacement {
    public int characterReplacement(String s, int k) {
        int max = 0;
        Map<Character, Integer> map = new HashMap<>();
        int len = s.length();
        int maxRepeat = 0;
        int left = 0;
        List<Integer> list = new ArrayList<>();
        for(int right=0; right<len; right++) {
            char c = s.charAt(right);
            map.put(c, map.getOrDefault(c,0)+1);
            maxRepeat = Math.max(maxRepeat, map.get(c));
            int changeTime = right-left+1-maxRepeat;
            if(changeTime>k) {
                map.put(s.charAt(left), map.get(s.charAt(left))-1);
                left++;
            }
            else max = Math.max(max, right-left+1);

        }


        return max;
    }
}
