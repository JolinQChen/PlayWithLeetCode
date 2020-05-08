import java.util.*;
public class Test{
    public static String arrange(String sentence) {
        // Write your code here
        String[] breaks = sentence.split(" ");
        int len = breaks.length;
        if(len == 0 || len == 1) return sentence;

        // change the last word
        breaks[len-1] = breaks[len-1].substring(0,len-1);
        Arrays.sort(breaks, new Comparator<T>() {
            public int compare(String s1, String s2){
                if(s1.length()==s2.length()) return -1;
                return s1.length() - s2.length();
            }
        });

    }
    public static void main(String args[]) {
        try {
            int x = 0;
            for (x=1; x<4; x++);
            System.out.println(x);
        } catch(Exception e) {}
        finally {
            System.out.println("Error");
        }
    }
}