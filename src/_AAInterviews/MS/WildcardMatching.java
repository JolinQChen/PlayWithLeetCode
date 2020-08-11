package _AAInterviews.MS;
import java.util.*;
public class WildcardMatching {



    public boolean isMatch(String s, String p) {
        int s_idx = 0;
        int p_idx = 0;
        int s_len = s.length();
        int p_len = p.length();

        int star_idx = -1;
        int s_tmp_idx = -1;
        while (s_idx<s_len) {
            if(p_idx<p_len && (p.charAt(p_idx)=='?'||p.charAt(p_idx)==s.charAt(s_idx))) {
                p_idx++;
                s_idx++;
            }
            else if(p_idx < p_len && p.charAt(p_idx)=='*') {
                // get a star
                star_idx = p_idx;
                s_tmp_idx = s_idx;
                p_idx++;
            }
            else if(star_idx==-1) {
                // no match and no previous star
                return false;
            }
            else {
                // no match, return to previous star
                p_idx = star_idx+1;
                s_idx = s_tmp_idx+1;
                s_tmp_idx = s_idx;
            }
        }
        for(int i=p_idx; i<p_len; i++) {
            if(p.charAt(i)!='*') return false;
        }
        return true;
    }
}
