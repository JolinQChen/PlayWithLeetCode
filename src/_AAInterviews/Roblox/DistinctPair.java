package _AAInterviews.Roblox;
import java.util.*;
public class DistinctPair {
    public static int countDistinctPair(int[] nums, int target) {
        Set<Integer> set = new HashSet<>();
        Set<Integer> seen = new HashSet<>();
        int count=0;
        for(int num:nums) {
            if(set.contains(target-num) && !seen.contains(num)) {
                count++;
                seen.add(num);
                seen.add(target-num);
            }
            else if(!set.contains(num)) set.add(num);
        }
        return count;
    }
}
