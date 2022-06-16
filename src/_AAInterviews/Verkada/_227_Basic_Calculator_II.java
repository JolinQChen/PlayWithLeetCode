package _AAInterviews.Verkada;
import java.util.*;
public class _227_Basic_Calculator_II {
    public int calculate(String s) {
        s = s.trim();
        // System.out.println("this is"+s+"haha");
        int len = s.length();
        int idx = 0;
        char[] store = s.toCharArray();
        Stack<Integer> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();
        int isNegative = 1;
        while(idx<len) {
            while(idx<len && store[idx]==' ') idx++;
            while(idx<len && Character.isDigit(store[idx])) {
                sb.append(store[idx]);
                idx++;
            }
            if(sb.length()>0) {
                // System.out.println(Integer.parseInt(sb.toString())*isNegative);
                stack.push(Integer.parseInt(sb.toString())*isNegative);
                sb = new StringBuilder();
                isNegative = 1;
            }
            while (idx<len && !isOperator(store[idx])) idx++;
            if(idx<len){
                if(store[idx]=='-'){
                    isNegative = -1;
                    idx++;
                }
                else if(store[idx]=='+') idx++;
                else {
                    // * or /
                    int next = 0;
                    int icon = store[idx];
                    while (idx<len && !Character.isDigit(store[idx])) idx++;
                    while(idx<len && Character.isDigit(store[idx])) {
                        sb.append(store[idx]);
                        idx++;
                    }
                    if(sb.length()>0) {
                        next = Integer.parseInt(sb.toString());

                        sb = new StringBuilder();
                    }
                    int pre = stack.pop();
                    int res = icon=='*'?pre*next : pre/next;
                    stack.push(res);
                }
            }
        }
        int ans = 0;
        for(int i:stack) {
            ans += i;
        }
        return ans;
    }

    private boolean isOperator(char c) {
        return c=='+'||c=='-'||c=='*'||c=='/';
    }
}
