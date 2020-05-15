package unionFind;

import java.util.Arrays;

public class UnionFind {
    int[] parent;
    int[] rank;
    int[] size;

    public int getSize(int x) {
        return size[findRoot(x)];
    }
    public UnionFind(int n) {
        this.parent = new int[n];
        this.rank = new int[n];
        this.size = new int[n];
        Arrays.fill(size, 1);
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }

    public int findRoot(int x) {
        if (parent[x] != x) {
            parent[x] = findRoot(parent[x]);
        }
        return parent[x];
    }

    public void union(int x, int y) {
        int xRoot = findRoot(x);
        int yRoot = findRoot(y);
        int xRootRank = rank[xRoot];
        int yRootRank = rank[yRoot];

        if (xRootRank < yRootRank) {
            parent[xRoot] = yRoot;
            //size[yRoot] += size[xRoot];
        } else if (xRootRank > yRootRank) {
            parent[yRoot] = xRoot;
            //size[xRoot] += size[yRoot];
        } else {
            parent[yRoot] = xRoot;
            rank[yRoot]++;
            //size[xRoot] += size[yRoot];
        }
    }
}
