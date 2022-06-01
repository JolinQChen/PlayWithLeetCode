package _AAInterviews.Google;

import org.junit.Test;
import java.util.*;
public class _87_Scramble_String {
    Map<String, Boolean> map = new HashMap<>();


    public boolean isScramble(String s1, String s2) {
        return isScrambleHelper(s1, s2);
    }

    private boolean isScrambleHelper(String s1, String s2){
        if(s1.equals(s2)) return true;
        if(s1.length()<=1) return false;
        String key = s1 + "_" + s2;
        if(map.containsKey(key)) {
            return map.get(key);
        }
        int len = s1.length();
        boolean res = false;
        for(int i=1; i<len; i++) {
            res = (isScramble(s1.substring(0,i), s2.substring(0,i)) && isScramble(s1.substring(i,len), s2.substring(i,len))) || (isScramble(s1.substring(0,i), s2.substring(len-i,len)) && isScramble(s1.substring(i,len),s2.substring(0,len-i)));

            if(res) {
                break;
            }
        }
        map.put(key, res);
        return res;
    }
    @Test
    public void testScrambleString(){
        System.out.println(isScramble("abcdefg", "bcafged"));
    }
}
