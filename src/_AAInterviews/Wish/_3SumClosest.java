package _AAInterviews.Wish;

import java.util.*;
/**
 *  Given an array nums of n integers and an integer target, find three integers in nums such that the sum is
 *  closest to target. Return the sum of the three integers. You may assume that each input would have exactly
 *  one solution.
 * */
public class _3SumClosest {
    public int threeSumClosest(int[] nums, int target) {
        int closest = nums[0]+nums[1]+nums[2];
        int first = 0;
        Arrays.sort(nums);


        while (first<nums.length-2) {
            if(nums[first]>=0 && nums[first]>=target) {
                return Math.abs(nums[first]+nums[first+1]+nums[first+2]-target)<Math.abs(target - closest)?nums[first]+nums[first+1]+nums[first+2]:closest;
            }
            int sec = first+1;
            int third = nums.length-1;
            while (sec<third) {
                int cur = nums[first]+nums[sec]+nums[third];
                if(Math.abs(closest-target)>Math.abs(cur-target)) closest = cur;
                if(cur<target) sec++;
                else if(cur>target) third--;
                else return target;
            }
            first++;
        }
        return closest;
    }
}
