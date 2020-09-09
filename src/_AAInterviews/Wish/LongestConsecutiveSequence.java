package _AAInterviews.Wish;
/**
 * Input: [100, 4, 200, 1, 3, 2]
 * Output: 4
 * Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.
 *
 * Your algorithm should run in O(n) complexity.
 * */
import java.util.*;
public class LongestConsecutiveSequence {

    private int[] parent;
    private int[] rank;
    private int[] size;

    private int find(int x) {
        if(parent[x]!=x) parent[x] = find(parent[x]);
        return parent[x];
    }
    private void union(int x, int y) {
        int xRoot = find(x);
        int yRoot = find(y);
        if(xRoot==yRoot) return;
        if(rank[xRoot]>rank[yRoot]) {
            parent[yRoot] = xRoot;
            size[xRoot] += size[yRoot];
        } else if(rank[xRoot]<rank[yRoot]) {
            parent[xRoot] = yRoot;
            size[yRoot] += size[xRoot];
        } else {
            parent[xRoot] = yRoot;
            rank[yRoot]++;
            size[yRoot] += size[xRoot];
        }
    }


    public int longestConsecutive(int[] nums) {
        if(nums == null || nums.length==0) return 0;

        // initiation
        int n = nums.length;
        parent = new int[n];
        for(int i=0; i<n; i++) parent[i]=i;
        rank = new int[n];
        size = new int[n];
        Arrays.fill(size,1);
        Map<Integer, Integer> map = new HashMap<>(); // value - position
        for(int i=0; i<n; i++) map.put(nums[i],i);
        for(int num:map.keySet()) {
            // union consecutive numbers
            if(map.containsKey(num-1)) {
                union(map.get(num), map.get(num-1));
            }
            if(map.containsKey(num+1)) {

                union(map.get(num), map.get(num+1));
            }
        }
        int res = 0;
        for(int i=0; i<n; i++) res = Math.max(res, size[i]);
        return res;
    }

}
