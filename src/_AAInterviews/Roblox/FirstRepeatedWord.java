package _AAInterviews.Roblox;

import java.util.HashSet;

public class FirstRepeatedWord {
    public static String findFirstRepeat(String text) {
        HashSet<String> set = new HashSet<>();
        StringBuilder sb = new StringBuilder();
        char[] input = text.toCharArray();
        for (char c:input) {
            if(Character.isLetter(c)) sb.append(c);
            else {
                if(sb.length()!=0) {
                    String tmp = sb.toString();
                    if(set.contains(tmp)) return tmp;
                    set.add(tmp);
                    sb = new StringBuilder();
                }

            }
        }
        return "";
    }
    public static void main(String[] args) {
        String text = "Hello World !!!  kkk/// Hello--";
        String res = findFirstRepeat(text);
        System.out.println(res);
    }
}
