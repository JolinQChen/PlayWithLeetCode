package Citrix;

import java.util.Stack;

public class PrefixToPostfix {
    private static boolean isOperator(char c){
        if(c == '+'||c=='-'||c=='*'||c=='/') return true;
        return false;
    }
    public static String prefixToPostfix(String prefixes){
        /*
        * Read the Prefix expression in reverse order (from right to left)
        * If the symbol is an operand, then push it onto the Stack
        * If the symbol is an operator, then pop two operands from the Stack
        * Create a string by concatenating the two operands and the operator after them.
        * string = operand1 + operand2 + operator
        * And push the resultant string back to Stack
        * Repeat the above steps until end of Prefix expression.
        * */
        Stack<String> stack = new Stack<>();
        int pointer = prefixes.length()-1;
        if(pointer == 0) return prefixes;
        while(pointer>=0){
            if(!isOperator(prefixes.charAt(pointer))) stack.push(String.valueOf(prefixes.charAt(pointer)));
            else {
                // meet an operator
                String operand1 = stack.pop();
                String operand2 = stack.pop();
                String str = operand1 + operand2 + String.valueOf(prefixes.charAt(pointer));
                stack.push(str);
            }
            pointer--;
        }
        return stack.peek();
    }
    public static void main(String[] args){
        String postfix = prefixToPostfix("*-A/BC-/AKL");
        System.out.println(postfix);
    }
}
