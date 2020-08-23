package _AAInterviews.MS;
import java.util.*;
public class CircularArrayLoop {
    public static boolean circularArrayLoop(int[] nums) {
        if(nums.length<=1) return false;
        boolean[] valid = new boolean[nums.length];
        Arrays.fill(valid,true);
        Map<Integer, Integer> map = new HashMap<>();
        List<Integer> heads = new ArrayList<>();
        for(int i = 0; i<nums.length; i++){

            int idx = i;
            System.out.println("i: "+i);

            for(boolean tmp:valid) System.out.print(tmp+" ");

            while (!map.containsKey(idx) && valid[idx]) {
                int nextIdx = idx + nums[idx];
                if(nextIdx>=0) nextIdx = (nextIdx)%nums.length;

                else nextIdx = nums.length - (-nextIdx % nums.length);
                System.out.println("i: "+i+ ", nextIdx: " + nextIdx);
                map.put(idx, nextIdx);
                idx = nextIdx;
            }
            if(!valid[idx]) {
                int ii = i;
                while (valid[ii]) {
                    valid[ii] = false;
                    ii = map.get(ii);
                }
                continue;
            }
            int init = idx; // find head
            idx = map.get(idx);
            if(idx == init) {
                valid[idx] = false;
                idx = i;
                while (idx!=init){
                    valid[idx] = false;
                    idx = map.get(idx);
                }
                continue;
            }
            boolean flag = true;
            while (idx!=init) {
                if(nums[init]*nums[idx]<0) {
                    flag = false;
                    break;
                }
                idx = map.get(idx);
            }
            if(flag) return true;
            else {
                //System.out.println("init: "+init);
                idx = i;
                valid[idx] = false;
                idx = map.get(idx);
                int count=0;
                if(init == i) count=1;
                while (count<2) {
                    if(idx==init) count++;
                    valid[idx] = false;
                    idx = map.get(idx);
                }
            }
        }
        return false;


    }
    public static void main(String[] args) {
        int[] nums = {2,2,2,2,2,4,7};
        boolean res = circularArrayLoop(nums);
        System.out.println(res);
    }
}
