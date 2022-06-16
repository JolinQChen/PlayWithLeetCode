package _AAInterviews.Verkada;

import org.junit.Test;

import java.util.*;

public class _772_Basic_Calculator_III {
    public int calculate(String s) {

        int len = s.length();
        List<Integer> list = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();
        for(int i=0; i<len; i++) {
            if(s.charAt(i)=='(') stack.push(i);
            else if(s.charAt(i)==')') {
                int pos = stack.pop();
                if(stack.isEmpty()) {
                    list.add(pos);
                    list.add(i);
                }
            }
        }
        if(list.size()==0) return calculateSingle(s);
        StringBuilder sb = new StringBuilder();
        sb.append(s.substring(0,list.get(0)));
        for(int i=0;i<list.size(); i+=2) {
            int j = i+1;
            int res = calculate(s.substring(list.get(i)+1,list.get(j)));
            sb.append(String.valueOf(res));
            if(j<list.size()-1) sb.append(s.substring(list.get(j)+1,list.get(j+1)));
        }
        sb.append(s.substring(list.get(list.size()-1)+1));
        System.out.println(sb.toString());
        return calculateSingle(sb.toString());



    }

    public int calculateSingle(String s) {
        int len = s.length();
        int idx = 0;
        char[] store = s.toCharArray();
        Stack<Integer> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();
        int isNegative = 1;
        while(idx<len) {
            while(idx<len && Character.isDigit(store[idx])) {
                sb.append(store[idx]);
                idx++;
            }
            if(sb.length()>0) {
                stack.push(Integer.parseInt(sb.toString())*isNegative);
                sb = new StringBuilder();
                isNegative = 1;
            }
            if(idx<len){
                if(store[idx]=='-'){
                    isNegative *= -1;
                    idx++;
                }
                else if(store[idx]=='+') idx++;
                else {
                    // * or /
                    int next = 0;
                    int icon = store[idx];
//                    while (idx<len && !Character.isDigit(store[idx])) idx++;
                    int nega = 1;
                    idx++;
                    if(idx<len && store[idx]=='-') {
                        idx++;
                        nega *= -1;
                    }
                    while(idx<len && Character.isDigit(store[idx])) {
                        sb.append(store[idx]);
                        idx++;
                    }
                    if(sb.length()>0) {
                        next = nega * Integer.parseInt(sb.toString());

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

    @Test
    public void test(){
        String s = "((((8+3)*(4-10))-2)+((5+(10/2))+((9+5)+(2+2))))";
        System.out.println(calculate(s));
    }
}
