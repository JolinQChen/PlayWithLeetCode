package Citrix;
import java.util.*;
/**
 * input "mokkori"
 * palindrome substring: m,o,k,r,i,kk,okko
 * */

public class SubPalindrome {
    public static int subPalindrome(String str){
        int len = str.length();
        int res = 0;
        for(int i=0; i<len-1; i++){
            res += isPalinDouble(str, len, i, i+1)/2+1;
            res += isPalinSingle(str, len, i)/2;
        }
        res += isPalinSingle(str, len, len-1)/2+1;
        return res;
    }
    private static int isPalinDouble(String str, int len, int left, int right){
        //int len = str.length();
        //int count = 0;
        while(left>=0 && right<len){
            if(str.charAt(left)!=str.charAt(right)) break;
            left--;
            right++;
        }
        return right - left-1;
    }
    private static int isPalinSingle(String str, int len, int center){
        if(center == 0 || center == len-1) return 1;
        int left = center-1;
        int right = center+1;
        while(left>=0 && right<len){
            if(str.charAt(left)!=str.charAt(right)) break;
            left--;
            right++;
        }
        return right - left-1;
    }
}
