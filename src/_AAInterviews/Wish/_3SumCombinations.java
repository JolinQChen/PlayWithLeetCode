package _AAInterviews.Wish;
import java.util.*;
/**
 * Given an array nums of n integers, are there elements a, b, c in nums such that a + b + c = 0?
 * Find [all unique triplets] in the array which gives the sum of zero.
 *
 * Notice that the solution set must not contain duplicate triplets.
 *
 * Input: nums = [-1,0,1,2,-1,-4]
 * Output: [[-1,-1,2],[-1,0,1]]
 * */
public class _3SumCombinations {
    public List<List<Integer>> threeSum(int[] nums) {
        int len = nums.length;
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        if(len<3 || nums[0]>0 || nums[len-1]<0) return res;

        int first = 0;
        while (nums[first]<=0 && first<len-2) {
            if(first>0 && nums[first]==nums[first-1]) {
                first++;
                continue;
            }
            // 双指针
            int sec = first+1;
            int third = len-1;
            while(sec<third) {
                if(sec!=first+1 && nums[sec-1] == nums[sec]){
                    sec++;
                    continue;
                }
                if(third!=len-1 && nums[third]==nums[third+1]) {
                    third--;
                    continue;
                }
                int cur = nums[first]+nums[sec] +nums[third];
                if(cur==0) {
                    List<Integer> tmp = new ArrayList<>();
                    tmp.add(nums[first]);
                    tmp.add(nums[sec]);
                    tmp.add(nums[third]);
                    res.add(tmp);
                    sec++;
                    third--;
                }
                else if(cur<0) sec++;
                else third--;
            }
            first++;
        }


        return res;
    }
}
