import java.util.*;
/**
 * Given an array of characters, compress it in-place.
 *
 * The length after compression must always be smaller than or equal to the original array.
 *
 * Every element of the array should be a character (not int) of length 1.
 *
 * After you are done modifying the input array in-place, return the new length of the array.
 *
 *
 * Follow up:
 * Could you solve it using only O(1) extra space?
 *
 * Input:
 * ["a","b","b","b","b","b","b","b","b","b","b","b","b"]
 *
 * Output:
 * Return 4, and the first 4 characters of the input array should be: ["a","b","1","2"].
 *
 * Explanation:
 * Since the character "a" does not repeat, it is not compressed. "bbbbbbbbbbbb" is replaced by "b12".
 * Notice each digit has it's own entry in the array.
 * */

public class _443_String_Compression {
    public static int compress(char[] chars) {
        int len = chars.length;
        if(len<=1) return len;
        int pr = 0;
        int p = 0;
        int count = 0;
        int lastCount = 0;

        while(p<len){
            while(p<len && chars[p]==chars[pr]) {
                p++;
                count++;
            }
            System.out.println("p: "+p);
            if(count == 1) {
                pr++;
                if(p<len) chars[pr] = chars[p];

            }
            else{
                String tmp = String.valueOf(count);
                int tmpl = tmp.length();
//                System.out.println("tmpl: "+tmpl);
                for(int i=0; i<tmpl; i++) chars[++pr]=tmp.charAt(i);
                if(p<len) chars[++pr] = chars[p];
            }
            lastCount = count;
            count=0;
        }
        //return Math.min(pr+1, len);
        if(lastCount == 1) return pr;
        return pr+1;
    }
    public static void main(String[] args){
        //["a","b","c","d","e","f","g","g","g","g","g","g","g","g","g","g","g","g","a","b","c"]
        String str = "abcdefggggggggggggabc";
        char[] test = str.toCharArray();
        int res = compress(test);
        //System.out.println(res);
    }

}
