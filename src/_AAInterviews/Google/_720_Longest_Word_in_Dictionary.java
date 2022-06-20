package _AAInterviews.Google;
import java.util.*;
public class _720_Longest_Word_in_Dictionary {
    public String longestWord(String[] words) {
        Arrays.sort(words, (w1, w2)->(w1.length()==w2.length()?w1.compareTo(w2):w2.length()-w1.length()));
//        String res = "";
//        int longest = 0;
        Set<String> set = new HashSet<>();
        for(int i=0; i<words.length; i++) set.add(words[i]);
        for(int i=0; i<words.length; i++) {
            int len = words[i].length();
            boolean find = true;
            for(int j =1; j<len; j++) {
                if(!set.contains(words[i].substring(0,j))) {
                    find = false;
                    break;
                }
            }
            if(find) {
                return words[i];
            }
        }
        return "";
    }
}
