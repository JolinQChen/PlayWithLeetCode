package _All;
import java.util.*;
// 就是记录第一次和最后一次出现的位置就行
public class _697_Degree_of_an_Array {
    public int findShortestSubArray(int[] nums) {
        Map<Integer, Integer> leftMap = new HashMap<>();
        Map<Integer, Integer> rightMap = new HashMap<>();
        Map<Integer, Integer> countMap = new HashMap<>();

        int degree = 0;

        for (int i=0; i<nums.length; i++) {
            int num = nums[i];
            if(!leftMap.containsKey(num)) leftMap.put(num,i);
            rightMap.put(num,i);
            countMap.put(num,countMap.getOrDefault(num,0)+1);
            degree = Math.max(countMap.get(num),degree);
        }
        int res = nums.length;
        for(int num:countMap.keySet()) {
            if(countMap.get(num) == degree) {
                res = Math.min(rightMap.get(num)-leftMap.get(num)+1,res);
            }
        }
        return res;
    }
}
