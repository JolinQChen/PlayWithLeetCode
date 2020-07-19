package _All;

import java.util.*;
/*
* Input: "a good   example!"
* Output: "example! good a"
*
* Explanation: You need to reduce multiple spaces between
* two words to a single space in the reversed string.
* */
public class _151_Reverse_Words_in_a_String {
    public String reverseWords(String s) {
        //StringBuilder sb = new StringBuilder();
        List<String> list = new ArrayList<>();
        int start = 0;

        int len = s.length();
        while(start<len && s.charAt(start)==' ')start++;
        if(start==len) return "";
        int end = start;
        while (end<len){
            while(end<len && s.charAt(end)!=' ') end++;
            list.add(s.substring(start,end));
            start = end+1;
            while(start<len && s.charAt(start)==' ')start++;
            end = start;
        }
        StringBuilder sb = new StringBuilder();
        int lenList = list.size();
        for(int i=lenList-1; i>0; i--){
            sb.append(list.get(i));
            sb.append(" ");
        }
        sb.append(list.get(0));
        return sb.toString();
    }

    public static void main(String[] args){
        String test = "I am  Genius!";
        String[] breaks = test.split(" ");
        for(String s:breaks) {
            System.out.println(s);
            System.out.println(s.length());
        }
    }
}
