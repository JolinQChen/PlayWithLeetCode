package _AAInterviews.Amazon;

import java.util.HashMap;
import java.util.Map;
public class TruckPackage_FindPairWithGivenSUm {
    private static int[] solution(int[] nums, int target) {
        int[] res = new int[2];
        target -= 30;
        Map<Integer, Integer> map = new HashMap<>();
        int large = Integer.MIN_VALUE;
        for(int i=0; i<nums.length; i++) {
            if(map.containsKey(target - nums[i])) {
                if(large < map.get(target - nums[i]) || nums[i]>large) {
                    res[0] = map.get(target - nums[i]);
                    res[1] = i;
                    large = Math.max(nums[i],target - nums[i]);
                }
            }
            map.put(nums[i],i);
        }
        return res;
    }
}
