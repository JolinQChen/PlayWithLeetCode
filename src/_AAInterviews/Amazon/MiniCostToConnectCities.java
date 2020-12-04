package _AAInterviews.Amazon;

import javax.swing.*;
import java.util.Arrays;

public class MiniCostToConnectCities {
    int components;
    int[] parent;
    int[] rank;
    private int find(int x) {
        if(parent[x]!=x) parent[x] = find(parent[x]);
        return parent[x];
    }
    private boolean isUnion(int x, int y) {
        return find(x)==find(y);
    }
    private void union(int x, int y) {
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

    public int minimumCost(int N, int[][] connections) {
        if(N==1) return 0;
        components = N;
        parent = new int[N+1];
        for(int i=0; i<=N; i++) parent[i] = i;
        rank = new int[N+1];
        int cost = 0;
        Arrays.sort(connections, (a,b)->(a[2]-b[2]));
        for(int[] connection:connections) {
            if(!isUnion(connection[0],connection[1])) {
                union(connection[0],connection[1]);
                cost += connection[2];
                if(components==1) return cost;
            }
        }
        return -1;
    }
}
