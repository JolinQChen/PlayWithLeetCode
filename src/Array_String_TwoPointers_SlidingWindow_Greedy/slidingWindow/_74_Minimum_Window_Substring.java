package Array_String_TwoPointers_SlidingWindow_Greedy.slidingWindow;

import java.util.*;

public class _74_Minimum_Window_Substring {
    public static String minWindow(String s, String t) {
        if(s.length()<t.length()) return "";
        
        int left=0, right = 0;
        int len = s.length();
        int currMin = len;
        String res = "";
        Map<Character, Integer> compareMap = new HashMap<>();
        Map<Character, Integer> storeMap = new HashMap<>(); // count charactor in string t
        for(int i=0; i<t.length(); i++) {
            char c = t.charAt(i);
            storeMap.put(c, storeMap.getOrDefault(c, 0)+1);
            compareMap.put(c,0);
        }

        char[] c = s.toCharArray();
        int remainChar = storeMap.keySet().size();
        while(right < len) {
            if(storeMap.containsKey(c[right])) {
                compareMap.put(c[right], compareMap.get(c[right])+1);
                if(compareMap.get(c[right]).equals(storeMap.get(c[right]))) {
                    // one charactor fully matches
                    remainChar--;
                }
                if(remainChar == 0) {
                    // all char fully matches, eliminate from left
                    while(left<right && (!storeMap.containsKey(c[left]) || compareMap.get(c[left]) > storeMap.get(c[left]))) {
                        if(compareMap.containsKey(c[left])) compareMap.put(c[left], compareMap.get(c[left])-1);
                        left++;
                    }
                    if(right-left+1 <= currMin) {
                        currMin = right-left+1;
                        res = s.substring(left, right+1);
                    }

                    // begain next search, move right first
                    compareMap.put(c[left], compareMap.get(c[left])-1);
                    remainChar++;
                    left++;

                }
            }
            right++;
        }
        return res;
    }
    public static void main(String[] args) {
        String res = minWindow("ADOBECODEBANC" , "ABC");
        System.out.println(res);
    }
}