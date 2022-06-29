package _AAInterviews.Google.calculater_notation_parenthesis;
import java.util.*;
public class _150_Evaluate_Reverse_Polish_Notation {
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        for(String token:tokens) {
            if(isOperator(token)) {
                int op2 = stack.pop();
                int op1 = stack.pop();
                if(token.equals("*")) stack.push(op1*op2);
                else if(token.equals("+")) stack.push(op1+op2);
                else if(token.equals("-")) stack.push(op1-op2);
                else if(token.equals("/")) stack.push(op1/op2);
            } else {
                stack.push(Integer.parseInt(token));
            }
        }
        return stack.pop();
    }

    private boolean isOperator(String str){
        if(str.equals("+") || str.equals("-") || str.equals("*") || str.equals("/")) return true;
        return false;
    }

}
