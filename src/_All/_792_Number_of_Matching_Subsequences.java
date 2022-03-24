package _All;

import java.util.*;

public class _792_Number_of_Matching_Subsequences {
    public int numMatchingSubseq(String S, String[] words) {
        int ans = 0;
        ArrayList<Node1>[] heads = new ArrayList[26];
        for (int i = 0; i < 26; ++i)
            heads[i] = new ArrayList<Node1>();

        for (String word: words)
            heads[word.charAt(0) - 'a'].add(new Node1(word, 0));

        for (char c: S.toCharArray()) {
            ArrayList<Node1> old_bucket = heads[c - 'a'];
            heads[c - 'a'] = new ArrayList<Node1>();

            for (Node1 node: old_bucket) {
                node.index++;
                if (node.index == node.word.length()) {
                    ans++;
                } else {
                    heads[node.word.charAt(node.index) - 'a'].add(node);
                }
            }
            old_bucket.clear();
        }
        return ans;
    }
    class Node1 {
        String word;
        int index;

        public Node1(String w, int i) {
            word = w;
            index = i;
        }

}

}

