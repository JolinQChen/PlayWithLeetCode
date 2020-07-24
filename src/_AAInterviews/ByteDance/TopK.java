package _AAInterviews.ByteDance;
import java.util.*;
public class TopK {
    public List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        // count the frequency and store in map
        for (int n:nums) map.put(n, map.getOrDefault(n,0)+1);

        // build a heap, sort the numbers based on frequency
        PriorityQueue<Integer> heap = new PriorityQueue<>(
                new Comparator<Integer>() {
                    public int compare(Integer a, Integer b) {
                        return map.get(a) - map.get(b);
                    }
                }
        );
        for(int n:map.keySet()) {
            heap.add(n);
            if(heap.size()>k) heap.poll();
        }
        List<Integer> res = new LinkedList<>();
        while(!heap.isEmpty()) {
            res.add(heap.poll());
        }
        Collections.reverse(res);
        return res;

    }
}
