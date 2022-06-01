package _AAInterviews.Google;

import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class _471_Encode_String_with_Shortest_Length {
    public String encode(String s) {
        if(s.length()<=3) return s;
        // encode whatever
        StringBuilder sb = new StringBuilder();
        int l=0, r=0;
        int len = s.length();
        Map<Character, Set<Integer>> map = new HashMap<>(); // c对应在substr【l,r】 中的位置
        while (l<len && r<len) {
                if(!map.containsKey(s.charAt(r))) {
                    map.put(s.charAt(r), new HashSet<>());
                    map.get(s.charAt(r)).add(r);
                    r++;
                } else {
                    // check if forms a repeating substring
                    boolean found = false;
                    for(Integer i:map.get(s.charAt(r))) {
                        int curLen = r-i;
                        String findRepeatStr = s.substring(i,r);
                        int count = 0;
                        while(r+curLen<=len && s.substring(r,r+curLen).equals(findRepeatStr)) {
                            count++;
                            // found element, exhaust the elements
                            r+=curLen;
                        }
                        if(count>0) {
                            found = true;
                            count++;
                            sb.append(s.substring(l,i));
                            String encoded = String.valueOf(count)+"["+findRepeatStr+"]";
                            if(encoded.length()<=count*findRepeatStr.length()){
                                sb.append(encoded);
                            } else {
                                sb.append(s.substring(l,r));
                            }
                            break;
                        }
                    }
                    if(found) {
                        // formed a string, continue to next
                        l = r;
                        map = new HashMap<>();
                    } else {
                        map.get(s.charAt(r)).add(r);
                        r++;
                    }
                }
        }
        sb.append(s, l, len);
        return sb.toString();
    }

    @Test
    public void test(){
        String testStr = "aabcaabc";
        System.out.println(encode(testStr));
    }

}
