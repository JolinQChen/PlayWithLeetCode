package _AAInterviews.Wish;
// Given a string, find the length of the longest substring T that contains at most k distinct characters.
/**
 *
 * Input: s = "eceba", k = 2
 * Output: 3
 * Explanation: T is "ece" which its length is 3.
 *
 * sliding window
 * */
import java.util.*;
public class _340_LongestSubstringWithAtMostKDistinctCharacters {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        int len = s.length();
        if(len<=k) return len;
        Map<Character, Integer> memo = new HashMap<>();
        int distinct = 0;
        int longest = 0;
        int left = 0;
        int right= 0;
        for(; right<k; right++) {
            char tmp = s.charAt(right);
            if(!memo.containsKey(tmp)) distinct++;
            memo.put(s.charAt(right), memo.getOrDefault(tmp,0)+1);
        }
        while (right<s.length()) {
            char tmp = s.charAt(right);
            if(!memo.containsKey(tmp) || memo.get(tmp)==0) distinct++;
            memo.put(tmp,memo.getOrDefault(tmp,0)+1);


            while (distinct>k) {
                char tmp_left = s.charAt(left);
                memo.put(tmp_left, memo.get(tmp_left)-1);
                if(memo.get(tmp_left)==0) distinct--;
                left++;
            }

            longest = Math.max(longest, right-left+1);
            right++;
        }
        return longest;

    }
}
