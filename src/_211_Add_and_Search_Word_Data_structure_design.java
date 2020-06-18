import sun.jvm.hotspot.debugger.linux.LinuxDebugger;

import java.util.*;
/**
 * addWord("bad")
 * addWord("dad")
 * addWord("mad")
 * search("pad") -> false
 * search("bad") -> true
 * search(".ad") -> true
 * search("b..") -> true
 * */



public class _211_Add_and_Search_Word_Data_structure_design {
    /**
     * 和前面那道构造Trie的区别在于search中可以用.代替任意一个字母，这里可以用dfs搜索即可
     * */
    class WordDictionary {
        class TrieNode {
            public TrieNode[] children;
            public boolean isWord;
            public TrieNode(){
                children = new TrieNode[26];
                isWord = false;
            }
        }
        /** Initialize your data structure here. */
        private TrieNode root;
        public WordDictionary() {
            root = new TrieNode();
        }

        /** Adds a word into the data structure. */
        public void addWord(String word) {
            TrieNode p = root;
            for(int i=0; i<word.length(); i++){
                int index = (int)(word.charAt(i)-'a');
                if(p.children[index]==null) p.children[index] = new TrieNode();
                p = p.children[index];
            }
            p.isWord = true;
        }

        /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
        public boolean search(String word) {
            return searchWord(root, word);
        }

        private boolean searchWord(TrieNode curNode, String word){
            //TrieNode p = curNode;
            char c = word.charAt(0);
            if(word.length()==1){ //边界条件，长度为1

                if(isAlpha(c)) {
                    int index = (int) (c-'a');
                    return curNode.children[index]!=null && curNode.children[index].isWord;
                }
                else {
                    for(int i=0; i<26; i++){
                        if(curNode.children[i]!=null && curNode.children[i].isWord) return true;
                    }
                    return false;
                }
            }
            else{
                if(isAlpha(c)) {
                    int index = (int) (c-'a');
                    if(curNode.children[index]==null) return false;
                    return searchWord(curNode.children[index], word.substring(1));
                }
                else {
                    // 首位为.
                    boolean flag;
                    for(int i=0; i<26; i++){
                        if(curNode.children[i]!=null){
                            flag = searchWord(curNode.children[i],word.substring(1));
                            if(flag)return true;
                        }
                    }
                    return false;
                }
            }

        }

        private boolean isAlpha(char c){
            return c>='a' && c<='z';
        }
    }

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */
}
