package _AAInterviews.VMware;
import java.util.*;
public class _937_ReorderDataInLogFiles {
    public String[] reorderLogFiles(String[] logs) {
        // letter-logs -> digit-log; sort letter logs, digit logs in original order
        Arrays.sort(logs, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                String[] str1 = o1.split(" ",2);
                String[] str2 = o2.split(" ",2);
                if(Character.isDigit(str1[1].charAt(0))) {
                    if(Character.isDigit(str2[1].charAt(0))) return 0;
                    else return 1;
                }
                else {
                    if(Character.isDigit(str2[1].charAt(0))) return -1;
                    else {
                        return str1[1].compareTo(str2[1])==0 ? str1[0].compareTo(str2[0]) : str1[1].compareTo(str2[1]);
                    }
                }
            }
        });
        return logs;
    }
}
