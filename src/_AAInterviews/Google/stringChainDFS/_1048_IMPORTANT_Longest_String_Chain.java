package _AAInterviews.Google.stringChainDFS;

import org.junit.Test;

import java.util.*;

public class _1048_IMPORTANT_Longest_String_Chain {

    /**
     * Given a list of words, each word consists of English lowercase letters.
     *
     * Let's say word1 is a predecessor of word2 if and only if we can add exactly one letter anywhere in word1 to make it equal to word2.  For example, "abc" is a predecessor of "abac".
     *
     * A word chain is a sequence of words [word_1, word_2, ..., word_k] with k >= 1, where word_1 is a predecessor of word_2, word_2 is a predecessor of word_3, and so on.
     *
     * Return the longest possible length of a word chain with words chosen from the given list of words.
     * 示例：
     * Input: ["a","b","ba","bca","bda","bdca"]
     * Output: 4
     * Explanation: one of the longest word chain is "a","ba","bda","bdca".
     *
     * 1 <= words.length <= 1000
     * 1 <= words[i].length <= 16
     * words[i] 仅由小写英文字母组成。
     * */


    // 以下为DFS解法
    int max=1;
    Map<String, Integer> memo;
    Map<Integer, Set<String>> map;
    int minLen;
    int maxLen;
    public int longestStrChain(String[] words) {
        if(words.length<=1) return words.length;
        memo = new HashMap<>();
        map = new HashMap<>();
        minLen = words[0].length();
        maxLen = words[0].length();
        for(String word: words) {
            int len = word.length();
            if(!map.containsKey(len)) map.put(len, new HashSet<>());
            map.get(len).add(word);
            minLen = Math.min(minLen, len);
            maxLen = Math.max(maxLen, len);
        }


        for(int len=minLen; len<=maxLen; len++) {
            if(!map.containsKey(len)) continue;
            Set<String> curStringSet = map.get(len);
            if(maxLen+1-len <= max) break;
            for(String curStr:curStringSet) {
                max = Math.max(dfs(curStr), max);
            }
        }
        return max;

    }

    private int dfs(String curStr) {
        if(memo.containsKey(curStr)) return memo.get(curStr);
        if(!map.containsKey(curStr.length()+1)) {
            memo.put(curStr, 1);
            return 1;
        }

        Set<String> nexts = map.get(curStr.length()+1);
        int res = 1;
        for(String next:nexts) {
            if(isPre(curStr, next)) {
                res = Math.max(res, 1+dfs(next));
            }
        }
        memo.put(curStr, res);
        return res;
    }

    private boolean isPre(String a, String b) {
        // lena-lenb = -1
        int alen = a.length();
        int idx = 0;
        while (idx<alen){
            if(a.charAt(idx)!=b.charAt(idx)) {
                break;
            }
            idx++;
        }

        while (idx<alen) {
            if(a.charAt(idx)!=b.charAt(idx+1)) return false;
            idx++;
        }
        return true;
    }



    // 以下为 DP
    // 按照字符串长度排序后再用最长升序子序列的做法即可
    /*
    这道题不用按照顺序取字符串。所以我们可以按照字符串长度升序排列整个数组后按照熟悉的方法做。
    相对以前的最长升序子序列，我们把升序这个条件换成是否为前身即可。
    */
    public int longestStrChain_dp(String[] words){
        Arrays.sort(words, (Comparator.comparingInt(String::length)));
        int[] dp = new int[words.length];
        int Max_dp = 0;
        for(int i=1; i<words.length; i++){
            for(int j=0; j<i; j++){
                if(isLinked(words[i], words[j])){
                    // words[j] -> words[i]
                    dp[i] = Math.max(dp[i], dp[j]+1);
                    Max_dp = Math.max(dp[i], Max_dp);
                }
            }
        }
        return Max_dp+1;
    }
    private boolean isLinked(String a, String b){
        //返回b是否为a的前身
        if(a.length() - b.length() != 1) return false;
        int i=0, j=0;
        while (i<a.length() && j<b.length()){
            if(a.charAt(i)==b.charAt(j)){
                i++;
                j++;
            }
            else i++;
        }
        return j==b.length();
    }
}


/*
* ["ksqvsyq","czvh","zczpzvdhx","zczpzvh","zczpzvhx","zcpzvh","zczvh","grukmj","ksqvsq","kssq","ksqsq","grukkmj","grukj","zczpzfvdhx"]
*
* */

