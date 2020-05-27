package Citrix;
import java.util.*;
/**
 * return the number of distinct substring of string s
 * */

public class SubstringCalculator {
    private static Set<String> set;
    private static int count;
    public static int calculator(String s){
        set = new HashSet<>();
        count = 0;
        reduce(s);
        return count;
    }
    private static void reduce(String s){
        if(s.length()==0) return;
        if(!set.contains(s)) {
            count++;
            set.add(s);
        }
        reduce(s.substring(0,s.length()-1));
        reduce(s.substring(1));
    }
    public static void main(String[] args){
        System.out.println(calculator("aaa"));
    }

}
