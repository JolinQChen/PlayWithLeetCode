package _AAInterviews.VMware;
import com.sun.xml.internal.fastinfoset.util.CharArrayArray;

import java.util.*;

// Input: s = "13[a2[c]]"
// Output: "accaccacc"

public class _394_DecodeString {
    public String decodeString(String s) {
        Stack<Integer> repeatStack = new Stack<>();
        Stack<String> preserveStack = new Stack<>();
        char[] cc = s.toCharArray();
        StringBuilder sb = new StringBuilder();
        int multi = 0;
        int len = s.length();
        for(char c:cc) {
            if(c=='[') {
                repeatStack.push(multi);
                multi = 0;
                preserveStack.push(sb.toString());
                sb = new StringBuilder();
            }
            else if(c==']') {
                int repeat = repeatStack.pop();
                String curr = preserveStack.pop();
                StringBuilder sbb = new StringBuilder();
                for(int i=0; i<repeat; i++) sbb.append(sb);
                sb = new StringBuilder(curr+sbb); //1[ab2[c]ab]
            }
            else if(Character.isDigit(c)) multi = multi*10+c-'0';
            else sb.append(c);
        }
        return sb.toString();
    }
}
