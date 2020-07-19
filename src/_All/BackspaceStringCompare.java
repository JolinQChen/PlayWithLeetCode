package _All;

import java.util.*;
public class BackspaceStringCompare {
    public boolean backspaceCompare(String S, String T) {
        String s_ = sort(S);
        String t_ = sort(T);
        return s_.equals(t_);

    }
    private String sort(String S){
        Stack<Character> stack = new Stack<>();
        char[] s_char = S.toCharArray();
        for(char tmp:s_char){
            if(tmp != '#'){
                stack.push(tmp);
            }
            else{
                //是'#'，倒出来一个
                if(!stack.isEmpty()){
                    stack.pop();
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        while(!stack.isEmpty()) sb.append(stack.pop());
        return sb.toString();
    }

}
