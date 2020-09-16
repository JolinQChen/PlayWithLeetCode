package _AAInterviews.Roblox;
import java.util.*;
public class MaxSubstring {
    //暴力
    public String lastSubstring1(String str) {
        char[] c = str.toCharArray();
        char max = 'a';
        List<Integer> list = new ArrayList<>();
        for(int i=0; i<c.length; i++){
            if(max<c[i]) {
                max = c[i];
                list = new ArrayList<>();
                list.add(i);
            }
            else if(max==c[i]) list.add(i);
        }
        String res = "";
        for(int i:list) {
            if(res.compareTo(str.substring(i))<0) res = str.substring(i);
        }
        return res;

    }
}
