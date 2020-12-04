package _AAInterviews.Amazon;

import java.util.*;
public class ReorderDataInLogFiles {
    public String[] reorderLogFiles(String[] logs) {
        Arrays.sort(logs, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                String[] s1 = o1.split(" ", 2);
                String[] s2 = o2.split(" ", 2);
                if(Character.isDigit(s1[1].charAt(0)) && Character.isDigit(s2[1].charAt(0))) {
                    return 0;
                }
                else if(Character.isDigit(s1[1].charAt(0))) return 1;
                else if(Character.isDigit(s2[1].charAt(0))) return -1;
                else {
                    return s1[1].compareTo(s2[1]) == 0 ? s1[0].compareTo(s2[0]) : s1[1].compareTo(s2[0]);
                }
            }
        });
        return logs;
    }
}
