package _AAInterviews.Amazon;

import com.sun.xml.internal.ws.policy.EffectiveAlternativeSelector;

import java.util.*;
public class LongestHappyString {
    /** 暴力法 */
    private static String res;

    private static boolean isValid(int a, int b, int c, String cur) {
        if (a + b + c == 0) return false;
        if (cur.length() < 2) return true;
        if (a == 0 && b == 0 && cur.substring(cur.length() - 2).equals("cc")) return false;
        if (a == 0 && c == 0 && cur.substring(cur.length() - 2).equals("bb")) return false;
        if (b == 0 && c == 0 && cur.substring(cur.length() - 2).equals("aa")) return false;
        return true;
    }

    private static void dfs(int a, int b, int c, String cur) {
//        System.out.println("cur: "+cur);
//        if(cur.length()>=2) System.out.println("后两位："+cur.substring(cur.length() - 2));
//        System.out.println(isValid(a,b,c,cur) + " a: "+a+" b: "+b+" c: "+c);
        if (!isValid(a, b, c, cur)) {
//            System.out.println("not valid");
            if (res.length() < cur.length()) res = cur;
            return;
        }
        if (a > 0 && (cur.length() < 2 || !cur.substring(cur.length() - 2).equals("aa"))) {
            dfs(a - 1, b, c, cur + "a");
        }
        if (b > 0 && (cur.length() < 2 || !cur.substring(cur.length() - 2).equals("bb")))
            dfs(a, b - 1, c, cur + "b");
        if (c > 0 && (cur.length() < 2 || !cur.substring(cur.length() - 2).equals("cc")))
            dfs(a, b, c-1, cur + "c");

    }

    public static String longestDiverseString_brute(int a, int b, int c) {
        res = "";
        dfs(a, b, c, "");
        return res;
    }

    private static boolean endPoint(int[] remain, StringBuilder cur) {
        int a=remain[0],b=remain[1],c=remain[2];
        if (a + b + c == 0) return false;
        if (cur.length() < 2) return true;
        if (a == 0 && b == 0 && cur.substring(cur.length() - 2).equals("cc")) return false;
        if (a == 0 && c == 0 && cur.substring(cur.length() - 2).equals("bb")) return false;
        if (b == 0 && c == 0 && cur.substring(cur.length() - 2).equals("aa")) return false;
        return true;
    }

    public static String longestDiverseString(int a, int b, int c) {
        int[] remain = new int[]{a,b,c};
        PriorityQueue<Character> pq = new PriorityQueue<>((c1,c2)->(-remain[(int)(c1-'a')]+remain[(int)(c2-'a')]));
        if(a>0) pq.add('a');
        if(b>0) pq.add('b');
        if(c>0) pq.add('c');
        StringBuilder res = new StringBuilder();
        while (endPoint(remain,res)) {
            char tmp = pq.poll();
            if(res.length()<2 || !res.substring(res.length()-2).equals(tmp +String.valueOf(tmp))) {
                res.append(tmp);
                remain[(int)(tmp-'a')]--;
                if(remain[(int)(tmp-'a')]>0) pq.add(tmp);
            }
            else {
                char tmp2 = pq.poll();
//                System.out.println(tmp2);
                res.append(tmp2);
                remain[(int)(tmp2-'a')]--;
                if(remain[(int)(tmp2-'a')]>0) pq.add(tmp2);
                pq.add(tmp);
            }


        }

        return res.toString();
    }


    public static void main(String[] args) {
        String res = longestDiverseString(4,42,7);
        System.out.println(res);

    }
}
