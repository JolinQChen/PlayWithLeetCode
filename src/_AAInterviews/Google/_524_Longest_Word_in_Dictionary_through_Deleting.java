package _AAInterviews.Google;
import java.util.*;
public class _524_Longest_Word_in_Dictionary_through_Deleting {
    public String findLongestWord(String s, List<String> dictionary) {
        Collections.sort(dictionary);
        String longestFind = "";
        int longest = 0;
        for(String word:dictionary) {
            if(word.length()>longest && canMake(s, word)) {
                longest = word.length();
                longestFind = word;
            }
        }
        return longestFind;
    }

    private boolean canMake(String s, String word) {
        int p1 = 0, p2 = 0;
        while (p2<word.length()) {
            while(p1<s.length() && s.charAt(p1)!=word.charAt(p2)) p1++;
            if(p1==s.length()) return false;
            p2++;
            p1++;
        }
        return true;
    }
}
