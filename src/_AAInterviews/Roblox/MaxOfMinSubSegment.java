package _AAInterviews.Roblox;

import java.util.Deque;
import java.util.LinkedList;

public class MaxOfMinSubSegment {
    //public static List<Integer> list = new LinkedList<>();
    public static int slidingWindow(int [] nums, int k){
        // find min
        int max = Integer.MIN_VALUE;
        Deque<Integer> deque = new LinkedList<>();

        //Step 1: handle first k elements in sliding window
        for (int i = 0; i <k ; i++) {
            //remove all the elements which are larger than the current elements
            while(!deque.isEmpty() && nums[deque.peekLast()]>=nums[i])
                deque.removeLast();
            //add new element at the end
            deque.addLast(i);
        }

        //Step 2: handle rest of the element, one at a time nums[k] to nums[n-1]
        for (int i = k; i <nums.length ; i++) {

            //before we do anything, print the first element in deque
            //since its a maximum among previous k elements
            //System.out.print(nums[deque.peekFirst()] + " ");
            //list.add(nums[deque.peekFirst()]);
            if(max<nums[deque.peekFirst()]) max = nums[deque.peekFirst()];
            //Now remove the elements which are out for next window (next k elements)
            while(!deque.isEmpty() && deque.peekFirst()<=i-k)
                deque.removeFirst();

            //Add the next element in the window = index i
            //remove all the elements which are larger than the next element = index i
            while(!deque.isEmpty() && nums[deque.peekLast()]>=nums[i])
                deque.removeLast();
            //add new element at the end
            deque.addLast(i);
        }
        //to print the last max element
        //list.add(nums[deque.peekFirst()]);
        if(max<nums[deque.peekFirst()]) max = nums[deque.peekFirst()];
        return max;
    }
    public static void main(String[] args) {
        int [] nums = {5, 2, 5, 4, 6, 8};
        int k = 3;

        int max = slidingWindow(nums, k);
        System.out.println(max);
    }
}
