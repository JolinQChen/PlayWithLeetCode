package _AAInterviews.weride;

public class _1319_Number_of_Operations_to_Make_Network_Connected {
    // follow u‍‍‌‌‌‍‌‍‍‌‌‌‍‍‍‍‌‌‍p是如果移动每条边需要cost，计算最小的总cost
    // union find
    private int[] parents;

    private int find(int x) {
        if(parents[x]!=x) parents[x] = find(parents[x]);
        return parents[x];
    }

    private boolean union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        if(rootX==rootY) return false;
        parents[rootX] = rootY;
        return true;
    }

    public int makeConnected(int n, int[][] connections) {
        // union find
        parents = new int[n];
        for(int i=0; i<n; i++) parents[i] = i;
        // connect
        int extraEdge = 0;
        for(int[] edge:connections) {
            if(!union(edge[0], edge[1])) extraEdge++;
        }
        // check how many groups
        int groups = 0;
        for(int i=0; i<n; i++) {
            if(parents[i]==i) groups++;
        }
        return groups-1>extraEdge?-1:groups-1;
    }

}
