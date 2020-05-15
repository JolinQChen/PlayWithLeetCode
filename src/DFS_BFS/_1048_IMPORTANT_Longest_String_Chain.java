package DFS_BFS;

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
    private int Max = 1;
    public int longestStrChain_dfs(String[] words) {

        if(words.length <=1) return words.length;
        int minLen = words[0].length();
        int maxLen = minLen;
        Map<Integer, Set<String>> map = new HashMap<>();
        //创建map
        for(String str:words){
            int len = str.length();
            minLen = Math.min(minLen, len);
            maxLen = Math.max(maxLen, len);
            Set<String> tmp_set = map.get(len);
            if(tmp_set == null) {
                tmp_set = new HashSet<>();
                map.put(len, tmp_set);
            }
            tmp_set.add(str);
        }
        for(int curLen=minLen; curLen<=maxLen; curLen++){
            Set<String> curSet = map.get(curLen);
            if(curSet == null) continue;
            if(maxLen+1-curLen <= Max) break;
            for(String next:curSet) {
                findNext(map, curLen, next);
            }
        }
        return Max;
    }

    private void findNext(Map<Integer, Set<String>> map, int curLen, String next){
        // curLen到next都走通了，找下一长度的
        Max = Math.max(Max, next.length()+1-curLen);
        Set<String> set = map.get(next.length()+1);
        if(set == null) return;
        Iterator<String> iterator = set.iterator();
        while (iterator.hasNext()){
            String tmp = iterator.next();
            //判断能不能连上
            if(match(next, tmp)){
                findNext(map, curLen, tmp);
                iterator.remove();
            }
        }

    }
    private boolean match(String str1, String str2){
        //前面已经保证了str2.length() - str1.length() = 1
        int i = 0;
        int len = str1.length();
        while (i < len && str2.charAt(i) == str1.charAt(i)) {
            i++;
        }

        if (i == len) return true;

        while (i < len && str2.charAt(i + 1) == str1.charAt(i)) {
            i++;
        }

        return i == len;
    }



    // 以下为 DP
    // 按照字符串长度排序后再用最长升序子序列的做法即可
    /*
    这道题不用按照顺序取字符串。所以我们可以按照字符串长度升序排列整个数组后按照熟悉的方法做。
    相对以前的最长升序子序列，我们把升序这个条件换成是否为前身即可。
    */
    public int longestStrChain_dp(String[] words){
        Arrays.sort(words, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.length() - o2.length();
            }
        });
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
