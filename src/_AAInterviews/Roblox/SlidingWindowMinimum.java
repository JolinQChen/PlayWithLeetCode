package _AAInterviews.Roblox;
// https://leetcode.com/problems/sliding-window-maximum/

import java.util.*;

public class SlidingWindowMinimum {
    // 法一：暴力法 见maxmin题目
    // 法二：Deque
    public static int[] maxSlidingWindowsDeque(int[] nums, int k) {
        ArrayDeque<Integer> deque = new ArrayDeque<>(); // idx
        int[] res = new int[nums.length-k+1];
        // initialize first window
        for(int i=0; i<k; i++) {
            while (!deque.isEmpty() && nums[deque.peekLast()]<=nums[i]) deque.removeLast();
            deque.addLast(i);
        }
        int idx = 0;
        res[idx++] = nums[deque.peekFirst()];
        for(int i=k; i<nums.length; i++) {
            // remove elements out of range
            while (!deque.isEmpty() && deque.peekFirst()<=i-k) deque.removeFirst();
            // add last to deque
            while (!deque.isEmpty() && nums[deque.peekLast()]<=nums[i]) deque.removeLast();
            deque.addLast(i);
            res[idx++] = nums[deque.peekFirst()];
        }
        return res;
    }
    public static void main(String[] args) {

    }
    // 法三：DP
}
