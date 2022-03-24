package _All;
import java.util.*;

public class _316_Remove_Duplicate_Letters {
    public String removeDuplicateLetters(String s) {
        if(s.length()<=1) return s;
        int[] count = new int[26];
        char[] cc = s.toCharArray();
        // fill count
        for(char c:cc) {
            count[c-'a']++;
        }
        int pos = 0;
        for (int i=0; i<cc.length; i++) {
            if(cc[i]<cc[pos]) pos = i;
            if(--count[cc[i]-'a']==0) break;
        }
        return cc[pos]+removeDuplicateLetters(s.substring(pos+1).replaceAll("" + cc[pos], ""));
    }

}
