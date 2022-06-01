package _AAInterviews.Google;
import org.junit.Test;

import java.util.*;


public class _68_Text_Justification {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> res = new ArrayList<>();
        int p = 0;
        while (p<words.length) {
            List<String> strList = new ArrayList<>();
            int countLen = 0;
            countLen = words[p].length();
            strList.add(words[p]);
            while(p<words.length-1) {
                if(countLen + 1 + words[p+1].length() > maxWidth) break;
                p++;
                countLen = countLen + 1+ words[p].length();
                strList.add(words[p]);
            }
            if(p<words.length-1) {
                String str = formString(strList, maxWidth - countLen + (strList.size()-1));
                res.add(str);
            } else {
                // last str
                StringBuilder sb = new StringBuilder();
                for(int i=0; i<strList.size(); i++) {
                    sb.append(strList.get(i));
                    if(i<strList.size()-1) sb.append(" ");
                }
                for(int i=0; i<maxWidth-countLen; i++) sb.append(" ");
                res.add(sb.toString());
            }
            p++;
        }


        return res;
    }

    private String formString(List<String> strList, int spaces) {
        // use strings in <strList> to form a string of <len> that has <spaces> of spaces

        StringBuilder sb = new StringBuilder();
        // only one word
        if(strList.size() == 1) {
            sb.append(strList.get(0));
            for(int i=0; i<spaces; i++) sb.append(" ");
            return sb.toString();
        }

        // exactly x-1 spaces
        if(spaces == strList.size()-1) {
            for (String str:strList) {
                sb.append(str);
                sb.append(" ");
            }
            sb.deleteCharAt(sb.length()-1);
            return sb.toString();
        }

        // have extra space
        int minSpaceNumberEachSlot = spaces / (strList.size()-1);
        int extraSpace = spaces % (strList.size()-1);
        for(int i=0; i<strList.size(); i++) {
            sb.append(strList.get(i));
            if(i<extraSpace) {
                for (int j=0; j<minSpaceNumberEachSlot+1; j++) sb.append(" ");
            } else if(i<strList.size()-1){
                for (int j=0; j<minSpaceNumberEachSlot; j++) sb.append(" ");
            }
        }
        return sb.toString();
    }

    @Test
    public void test() {
//        String[] words = new String[]{"Science","is","what","we","understand","well","aaa","enough","to","explain","to","a","computer.","Art","is","everything","else","we","do"};
//        List<String> res = fullJustify(words, 20);
//        System.out.println(res);
        String[] words = new String[]{"enough","to","explain","to"};
        List<String> list = Arrays.asList(words);
        String res = formString(list, 3);
        System.out.println(res+".");

    }
}
