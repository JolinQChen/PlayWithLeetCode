package DFS_BFS_BackTracking;

import java.util.LinkedList;
import java.util.List;

/**
 * Given a string s, partition s such that every substring of the partition is a palindrome.
 * Return all possible palindrome partitioning of s.
 *
 * Input: "aab"
 * Output:
 * [
 *   ["aa","b"],
 *   ["a","a","b"]
 * ]
 * */

public class _131_Palindrome_Partitioning {
    private List<List<String>> res;

    public List<List<String>> partition(String s){
        res = new LinkedList<>();
        if(s == null || s.length()==0) return res;
        backTracking(s,0,new LinkedList<String>());
        return res;
    }

    private void backTracking(String s, int pos, List<String> cur) {
        if(pos >= s.length()) {
            System.out.println("it's the time");
            res.add(new LinkedList<>(cur));
            return;
        }
        int remain = s.length() - pos;
        for(int i=1; i<=remain; i++){
            String tmp = s.substring(pos,pos+i);
            if(!isPalindrome(tmp)) continue;
            cur.add(tmp);
            backTracking(s, pos+i, cur);
            cur.remove(cur.size()-1);
        }
    }
    private boolean isPalindrome(String s){
        int len = s.length();
        int left = 0;
        int right = len-1;
        while (left<right) {
            if(s.charAt(left)!=s.charAt(right)) return false;
            left++;
            right--;
        }
        return true;
    }

    public static void main(String[] args){
        _131_Palindrome_Partitioning test = new _131_Palindrome_Partitioning();
        String s = "efe";

    }
}
