package Array_String_TwoPointers_SlidingWindow_Greedy.largest_least_sort_etc;

import java.util.Comparator;
import java.util.PriorityQueue;

public class _215_Kth_Largest_Element_In_An_Array {
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(
                new Comparator<Integer>() {
                    @Override
                    public int compare(Integer o1, Integer o2) {
                        return o1-o2;
                    }
                }
        ); //由小到大排列，第一个就是第K大的
        for(int num : nums){
            pq.add(num);
            if(pq.size()>k) pq.poll();
        }
        return pq.peek();

    }
}
