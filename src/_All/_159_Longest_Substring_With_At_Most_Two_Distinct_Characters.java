package _All;

import java.util.*;

public class _159_Longest_Substring_With_At_Most_Two_Distinct_Characters {
    public static int lengthOfLongestSubstringTwoDistinct(String s) {

        int res = 0;
        Map<Character, Integer> map = new HashMap<>();

        int len = s.length();
        if(len<=1) return len;
        int left = 0;
        int right = 1;
        map.put(s.charAt(left), map.getOrDefault(s.charAt(left),0)+1);

        Character[] curr = new Character[2];
        curr[0] = s.charAt(left);

        while (right<len) {
            char tmp_r = s.charAt(right);
            if(curr[0]==null || curr[1]==null || tmp_r==curr[0] || tmp_r==curr[1]) {
                if(curr[0]==null && curr[1]!=tmp_r) curr[0] = tmp_r;
                else if(curr[1]==null && curr[0]!=tmp_r) curr[1] = tmp_r;
                map.put(tmp_r,map.getOrDefault(tmp_r,0)+1);
                right++;
            }
            else {
                res = Math.max(res, right - left);
                while (left<right) {
                    char tmp_l = s.charAt(left);
                    left++;
                    map.put(tmp_l, map.get(tmp_l)-1);
                    if(map.get(tmp_l)==0) {
                        if(curr[0]==tmp_l) curr[0] = null;
                        else curr[1] = null;
                        break;
                    }
                }
            }
        }
//        if(curr[0]==null || curr[1]==null) return res;
        return Math.max(res,right-left);

    }
    public static void main(String[] args) {
        String test = "eceba";
        System.out.println(lengthOfLongestSubstringTwoDistinct(test));
    }
}
