package _All;
import java.util.*;
public class _18_4Sum {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        if(nums.length<4) return res;
        for(int p1=0; p1<=nums.length-4; p1++) {
            if(target>=0 && target<nums[p1]) break;
            if(p1>0 && nums[p1]==nums[p1-1]) continue;
            for(int p2 = p1+1; p2<=nums.length-3; p2++) {
                int remain2 = target-nums[p1];
                if(remain2>=0 && remain2<nums[p2]) break;
                if(p2>p1+1 && nums[p2]==nums[p2-1]) continue;
                for(int p3 = p2+1; p3<=nums.length-2; p3++) {
                    int remain3 = remain2 - nums[p2];
                    if(remain3>=0 && remain3<nums[p3]) break;
                    if(p3>p2+1 && nums[p3]==nums[p3-1])continue;
                    for(int p4=p3+1; p4<nums.length; p4++) {
                        int remain4 = remain3-nums[p3];
                        if(remain4>=0 && remain4<nums[p4]) break;
                        if(p4>p3+1 && nums[p4]==nums[p4-1]) continue;
                        if(remain4 == nums[p4]) {
                            List<Integer> list = new ArrayList<>();
                            list.add(nums[p1]);
                            list.add(nums[p2]);
                            list.add(nums[p3]);
                            list.add(nums[p4]);
                            res.add(list);
                        }
                    }
                }
            }
        }
        return res;
    }
}
