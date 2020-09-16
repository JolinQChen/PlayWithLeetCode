package _AAInterviews.Roblox;

import java.util.*;

public class SubPalindrome {
    public static int countSub(String s) {
        Set<String> set = new HashSet<>();
        char[] cs = s.toCharArray();
        int len = cs.length;
        for(int c = 0; c<len*2-1; c++) {
            int left = c/2;
            int right;
            if(c%2==0) right = left;
            else right = left+1;
            while (left>=0 && right<len && cs[left]==cs[right]) {
                left--;
                right++;
                set.add(s.substring(left+1,right));
            }
        }
        int res = set.size();
        return res;
    }
    public static void main(String[] args) {
        String str = "aabc";
        System.out.println(countSub(str));
    }
}
