package unionFind;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Given an unsorted array of integers, find the length of the longest consecutive elements sequence.
 *
 * Your algorithm should run in O(n) complexity.
 * */

public class _128_Longest_Consecutive_Sequence {
    private static int[] parent;
    private static int[] rank;
    private static int[] size;
    private static int find(int i){
        if(parent[i]!=i) parent[i] = find(parent[i]);
        return parent[i];

    }

    private static void union(int x, int y){
        int xRoot = find(x);
        int yRoot = find(y);
        if(xRoot == yRoot) return;
        if(rank[xRoot]<rank[yRoot]) {
            parent[xRoot] = yRoot;
            size[yRoot] += size[xRoot];
        }
        else if(rank[xRoot]>rank[yRoot]) {
            parent[yRoot] = xRoot;
            size[xRoot] += size[yRoot];
        }
        else {
            parent[xRoot] = yRoot;
            rank[xRoot]++;
            size[yRoot] += size[xRoot];
        }
    }


    public static int longestConsecutive(int[] nums) {
        if(nums==null||nums.length==0) return 0;
        // initialization
        int n = nums.length;
        parent = new int[n];
        for(int i=0; i<n; i++) parent[i]=i;
        rank = new int[n];
        size = new int[n];
        Arrays.fill(size,1);
        Map<Integer, Integer> map = new HashMap<>();
        int index = 0;
        for (int i=0; i<n; i++) map.put(nums[i],i);
        //连续数字聚为一类
        for(int num:map.keySet()){
            if(map.containsKey(num-1)) union(map.get(num-1),map.get(num));
            if(map.containsKey(num+1)) union(map.get(num+1),map.get(num));
        }
        //遍历parent看看有多少独立节点
        int res = 0;
        for(int i=0; i<n; i++){
            res = Math.max(res, size[i]);
            System.out.println(size[i]);
        }
        return res;
    }

    public static void main(String[] args){
        int[] input = {0,0,-1};
        int res = longestConsecutive(input);
        System.out.println("final");
        System.out.println(res);
    }
}
