import java.util.*;
/**
 * Given a string s and a list of strings dict, you need to add a closed pair of bold tag <b> and </b>
 * to wrap the substrings in s that exist in dict. If two such substrings overlap, you need to wrap them
 * together by only one pair of closed bold tag. Also, if two substrings wrapped by bold tags are
 * consecutive, you need to combine them.
 *
 * Example 1:
 * Input:
 * s = "abcxyz123"
 * dict = ["abc","123"]
 * Output:
 * "<b>abc</b>xyz<b>123</b>"
 *
 *
 * Example 2:
 * Input:
 * s = "aaabbcc"
 * dict = ["aaa","aab","bc"]
 * Output:
 * "<b>aaabbc</b>c"
 * */
public class _616_Add_Bold_Tag_in_String {
    /**
     * brute force
     * Algorithm
     *
     * Let's work on first setting mask[i] = true if and only if the i-th letter is bold.
     * For each starting position i in S, for each word, if S[i] starts with word, we'll
     * set the appropriate letters bold.
     *
     * Now armed with the correct mask, let's try to output the answer. A letter in position i
     * is the first bold letter of the group if mask[i] && (i == 0 || !mask[i-1]), and is the
     * last bold letter if mask[i] && (i == N-1 || !mask[i+1]).
     *
     * Once we know which letters are the first and last bold letters of a group, we know where
     * to put the "<b>" and "</b>" tags.
     *
     * */
    public String addBoldTag(String S, String[] words) {
        StringBuilder sb = new StringBuilder();
        int N = S.length();
        boolean[] bold = new boolean[N];
        int cur = 0;
        for (int i=0; i<N; i++){
            // start at i in S, begin matching
            for(String word:words){
                boolean flag = true;
                int len = word.length();
                for(int k=0; k<len; k++){
                    if(i+k>=N || S.charAt(i+k)!=word.charAt(k)) {
                        flag = false;
                        break;
                    }
                }
                if(flag) {
                    for(int j=Math.max(cur,i); j<i+len; j++) bold[j]=true;
                    cur = Math.max(cur,i+len);
                }
            }
        }
        for(int i=0; i<N; i++){
            if(bold[i] && (i==0 || !bold[i-1])) sb.append("<b>");
            sb.append(S.charAt(i));
            if(bold[i] && (i==N-1 || !bold[i+1])) sb.append("</b>");
        }
        return sb.toString();


    }

}
