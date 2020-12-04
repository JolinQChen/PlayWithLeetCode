package _AAInterviews.Amazon;

import java.util.*;
public class MinCostToRepairEdges {
    static int components;
    static int[] parent;
    static int[] rank;
    private static int find(int x) {
        if(parent[x]!=x) parent[x] = find(parent[x]);
        return parent[x];
    }
    private static boolean isUnion(int x, int y) {
        return find(x)==find(y);
    }
    private static void union(int x, int y) {
        int xRoot = find(x);
        int yRoot = find(y);
        if(xRoot == yRoot) return;
        components--;
        int xRootRank = rank[xRoot];
        int yRootRank = rank[yRoot];
        if(xRootRank>yRootRank) {
            parent[yRoot] = xRoot;
        }
        else if(xRootRank<yRootRank) {
            parent[xRoot] = yRoot;
        }
        else {
            parent[xRoot] = yRoot;
            rank[yRoot]++;
        }
    }

    public static int minimumCost(int n, int[][] edges, int[][] edgesToRepair) {
        parent = new int[n+1];
        rank = new int[n+1];
        components = n;
        int cost = 0;
        for(int i=0; i<=n; i++) parent[i] = i;
        Set<Integer> damaged = new HashSet<>();
        Arrays.sort(edgesToRepair,(a,b)->(a[2]-b[2]));
        for(int[] e:edgesToRepair){
            damaged.add(e[1]*n+e[0]);
            damaged.add(e[0]*n+e[1]);
        }
        for(int[] edge:edges) {
            if(!damaged.contains(edge[0]*n+edge[1])) {
//                System.out.println(edge[0] + ", " + edge[1] + " not in damaged set");
                if(!isUnion(edge[0],edge[1])) union(edge[0],edge[1]);
            }
        }

        if(components==1) return 0;
        for(int[] e:edgesToRepair) {
            if(!isUnion(e[0],e[1])) {
                union(e[0],e[1]);
                cost += e[2];
                if(components==1) return cost;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[][] edges = {{1, 2}, {2, 3}, {4, 5}, {5, 3}, {1, 6}, {2, 4}};
        int[][] edgesToRepair = {{1, 6, 410}, {2, 4, 804}};
        int res = minimumCost(6,edges,edgesToRepair);
        System.out.println(res);
    }
}
