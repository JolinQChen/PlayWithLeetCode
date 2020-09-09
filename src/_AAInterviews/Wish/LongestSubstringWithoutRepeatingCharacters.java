package _AAInterviews.Wish;
import java.util.*;
public class LongestSubstringWithoutRepeatingCharacters {
    public int lengthOfLongestSubstring(String s) {
        //双指针
        int left = 0;
        int right = 0;
        int len = s.length();
        int res = 0;
        int currLen = 0;
        Map<Character, Integer> map = new HashMap<>();
        while (right<len) {
            if(!map.containsKey(s.charAt(right)) || map.get(s.charAt(right))<left) {
                // distinct character
                map.put(s.charAt(right),right);
                right++;
                currLen++;
            }
            else {
                res = Math.max(res, currLen);
                System.out.println(s.substring(left,right) + " "+left);
                left = map.get(s.charAt(right))+1;
                currLen = right - left;
                map.put(s.charAt(right),-1);
            }
        }
        return Math.max(res, right-left);
    }
}
