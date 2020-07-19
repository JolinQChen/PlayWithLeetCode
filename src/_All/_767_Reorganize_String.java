package _All;

import java.util.*;
/**
 * Given a string S, check if the letters can be rearranged so that two characters that are adjacent to
 * each other are not the same.
 *
 * If possible, output any possible result.  If not possible, return the empty string.
 *
 * Example 1:
 *
 * Input: S = "aab"
 * Output: "aba"
 * Example 2:
 *
 * Input: S = "aaab"
 * Output: ""
 *
 * */


public class _767_Reorganize_String {
    public static String reorganizeString(String S) {
        char[] s_c = S.toCharArray();
        Map<Character, Integer> map = new HashMap<>();
        for(char c:s_c){
            int tmp = 0;
            if(map.containsKey(c)) tmp = map.get(c);
            map.put(c,tmp+1);
        }
        PriorityQueue<Character> pq = new PriorityQueue<>(
                new Comparator<Character>() {
                    @Override
                    public int compare(Character o1, Character o2) {
                        return map.get(o2)-map.get(o1)==0?1:map.get(o2)-map.get(o1);
                    }
                }
        );
        for(char c:map.keySet()){
            pq.add(c);
        }
        StringBuilder res = new StringBuilder();
        while (pq.size()>1){
            char next1 = pq.poll();
            char next2 = pq.poll();
            res.append(next1);
            res.append(next2);
            map.put(next1,map.get(next1)-1);
            map.put(next2,map.get(next2)-1);
            if(map.get(next1)>0) pq.add(next1);
            if(map.get(next2)>0) pq.add(next2);
        }
        if(pq.size()==1){
            char t = pq.poll();
            if(map.get(t)>1) return "";
            else res.append(t);
        }
        return res.toString();
    }
    public static void main(String[] args){
        String res = reorganizeString("caaab");
        System.out.println(res);
    }

}
