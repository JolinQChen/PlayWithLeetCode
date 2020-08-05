package _AAInterviews.MS;
import java.util.*;
/** Could you do it in-place without allocating extra space?
 *
 * A word is defined as a sequence of non-space characters.
 * The input string does not contain leading or trailing spaces.
 * The words are always separated by a single space.
 * */
public class ReverseWordsInAStringII {
    // Reverse all the string then reverse each word
    private char[] s;
    public void reverseWords(char[] s) {
        this.s = s;
        reverseHelper(0,s.length-1);
        int start = 0, end = 0;
        while (end<s.length) {
            if(end==s.length-1 || s[end+1]==' ') {
                reverseHelper(start,end);
                start = end+2;
                end = end+2;
            }
            else
                end++;

        }


    }

    private void reverseHelper(int start, int end) {
        int l=start,r=end;
        while(l < r) {
            swap(l++, r--);
        }
    }
    private void swap(int a, int b) {
        char tmp = s[a];
        s[a] = s[b];
        s[b] = tmp;
    }
    public static void main(String[] args) {
        String str = "the sky is so blue";
        char[] c = str.toCharArray();
        ReverseWordsInAStringII test = new ReverseWordsInAStringII();
        test.reverseWords(c);
        for(char cc:c)
            System.out.print(cc + " ");
    }
}
