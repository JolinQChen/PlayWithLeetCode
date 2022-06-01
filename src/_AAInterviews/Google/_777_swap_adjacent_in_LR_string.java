package _AAInterviews.Google;

import java.util.HashSet;
import java.util.Set;

public class _777_swap_adjacent_in_LR_string {
    // 本质是L左移，R右移
    public boolean canTransform(String start, String end) {
        String startTrim = trimString(start);
        String endTrim = trimString(end);
        if(!startTrim.equals(endTrim)) return false;
        Set<Integer> matchedSet = new HashSet<>();
        int len = start.length();
        int pointer_l = 0;
        int pointer_r = 0;
        for(int i=0; i<len; i++) {
            if(end.charAt(i) == 'L') {
                // comes from right of current index
                int idx = i;
                while(idx<len && start.charAt(idx)!='R') {
                    if(start.charAt(idx) == 'L') {
                        if(!matchedSet.contains(idx)) {
                            matchedSet.add(idx);
                            break;
                        }
                    }
                    idx++;
                }
                if(idx==len || start.charAt(idx)!='L') return false;
            }
            else if(end.charAt(i) == 'R') {
                // comes from left of current index
                int idx = i;
                while(idx>=0 && start.charAt(idx)!='L') {
                    if(start.charAt(idx)=='R') {
                        if(!matchedSet.contains(idx)) {
                            matchedSet.add(idx);
                            break;
                        }
                    }
                    idx--;
                }
                if(idx<0 || start.charAt(idx)!='R') return false;
            }
        }
        return true;

    }

    private String trimString(String str) {
        StringBuilder sb = new StringBuilder();
        char[] cc = str.toCharArray();
        int len = str.length();
        for(char c:cc) {
            if(c == 'L' || c == 'R') sb.append(c);
        }
        return sb.toString();
    }
}
