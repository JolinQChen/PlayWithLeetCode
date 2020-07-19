package _All;

import java.util.*;
public class _1248_Count_Number_of_Nice_Subarrays {
    public int numberOfSubarrays(int[] nums, int k) {
        int len = nums.length;
        List<Integer> oddIdxList = new ArrayList<>();
        oddIdxList.add(-1);
        for(int i=0; i<len; i++) {
            if(nums[i]%2==1) oddIdxList.add(i);
        }
        oddIdxList.add(len);
        int l = 1;
        int r = k;
        int oddSize = oddIdxList.size();
        if(oddSize-2<k) return 0;
        int res = 0;
        while(r<oddSize-1) {
            res += (oddIdxList.get(l) - oddIdxList.get(l-1)) * (oddIdxList.get(r+1) - oddIdxList.get(r));
            l++;
            r++;
        }
        return res;
    }
}
