import java.util.*;
public class _242_Valid_Anagram {
    public static boolean isAnagram(String s, String t) {
        int len = s.length();
        if(len!= t.length()) return false;
        char[] c1 = s.toCharArray();
        char[] c2 = t.toCharArray();
        Arrays.sort(c1);
        Arrays.sort(c2);
        for(int i=0; i<len; i++){
            if(c1[i]!=c2[i]) return false;
        }
        return true;
    }


}
