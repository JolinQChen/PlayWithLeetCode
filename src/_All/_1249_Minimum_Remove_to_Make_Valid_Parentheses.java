package _All;

import java.util.*;
/**
 * 给你一个由 '('、')' 和小写字母组成的字符串 s。
 *
 * 你需要从字符串中删除最少数目的 '(' 或者 ')' （可以删除任意位置的括号)，使得剩下的「括号字符串」有效。
 *
 * 请返回任意一个合法字符串。
 *
 * 有效「括号字符串」应当符合以下 任意一条 要求：
 *
 * 空字符串或只包含小写字母的字符串
 * 可以被写作 AB（A 连接 B）的字符串，其中 A 和 B 都是有效「括号字符串」
 * 可以被写作 (A) 的字符串，其中 A 是一个有效的「括号字符串」
 *
 * 输入：s = "a)b(c)d"
 * 输出："ab(c)d"
 *
 * 输入：s = "))(("
 * 输出：""
 * 解释：空字符串也是有效的
 * */

public class _1249_Minimum_Remove_to_Make_Valid_Parentheses {
    public String minRemoveToMakeValid(String s) {
        char[] arr = s.toCharArray();
        Set<Integer> delete = new HashSet<>();
        Stack<Integer> stack = new Stack<>(); // stack中存储的是"("的index
        for(int i=0; i<arr.length; i++){
            char cur = arr[i];
            if(cur=='(') stack.push(i);
            else if(cur == ')'){
                //若前面有一个'('，抵消掉
                if(!stack.isEmpty()) stack.pop();
                else {
                    //这个')'在的位置应该被消除掉
                    delete.add(i);
                }
            }
        }
        while(!stack.isEmpty()){
            delete.add(stack.pop());
        }
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<arr.length; i++){
            if(!delete.contains(i)) sb.append(arr[i]);
        }
        return sb.toString();

    }
}
