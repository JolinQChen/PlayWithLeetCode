package _AAInterviews.Roblox;
// leetcode 1297. Maximum Number of Occurrences of a Substring
import java.util.*;

public class MostFrequentSubstring {
    public int maxFreq(String s, int maxLetters, int minSize, int maxSize) {
        // 只需要考虑minSize就好了，maxSize根本不用考虑
        int n = s.length();
        HashMap<String,Integer> freqMap = new HashMap<>();
        int maxfreq = -1;
        for(int i = 0; i < n - minSize + 1; i++){
            String substr = s.substring(i,i + minSize);
            Set<Character> charSet = new HashSet<>();
            for(char c : substr.toCharArray()){
                charSet.add(c);
            }
            if(charSet.size() <= maxLetters){
                freqMap.put(substr,freqMap.getOrDefault(substr,0) + 1);
                maxfreq = Math.max(maxfreq,freqMap.get(substr));
            }
        }
        return maxfreq == -1 ? 0 : maxfreq;
    }
}
