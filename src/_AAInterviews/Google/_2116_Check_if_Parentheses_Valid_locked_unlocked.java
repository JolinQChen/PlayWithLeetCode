package _AAInterviews.Google;
import org.junit.Test;

import java.util.*;
public class _2116_Check_if_Parentheses_Valid_locked_unlocked {
    public boolean canBeValid(String s, String locked) {
        char[] locked_cc = locked.toCharArray();
        char[] s_cc = s.toCharArray();
        if(s_cc.length % 2 == 1) return false;
        Stack<Integer> stack_locked = new Stack<>();
        Stack<Integer> stack_uni = new Stack<>();
        for(int i=0; i<s_cc.length; i++) {
            if(locked_cc[i]=='0') stack_uni.push(i);
            else if(s_cc[i]=='(') stack_locked.push(i);
            else {
                // locked ')'
                if(stack_locked.size()==0){
                    if(stack_uni.size()==0) return false;
                    // change a stack uni
                    stack_uni.pop();
                } else if(stack_uni.size()==0){
                    // change a stack locked
                    stack_locked.pop();
                } else {
                    int uni = stack_uni.peek();
                    int lock = stack_locked.peek();
                    if(uni>lock) {
                        stack_uni.pop();
                    } else stack_locked.pop();
                }
            }
        }
        if(stack_locked.size()>stack_uni.size()) return false;
        while(stack_locked.size()>0) {
            int lock = stack_locked.pop();
            int uni = stack_uni.pop();
            if(uni<lock) return false;
        }
        if(stack_uni.size() % 2 == 1) return false;
        return true;
    }

    @Test
    public void test(){
        String s = "())()))()(()(((())(()()))))((((()())(())";
        String locked = "1011101100010001001011000000110010100101";
        StringBuilder test = new StringBuilder();
        for(int i=0; i<s.length(); i++) {
            if(locked.charAt(i)=='0') test.append('O');
            else test.append(s.charAt(i));
        }
        System.out.println(test.toString());
    }
}
