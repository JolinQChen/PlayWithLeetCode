package _All;

import java.util.*;
// 运算问题
/**
 * Implement a basic calculator to evaluate a simple expression string.
 *
 * The expression string contains only non-negative integers, +, -, *, / operators
 * and empty spaces . The integer division should truncate toward zero.
 *
 * Input: " 3+5 / 2 "
 * Output: 5
 * */

public class _227_Basic_Calculator_II {

    public int calculate(String s) {
        int res = 0;
        Stack<Integer> stack = new Stack<>();
        char[] store = s.toCharArray();
        int index = 0;
        int len = store.length;
        //List<Character> list = new ArrayList<>();
        int isMinus = 1;
        StringBuilder sb = new StringBuilder();
        while(index<len){
            //得到一个完整的数
            while(index<len && Character.isDigit(store[index])) {
                sb.append(store[index]);
                index++;
            }
            if(sb.length()!=0) {
                stack.push(Integer.parseInt(sb.toString()) * isMinus);
                if(isMinus == -1) isMinus = 1;
            }
            sb = new StringBuilder();

            if(index<len && (store[index]=='*'||store[index]=='/')) {
                char icon = store[index];
                int pre = stack.pop();
                int next = 0;
                while (index<len && !Character.isDigit(store[index])) index++;
                while(index<len && Character.isDigit(store[index])) {
                    sb.append(store[index]);
                    index++;
                }
                if(sb.length()!=0) next = Integer.parseInt(sb.toString());
                sb = new StringBuilder();

                int tmp = icon=='*'?pre*next:pre/next;
                stack.push(tmp);
            }
            else if(index<len && store[index]=='-'){
                isMinus = -1;
                index++;
            }
            else index++;
        }
        for(int i:stack){
            res += i;
        }
        return res;

    }
}
