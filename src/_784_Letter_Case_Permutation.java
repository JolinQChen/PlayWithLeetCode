import java.util.*;
/**
 * Given a string S, we can transform every letter individually to be
 * lowercase or uppercase to create another string.  Return a list of all
 * possible strings we could create.
 *
 * Examples:
 * Input: S = "a1b2"
 * Output: ["a1b2", "a1B2", "A1b2", "A1B2"]
 *
 * Input: S = "3z4"
 * Output: ["3z4", "3Z4"]
 *
 * Input: S = "12345"
 * Output: ["12345"]
 * */

public class _784_Letter_Case_Permutation {
    List<String> res;
    public List<String> letterCasePermutation(String S) {
        res = new LinkedList<>();
        backTracking(S, 0, "");
        return res;
    }
    private void backTracking(String s, int pos, String cur){
        if(cur.length() == s.length()) {
            res.add(cur);
            return;
        }
        else {
            char tmp = s.charAt(pos);
            if(!Character.isDigit(tmp)){
                if(tmp>='a' && tmp <='z') {
                    cur += (char) (tmp - 'a' + 'A');
                    backTracking(s,pos+1, cur);
                    cur = cur.substring(0,cur.length()-1);
                }
                else if(tmp>='A' && tmp <='Z'){
                    cur += (char) (tmp - 'A' + 'a');
                    backTracking(s,pos+1, cur);
                    cur = cur.substring(0,cur.length()-1);
                }
            }
            //是数字
            cur += tmp;
            backTracking(s,pos+1,cur);


        }
    }
    public static void main(String[] args){
        _784_Letter_Case_Permutation test = new _784_Letter_Case_Permutation();
        List<String> ls = test.letterCasePermutation("a1b2");
        System.out.println("Testing...");
        for(String s:ls) System.out.println(s);

    }
}
