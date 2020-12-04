package _AAInterviews.Amazon;

import java.util.*;

public class KDistinct {
    public static int countKDistinct(String str, int k) {
        int res = 0;
        int left = 0;
        int right = 0;
        int len = str.length();
        int[] memo = new int[26];
        int distinct = 0;
        while (right<len) {
            if(memo[str.charAt(right)-'a']==0) {
                distinct++;
            }
            memo[str.charAt(right)-'a']++;
            if(distinct==k) {
                int left_pre = left;
                while (memo[str.charAt(left)-'a']>1){
                    memo[str.charAt(left)-'a']--;
                    left++;
                }
                int right_pre = right;
                right++;
                while (right<len && memo[str.charAt(right)-'a']>0) {
                    right++;
                }
                res += (left-left_pre+1)*(right-right_pre);
                memo[str.charAt(left)-'a']--;
                if(memo[str.charAt(left)-'a']==0) distinct--;
                left++;
                right = right_pre;


            }
            right++;
        }
        return res;
    }
    public static void main(String[] args){
        String str = "abaca";
        System.out.println(countKDistinct(str,2));
    }
}
