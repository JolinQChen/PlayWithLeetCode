package _AAInterviews.Google;
import org.junit.Test;

import java.util.*;
public class _1320_Minimum_Distance_to_Type_a_Word_Using_Two_Fingers {
    Map<String, Integer> dpMap;
    public int minimumDistance(String word) {
        // char[] cc = word.toCharArray();
        dpMap = new HashMap<>();
        return helper(word,0,'#','#');
    }

    private int helper(String word, int idx, char end1, char end2) {
        if(idx == word.length()) {
            return 0;
        }
        String key = word+String.valueOf(idx)+String.valueOf(end1)+String.valueOf(end2);
        if(dpMap.containsKey(key)) {
            return dpMap.get(key);
        }
        char c = word.charAt(idx);
        int dist_1 = 0;
        int dist_2 = 0;
        if(end1!='#') {
            dist_1 = countDis(end1, c);
        }
        if(end2!='#') {
            dist_2 = countDis(end2, c);
        }
        // try group1
        int group1_res = dist_1+helper(word, idx+1, c, end2);
        // group2
        int group2_res = dist_2+helper(word, idx+1, end1, c);
        dpMap.put(key, Math.min(group2_res, group1_res));
        return dpMap.get(key);
    }

    private int[] pos(char c){
        return new int[]{(c-'A') / 6,(c-'A') % 6};
    }

    private int countDis(char a, char b){
        int[] pos_a = pos(a);
        int[] pos_b = pos(b);
        return Math.abs(pos_a[0]-pos_b[0]) + Math.abs(pos_a[1]-pos_b[1]);
    }

    @Test
    public void test(){
        System.out.println(minimumDistance("abcd"));
    }
}
