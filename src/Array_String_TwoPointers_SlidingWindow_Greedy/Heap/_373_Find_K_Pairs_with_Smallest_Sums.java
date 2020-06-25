package Array_String_TwoPointers_SlidingWindow_Greedy.Heap;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * You are given two integer arrays nums1 and nums2 sorted in ascending order and an integer k.
 *
 * Define a pair (u,v) which consists of one element from the first array and one element from the
 * second array.
 *
 * Find the k pairs (u1,v1),(u2,v2) ...(uk,vk) with the smallest sums.
 *
 * Input: nums1 = [1,7,11], nums2 = [2,4,6], k = 3
 * Output: [[1,2],[1,4],[1,6]]
 * Explanation: The first 3 pairs are returned from the sequence:
 *              [1,2],[1,4],[1,6],[7,2],[7,4],[11,2],[7,6],[11,4],[11,6]
 * */
public class _373_Find_K_Pairs_with_Smallest_Sums {
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {

        List<List<Integer>> list = new ArrayList<>();
        if(nums1==null || nums2== null || nums1.length==0 || nums2.length==0 || k==0)
            return list;
        PriorityQueue<int[]> pq = new PriorityQueue<>( ( a, b) -> {

            int sum1 = nums1[a[0]] + nums2[a[1]];
            int sum2 = nums1[b[0]] + nums2[b[1]];
            if(sum1 == sum2) {
                return a[0] - b[0];
            }
            return sum1-sum2;
        });
        for(int i=0;i<nums1.length && i<k;i++)
            pq.add(new int[]{i,0}); /**避免了重复*/


        while(list.size()!=k && !pq.isEmpty()) {

            int[] top = pq.poll();

            List<Integer> temp = new ArrayList<>();

            temp.add(nums1[top[0]]);
            temp.add(nums2[top[1]]);
            list.add(temp);
            if(top[1] != (nums2.length-1)) {
                pq.add(new int[]{top[0], top[1]+1});
            }
        }
        return list;
    }
}
