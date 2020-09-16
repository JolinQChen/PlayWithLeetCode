package _AAInterviews.Roblox;

import java.util.*;

/**
 * Prefix : An expression is called the prefix expression if the operator appears in the expression before the operands. Simply of the form (operator operand1 operand2).
 * Example : *+AB-CD (Infix : (A+B) * (C-D) )
 *
 * Postfix: An expression is called the postfix expression if the operator appears in the expression after the operands. Simply of the form (operand1 operand2 operator).
 * Example : AB+CD-* (Infix : (A+B * (C-D) )
 *
 * */
public class PrefixToPostfix {
    private static Set<Character> operators;
    public static String prefixToPostfix(String str){
        operators = new HashSet<>();
        operators.add('+');
        operators.add('-');
        operators.add('*');
        operators.add('/');
        char[] cs = str.toCharArray();
        Stack<String> stack = new Stack<>();
        int len = cs.length;
        for(int i=len-1; i>=0; i--) {
            if(operators.contains(cs[i])) {
                String s1 = stack.pop();
                String s2 = stack.pop();
                String tmp = s1+s2+cs[i];
                stack.push(tmp);
            }
            else stack.push(String.valueOf(cs[i]));
        }
        return stack.peek();
    }
    public static void main(String[] args) {
        String str = "*+AB-CD";
        System.out.println(prefixToPostfix(str));
    }
}
