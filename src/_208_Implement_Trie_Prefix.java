public class _208_Implement_Trie_Prefix {
    class Trie {
        class TrieNode{
            public TrieNode[] children;
            public boolean isWord;
            public TrieNode(){
                children = new TrieNode[26];
                isWord = false;
            }
        }

        /** Initialize your data structure here. */
        private TrieNode root;
        //尝试用int[]实现trie
        public Trie() {
            root = new TrieNode();
        }

        /** Inserts a word into the trie. */
        public void insert(String word) {
            TrieNode p = root;
            for(int i=0; i<word.length(); i++){
                int index = (int)(word.charAt(i)-'a');
                if(p.children[index]==null) p.children[index] = new TrieNode();
                p = p.children[index];
            }
            p.isWord = true;
        }

        private TrieNode find(String prefix){
            TrieNode p = root;
            for(int i=0; i<prefix.length(); i++){
                int index = (int)(prefix.charAt(i)-'a');
                if(p.children[index]==null) return null;
                else p = p.children[index];
            }
            return p;
        }

        /** Returns if the word is in the trie. */
        public boolean search(String word) {
            TrieNode res = find(word);
            return res!=null && res.isWord;
        }

        /** Returns if there is any word in the trie that starts with the given prefix. */
        public boolean startsWith(String prefix) {
            return find(prefix)!=null;
        }
    }

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */
}
