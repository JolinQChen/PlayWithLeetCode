package _stack;

import java.util.Stack;

/**
 * () = 1;
 * (A) = 2*A
 * AB = A+B
 * */

public class _856_Score_of_Parentheses {
    public int scoreOfParentheses(String S) {
        Stack<Integer> stack = new Stack<>(); //0表示左边括号
        if(S.length()==0) return 0;
        char[] s_char = S.toCharArray();
        for(char c:s_char){
            if(c=='(') stack.push(0);
            else {
                int count = 0;
                while(stack.peek()!=0){
                    count += stack.pop();
                }
                if(count == 0) count = 1;
                else count *= 2;
                stack.pop();
                stack.push(count);
            }
        }
        int res = 0;
        while(!stack.isEmpty()){
            res += stack.pop();
        }
        return res;
    }
}
