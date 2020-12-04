package _AAInterviews.Amazon;

import java.util.*;
// 理解：每个part中必须完全包含所有字母的始末位置
/**
 * Input: S = "ababcbacadefegdehijhklij"
 * Output: [9,7,8]
 * Explanation:
 * The partition is "ababcbaca", "defegde", "hijhklij".
 * This is a partition so that each letter appears in at most one part.
 * A partition like "ababcbacadefegde", "hijhklij" is incorrect, because it splits S into less parts.
 * */
public class PartitionLabels {

    public static List<Integer> partitionLabels(String S) {
        List<Integer> res = new ArrayList<>();
        int len = S.length();
        Map<Character, int[]> map = new HashMap<>();
        for(int i=0; i<len; i++) {
            char c = S.charAt(i);
            if(!map.containsKey(c)) {
                map.put(c, new int[]{i,i});
            }
            else {
                map.get(c)[1] = i;
            }
        }
        int partStart = 0;
        int partEnd = map.get(S.charAt(partStart))[1];
        int prev = partStart;
        while (partStart<len) {
            if(partStart == partEnd) {
                res.add(partEnd - prev + 1);
                partStart = partEnd+1;
                prev = partStart;
                if(partStart<len) partEnd = map.get(S.charAt(partStart))[1];
            }
            else {
                partStart++;
                partEnd = Math.max(map.get(S.charAt(partStart))[1],partEnd);
            }
        }
        return res;
    }
    public static void main(String[] args) {
        String str = "ababcbacadefegdehijhklij";
        List<Integer> list = partitionLabels(str);
        for(int i:list) System.out.println(i);
    }
}
