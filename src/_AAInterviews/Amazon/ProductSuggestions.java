package _AAInterviews.Amazon;

import java.util.*;
public class ProductSuggestions {
    // Trie
    private class TrieNode {
        public TrieNode[] children;
        public boolean isWord;
        public TrieNode() {
            children = new TrieNode[26];
            isWord = false;
        }
    }
    private TrieNode root;
    private void insertWord(String word) {
        TrieNode p = root;
        int len = word.length();
        for(int i=0; i<len; i++) {
            int idx = (int)(word.charAt(i) - 'a');
            if(p.children[idx] == null) p.children[idx] = new TrieNode();
            p = p.children[idx];
        }
        p.isWord = true;
    }

    private TrieNode findPrefix(String pre) {
        TrieNode p = root;
        int len = pre.length();
        for(int i=0; i<len; i++) {
            int idx = (int)(pre.charAt(i)-'a');
            if(p.children[idx]==null) return null;
            p = p.children[idx];
        }
        return p;
    }



    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        root = new TrieNode();
        List<List<String>> res = new ArrayList<>();
        // store products
        for(String product : products) {
            insertWord(product);
        }
        int len = searchWord.length();
        for(int i=1; i<=len; i++) {
            String pre = searchWord.substring(0,i);
            TrieNode node = findPrefix(pre);
            List<String> list = new ArrayList<>();
            dfs(list, node, new StringBuilder(pre));
            res.add(list);
        }
        return res;
    }

    private void dfs(List<String> list, TrieNode node, StringBuilder pre) {
        if(list.size()>=3) return;
        if(node == null) return;
        if(node.isWord)
            list.add(pre.toString());

        for(int i = 0; i<26; i++) {
            if(node.children[i]!=null) {
                pre.append((char) ('a' + i));
                dfs(list,node.children[i],pre);
                pre.delete(pre.length()-1,pre.length());
                if(list.size()>=3) break;
            }
        }
    }
}
