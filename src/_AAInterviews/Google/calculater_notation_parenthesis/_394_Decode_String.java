package _AAInterviews.Google.calculater_notation_parenthesis;
import java.util.*;
public class _394_Decode_String {
    public String decodeString(String s) {
        Stack<Integer> timeStack = new Stack<>();
        Stack<String> sbStack = new Stack<>();
        char[] cc = s.toCharArray();
        int curTimes = 0;
        StringBuilder sb = new StringBuilder();
        for(char c:cc ) {
            if (Character.isDigit(c)) {
                curTimes = curTimes * 10 + (c - '0');
            }
            else if (c == '[') {
                timeStack.push(curTimes);
                curTimes = 0;
                sbStack.push(sb.toString());
                sb = new StringBuilder();

            } else if (c == ']') {
                int t = timeStack.pop();
                String pre = "";
                if(!sbStack.isEmpty()) {
                    pre = sbStack.pop();
                }
                StringBuilder tmpSb = new StringBuilder();
                for(int i=0; i<t; i++) {
                    tmpSb.append(sb);
                }
                sb = new StringBuilder(pre+tmpSb);
            } else {
                // c is a normal string character
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
