package _AAInterviews.VMware;
import java.lang.reflect.GenericDeclaration;
import java.util.*;

public class DecodeEncode {


    public static List<String> decode(String str) {
        // within the range 10 - 126
        String reversedCode = reverse(encode(str));
        System.out.println(reversedCode);
        List<String> res = new ArrayList<>();
        backTrack(reversedCode,"", 0, reversedCode.length(), res);
        return res;
    }
    public static void backTrack(String code, String curr, int pos, int len, List<String> list) {
        if(pos >= len) {
            System.out.println(curr);
            list.add(curr);
        }
        else {
            if(pos+1==len) return;
            int next1 = Integer.parseInt(code.substring(pos,pos+2));
            backTrack(code,curr+ (char) next1,pos+2, len, list);
            if(pos+2<len && Integer.parseInt(code.substring(pos,pos+3))<=126) {
                int next2 = Integer.parseInt(code.substring(pos,pos+3));
                backTrack(code,curr+(char) next2,pos+3, len, list);
            }
        }
    }


    public static String reverse(String str) {
        char[] c = str.toCharArray();
        int len = c.length;
        StringBuilder sb = new StringBuilder();
        for(int i=len-1; i>=0; i--) sb.append(c[i]);
        return sb.toString();
    }

    public static String encode(String str) {
        String res = "";
        int len = str.length();
        for(int i=0; i<len; i++) {
            int curr =(int) str.charAt(i);
            res += String.valueOf(curr);
        }
        return res;
    }

    public static void main(String[] args) {
//        String encode = encode("Go VMWare");
//        System.out.println(encode("Go VMWare"));
//        System.out.println(reverse(encode));
        List<String> r = decode("Go VMWare");
        for(String s:r) System.out.println(s);
//        System.out.println("t" + (char) 10);
    }
}
