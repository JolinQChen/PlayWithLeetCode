package _AAInterviews.Google;
import org.junit.Test;

import java.sql.SQLOutput;
import java.util.*;
public class _792_Number_of_Matching_Subsequences {
    Map<Character, List<Integer>> map;
    public int numMatchingSubseq(String s, String[] words) {
        map = new HashMap<>();
        int len = s.length();
        for(int i=0; i<len; i++) {
            if(!map.containsKey(s.charAt(i))) map.put(s.charAt(i), new ArrayList<>());
            map.get(s.charAt(i)).add(i);
        }
        int count = 0;
        for(String word:words) {
            if(isSubSeq(word)) count++;
        }
        return count;
    }
    private boolean isSubSeq(String word) {
        char[] cc = word.toCharArray();
        int idx = -1;
        for(char c:cc) {
            if(!map.containsKey(c)) return false;
            List<Integer> idxList = map.get(c);
            boolean found = false;
            for(int i=0; i<idxList.size(); i++) {
                if(idxList.get(i) > idx) {
                    found = true;
                    idx = idxList.get(i);
                    break;
                }
            }
            if(!found) return false;
        }
        return true;
    }

    @Test
    public void test(){
        System.out.println(numMatchingSubseq("abcde", new String[]{"a","bb","acd","ace"}));
        System.out.println(isSubSeq("bb"));
    }
}
