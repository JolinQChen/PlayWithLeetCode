package _AAInterviews.Google;
import java.util.*;
public class _244_Shortest_Word_Distance_II {
    Map<String, List<Integer>> map;
    // Map<String, Integer> memo;

    public _244_Shortest_Word_Distance_II(String[] wordsDict) {
        map = new HashMap<>();
        // memo = new HashMap<>();
        for(int i=0; i<wordsDict.length-1; i++) {
            String word = wordsDict[i];
            if(!map.containsKey(word)) map.put(word, new ArrayList<>());
            map.get(word).add(i);
            // if(!word.equals(wordsDict[i+1])) {
            //     String key1 = word +"_"+ wordsDict[i+1];
            //     String key2 = wordsDict[i+1] +"_"+ word;
            //     memo.put(key1, 1);
            //     memo.put(key2, 1);
            // }
        }
        if(!map.containsKey(wordsDict[wordsDict.length-1])) map.put(wordsDict[wordsDict.length-1], new ArrayList<>());
        map.get(wordsDict[wordsDict.length-1]).add(wordsDict.length-1);
    }

    public int shortest(String word1, String word2) {
        // String key1 = word1+"_"+word2;
        // String key2 = word2+"_"+word1;
        // if(memo.containsKey(key1)) return memo.get(key1);
        List<Integer> list1 = map.get(word1);
        List<Integer> list2 = map.get(word2);
        int len1 = list1.size();
        int len2 = list2.size();
        int idx1 = 0;
        int idx2 = 0;
        int res = Integer.MAX_VALUE;
        while(idx1<len1 && idx2< len2) {
            while(idx2<len2 && list2.get(idx2)<list1.get(idx1)) {
                idx2++;
            }
            if(idx2>0) res = Math.min(Math.abs(list1.get(idx1)-list2.get(idx2-1)), res);
            if(idx2<len2) {
                res = Math.min(Math.abs(list1.get(idx1)-list2.get(idx2)), res);
                while(idx1<len1 && list1.get(idx1)<list2.get(idx2)) {
                    idx1++;
                }
                if(idx1>0) res = Math.min(Math.abs(list2.get(idx2)-list1.get(idx1-1)), res);
                if(idx1<len1) res = Math.min(Math.abs(list1.get(idx1)-list2.get(idx2)), res);
            }
        }
        // memo.put(key1, res);
        // memo.put(key2, res);
        return res;
    }
}
