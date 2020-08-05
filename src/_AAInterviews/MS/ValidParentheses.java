package _AAInterviews.MS;
import java.util.*;
public class ValidParentheses {

    public boolean isValid(String s) {
        Map<Character, Character> map = new HashMap<>();
        map.put(')','(');
        map.put('}','{');
        map.put(']','[');

        Stack<Character> stack = new Stack<>();
        int idx = 0;
        int len = s.length();
        while (idx<len) {
            char tmp = s.charAt(idx);
            if(map.containsKey(tmp)) {
                if(stack.size()==0 || map.get(tmp)!=stack.pop()) return false;
            }
            else stack.push(tmp);
            idx++;
        }
        return stack.size() == 0;
    }
    public static void main(String[] args) {
        ValidParentheses test = new ValidParentheses();
        System.out.println(test.isValid("()[]"));
    }
}
