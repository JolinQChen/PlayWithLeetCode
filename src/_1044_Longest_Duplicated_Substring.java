//https://www.youtube.com/watch?v=BMvotl5vHvM&feature=youtu.be
import java.util.*;
public class _1044_Longest_Duplicated_Substring {
    //暴力法：
    public String longestDupSubstring_1(String S) {
        int len = S.length();
        Set<String> set = new HashSet<>();
        int resLen = 0;
        String ans = "";
        for(int i=0; i<len; i++){
            for(int j=i+resLen; j<=len; j++){
                String tmp = S.substring(i,j);//S.subString(i,j);
                if(set.contains(tmp)) {
                    resLen = j-i;
                    ans = tmp;
                }
                else set.add(tmp);
            }
        }
        return ans;
    }

// Trie法，靠看不懂
    private String S;
    private static class TrieNode {
        private TrieNode[] next;
        private int i;
        private int depth;

        public TrieNode(int i, int depth) {
            this.i = i;
            this.depth = depth;
        }
    }

    private boolean isLeaf(TrieNode node) {
        return node.next == null;
    }

    private int getIndex(int i, int depth) {
        return S.charAt(i + depth) - 'a';
    }

    private int addNew(TrieNode node, int i) {
        int depth = node.depth;
        if (i + depth == S.length()) return depth;
        if (isLeaf(node)) {
            node.next = new TrieNode[26];
            node.next[getIndex(node.i, node.depth)] = new TrieNode(node.i, depth + 1);
        }
        int c = getIndex(i, node.depth);
        TrieNode x = node.next[c];
        if (x == null) {
            node.next[c] = new TrieNode(i, depth + 1);
            return depth;
        }
        return addNew(x, i);
    }


    // T = O(N*K), S = O(N), where N is length of S and K avg depth of trie.
    public String longestDupSubstring(String S) {
        this.S = S;
        int maxLo = 0, maxLength = 0;
        TrieNode root = new TrieNode(0, 0);
        for (int i = 1; i + maxLength < S.length(); i++) {
            int len = addNew(root, i);
            if (len > maxLength) {
                maxLength = len;
                maxLo = i;
            }
        }
        return S.substring(maxLo, maxLo + maxLength);
    }
}
