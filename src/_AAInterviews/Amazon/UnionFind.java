package _AAInterviews.Amazon;

import java.util.Arrays;

public class UnionFind {
    private int[] parent;
    private int[] rank;

    public UnionFind(int n) {
        parent = new int[n];
        rank = new int[n];
        for(int i=0; i<n; i++) parent[i] = i;
    }

    public int find(int x) {
        if(parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    public boolean union(int x, int y) {
        int x_root = find(x);
        int y_root = find(y);
        if(x_root == y_root) return false;
        int x_root_rank = rank[x_root];
        int y_root_rank = rank[y_root];
        if(x_root_rank == y_root_rank) {
            parent[x_root] = y_root;
            rank[y_root]++;
        }
        else if(x_root_rank>y_root_rank) {
            parent[y_root] = x_root;
        }
        else {
            parent[x_root] = y_root;
        }
        return true;
    }
}
