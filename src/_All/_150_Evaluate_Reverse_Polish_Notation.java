package _All;

import java.util.*;
/**
 * Valid operators are +, -, *, /. Each operand may be an integer or another expression.
 *
 * Note:
 *
 * Division between two integers should truncate toward zero.
 * The given RPN expression is always valid. That means the expression would always evaluate
 * to a result and there won't be any divide by zero operation.
 *
 * Input: ["2", "1", "+", "3", "*"]
 * Output: 9
 * Explanation: ((2 + 1) * 3) = 9
 *
 * Input: ["10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"]
 * Output: 22
 * Explanation:
 *   ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
 * = ((10 * (6 / (12 * -11))) + 17) + 5
 * = ((10 * (6 / -132)) + 17) + 5
 * = ((10 * 0) + 17) + 5
 * = (0 + 17) + 5
 * = 17 + 5
 * = 22
 * */

public class _150_Evaluate_Reverse_Polish_Notation {
    public int evalRPN(String[] tokens) {
        Set<String> set = new HashSet<>();
        set.add("/");
        set.add("+");
        set.add("-");
        set.add("*");
        Stack<Integer> stack = new Stack<>();
        for(String str:tokens){
            if(!set.contains(str)) stack.push(Integer.parseInt(str));
            else {
                // operator
                int second = stack.pop();
                int first = stack.pop();
                int res = 0;
                if(str.equals("/")) res = first/second;
                else if(str.equals("+")) res = first+second;
                else if(str.equals("-")) res = first - second;
                else res = first * second;
                stack.push(res);
            }
        }
        return stack.pop();
    }
}
