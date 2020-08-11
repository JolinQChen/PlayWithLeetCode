package _AAInterviews.MS;
import java.util.*;
public class LetterCombinationsOfAPhoneNumber {
    // Backtracking
    Map<Character, char[]> map;
    List<String> list;
    String digits;
    int len;
    private void backTrackHelper(int idx, StringBuilder curPath) {
        if(idx==len) {
            if(curPath.length()>0) list.add(curPath.toString());
            return;
        }
        char cur = digits.charAt(idx);
        for(char c:map.get(cur)) {
            curPath.append(c);
            backTrackHelper(idx+1,curPath);
            curPath.delete(curPath.length()-1, curPath.length());
        }
    }

    public List<String> letterCombinations(String digits) {
        this.digits = digits;
        this.len = digits.length();
        map = new HashMap<>();
        map.put('2',new char[]{'a','b','c'});
        map.put('3',new char[]{'d','e','f'});
        map.put('4',new char[]{'g','h','i'});
        map.put('5',new char[]{'j','k','l'});
        map.put('6',new char[]{'m','n','o'});
        map.put('7',new char[]{'p','q','r','s'});
        map.put('8',new char[]{'t','u','v'});
        map.put('9',new char[]{'w','x','y','z'});

        list = new LinkedList<>();
        backTrackHelper(0,new StringBuilder());
        return list;

    }
}
