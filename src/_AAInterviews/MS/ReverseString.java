package _AAInterviews.MS;
import java.util.*;
public class ReverseString {
    public void reverseString(char[] s){
        int l=0, r = s.length-1;
        while(l<r)
            swap(s,l++,r--);

    }

    private void swap(char[] s, int a, int b) {
        char tmp = s[a];
        s[a] = s[b];
        s[b] = tmp;
    }
}
