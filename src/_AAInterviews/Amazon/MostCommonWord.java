package _AAInterviews.Amazon;

import java.util.*;
public class MostCommonWord {
    public String mostCommonWord(String paragraph, String[] banned) {
        Set<String> ban = new HashSet<>();
        for(String tmp_s:banned) {
            ban.add(tmp_s);
        }
        Map<String, Integer> map = new HashMap<>();
        String res = "";
        int maxCount = 0;
        paragraph += " ";
        char[] paragraph_char = paragraph.toCharArray();
        StringBuilder word = new StringBuilder();

        for(char c: paragraph_char) {
            if(Character.isLetter(c)) {
                word.append(Character.toLowerCase(c));
            }
            else if(word.length()>0){
                String cur = word.toString();
                if(!ban.contains(cur)) {
                    map.put(cur,map.getOrDefault(cur,0)+1);
                    res = maxCount > map.get(cur)?res:cur;
                    maxCount = Math.max(maxCount, map.get(cur));
                }
                word = new StringBuilder();
            }
        }
        return res;
    }

    // 法2：
    public String mostCommonWord2(String paragraph, String[] banned) {
        Set<String> ban = new HashSet<>();
        for(String str:banned) ban.add(str);
        String[] words = paragraph.split("\\W");


        Map<String, Integer> map = new HashMap<>();
        int count = 0;
        String res = "";
        for(String word:words) {
            word = word.toLowerCase();
            if(!ban.contains(word) && !word.equals("")) {

                map.put(word, map.getOrDefault(word,0)+1);
                if(count<map.get(word)) {
                    count = map.get(word);
                    res = word;
                }
            }
        }
        return res;
    }
}
