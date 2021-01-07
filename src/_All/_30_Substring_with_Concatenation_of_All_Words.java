package _All;
import java.util.*;
public class _30_Substring_with_Concatenation_of_All_Words {
    /**暴力法已经很优了。。。N(n*m)*/
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> res = new ArrayList<>();
        if(words.length==0 || words[0].length()==0) return res;

        // 维护一个所有单词总长度的滑动窗口
        int wordLen = words[0].length();
        int totalLen = wordLen * words.length;
        if(s.length()<totalLen) return res;

        // hashmap1 存所有单词
        HashMap<String, Integer> allWordsMap = new HashMap<>();
        for(String word:words)
            allWordsMap.put(word, allWordsMap.getOrDefault(word,0)+1);
        //遍历所有子串
        for(int left=0; left<=s.length()-totalLen; left++) {
            //hashmap2 包含当前字符串中的单词
            HashMap<String, Integer> checkMap = new HashMap<>();
            int validCount = 0;
            for(int p=left; p<=left+totalLen-wordLen; p+=wordLen) {
                String tmp = s.substring(p,p+wordLen);
                if(allWordsMap.containsKey(tmp)){
                    checkMap.put(tmp,checkMap.getOrDefault(tmp,0)+1);
                    if(checkMap.get(tmp)>allWordsMap.get(tmp)) break;
                }
                else break;
                validCount++;
            }
            if(validCount == words.length) res.add(left);

        }
        return res;
    }
}
