package _All;

import sun.util.resources.cldr.zh.CalendarData_zh_Hans_HK;

import java.util.*;
/**
 * In an alien language, surprisingly they also use english lowercase letters, but possibly in a different order.
 * The order of the alphabet is some permutation of lowercase letters.
 *
 * Given a sequence of words written in the alien language, and the order of the alphabet,
 * return true if and only if the given words are sorted lexicographicaly in this alien language.
 * */

public class _953_Verifying_an_Alien_Dictionary {
    //只有每对相邻单词都是有序的，那么整个words才是有序的（可传递性）
    public boolean isAlienSorted(String[] words, String order) {
        Map<Character, Integer> map = new HashMap<>();
        int index = 0;
        for(char c:order.toCharArray()){
            if(map.containsKey(c)) return false;
            map.put(c,index++);
        }
        int len = words.length;
        for(int i=0; i<len-1; i++){
            //比较当前word和后一个之间是否符合顺序
            String cur = words[i];
            String next = words[i+1];
            int cur_len = cur.length();
            int pointer = 0;
            while (pointer<cur_len){
                if(pointer>=next.length()) return false;
                else if(cur.charAt(pointer)==next.charAt(pointer)) pointer++;
                else break;
            }
            if(pointer<cur_len && map.get(cur.charAt(pointer))>map.get(next.charAt(pointer))) return false;
        }
        return true;
    }
}
