package _AAInterviews.Google;
import org.junit.Test;

import java.util.*;
public class AutoCompleteSystem {
    // trie
    class TrieNode {
        boolean isWord;
        TrieNode[] children;
        char val;

        public TrieNode(char val) {
            children = new TrieNode[27]; // space
            isWord = false;
            this.val = val;
        }
    }

    public Map<String, Integer> sentenceMap;
    public StringBuilder sb;
    public TrieNode root;
    public TrieNode searchNode;

    public AutoCompleteSystem(String[] sentences, int[] times) {
        sb = new StringBuilder();
        root = new TrieNode('*');
        searchNode = root;
        sentenceMap = new HashMap<>();
        for(int i=0; i<sentences.length; i++) {
            sentenceMap.put(sentences[i], times[i]);
            constructTrie(sentences[i]);
        }
    }

    private void constructTrie(String str) {
        int len = str.length();
        TrieNode cur = root;
        for(int i=0; i<len; i++) {
            int idx;
            if(str.charAt(i)==' ') idx = 26;
            else idx = str.charAt(i)-'a';
            if(cur.children[idx] == null) {
                cur.children[idx] = new TrieNode(str.charAt(i));
            }
            cur = cur.children[idx];
        }
        cur.isWord = true;
    }

    public List<String> input(char c) {
        if(c == '#') {
            // add to map
            constructTrie(sb.toString());
            sentenceMap.put(sb.toString(), sentenceMap.getOrDefault(sb.toString(),0)+1);
            // recover
            searchNode = root;
            sb = new StringBuilder();
            return new LinkedList<>();
        } else {
            int idx;
            if(c == ' ') idx = 26;
            else idx = c - 'a';
            if(searchNode == null || searchNode.children[idx] == null) {
                searchNode = null;
                sb.append(c);
                return new LinkedList<>();
            }
            searchNode = searchNode.children[idx];
            sb.append(c);
            return sentencesFromCurNode();
        }
    }

    private List<String> sentencesFromCurNode(){
        if(searchNode == null) return new LinkedList<>();
        PriorityQueue<String> pq = new PriorityQueue<>((str1, str2) -> (
                sentenceMap.get(str1) - sentenceMap.get(str2) == 0 ? str1.compareToIgnoreCase(str2) : sentenceMap.get(str1) - sentenceMap.get(str2)
        ));
        TrieNode curNode = searchNode;
        String str = sb.toString();
        dfs(pq, curNode, str);
        List<String> res = new LinkedList<>();
        while(!pq.isEmpty()) res.add(pq.poll());
        Collections.reverse(res);
        return res;
    }

    private void dfs(PriorityQueue<String> pq, TrieNode curNode, String str) {
        if(curNode.isWord) {
            pq.add(str);
            if(pq.size()>3) pq.poll();
        }
        for(int i=0; i<27; i++) {
            if(curNode.children[i] != null) {
                dfs(pq, curNode.children[i], str+curNode.children[i].val);
            }
        }
    }
}
/**
 * Your AutocompleteSystem object will be instantiated and called as such:
 * AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
 * List<String> param_1 = obj.input(c);
 */
