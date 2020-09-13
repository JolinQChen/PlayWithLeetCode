package _AAInterviews.Citrix.FullTime;

import java.util.Stack;

public class DecodeString {
    public static String decode(String input) {
        Stack<String> stack = new Stack<>();
        int idx = 0;
        int len = input.length();
        StringBuilder res = new StringBuilder();
        //String res = "";
        while (idx<len){
            char c = input.charAt(idx);
            if(c=='(' || c==')') {
                if(res.length()>0) {
                    stack.push(res.toString());
                    res = new StringBuilder();
                }
                idx++;
            }
            else if(c=='{') {
                StringBuilder repeat = new StringBuilder();
                idx++;
                while (input.charAt(idx)!='}') {
                    repeat.append(input.charAt(idx));
                    idx++;
                }
                idx++;
//                System.out.println(repeat.toString());
                String last = stack.pop();
                StringBuilder tmp = new StringBuilder();
                int rep = Integer.parseInt(repeat.toString());
                for(int j=0; j<rep; j++) tmp.append(last);
                if(stack.isEmpty()) res = tmp;
                else {
                    String pre = stack.pop();
                    res = new StringBuilder(pre+tmp.toString());
                }

            }
            else {
                res.append(c);
                idx++;
            }


        }
        return res.toString();
    }

    public static void main(String[] args) {
        String test = "(abc(cd){3}xyz){2}";
        System.out.println(decode(test));
    }

    // (abc(cd){3}xyz){2}
}
