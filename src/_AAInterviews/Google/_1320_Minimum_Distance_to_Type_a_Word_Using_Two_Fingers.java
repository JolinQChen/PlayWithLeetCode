package _AAInterviews.Google;
import org.junit.Test;

import java.util.*;
public class _1320_Minimum_Distance_to_Type_a_Word_Using_Two_Fingers {
    int val;
    public int minimumDistance(String word) {
        // char[] cc = word.toCharArray();
        val = Integer.MAX_VALUE;
        List<Character> list_1 = new LinkedList<>();
        List<Character> list_2 = new LinkedList<>();
        helper(0, word, list_1, list_2);
        return val;
    }

    private void helper(int dist, String curWord, List<Character> list_1, List<Character> list_2) {
        if(curWord=="") {
            val = Math.min(val, dist);
            return;
        }
        char c = curWord.charAt(0);
        int dist_1 = 0;
        int dist_2 = 0;
        if(!list_1.isEmpty()) {
            char c_1 = list_1.get(list_1.size()-1);
            dist_1 = countDis(c_1, c);
        }
        if(!list_2.isEmpty()) {
            char c_2 = list_2.get(list_2.size()-1);
            dist_2 = countDis(c_2, c);
        }
        // try group1
        list_1.add(c);
        helper(dist+dist_1, curWord, list_1, list_2);
        list_1.remove(list_1.size()-1);
        // group2
        list_2.add(c);
        helper(dist+dist_1, curWord, list_1, list_2);
        list_2.remove(list_2.size()-1);
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
        minimumDistance("abcd");
    }
}
