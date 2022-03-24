package _All;

import org.junit.Assert;
import org.junit.Test;

public class _161_One_Edit_Distance {
    public boolean isOneEditDistance(String s, String t) {
        int s_len = s.length();
        int t_len = t.length();
        if(Math.abs(s_len-t_len)>1) return false;
        int difCount = 0;
        if(s_len == t_len) {
            for(int i=0; i<s_len; i++) {
                if(s.charAt(i)!=t.charAt(i)){
                    difCount++;
                    if(difCount>1) return false;
                }
            }
            return difCount == 1;
        }
        if(s_len == t_len+1) {
            String str = s;
            s = t;
            t = str;
        }
        // s_len = t_len-1
        int idx = 0;
        while (idx<s.length()) {
            if(s.charAt(idx) != t.charAt(idx)) {
                return s.substring(idx).equals(t.substring(idx+1));
            }
            idx++;
        }
        return true;
    }

    @Test
    public void test(){
        Assert.assertEquals(true, isOneEditDistance("a",""));
    }
}
