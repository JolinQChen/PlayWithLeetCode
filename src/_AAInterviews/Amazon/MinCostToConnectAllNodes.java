package _AAInterviews.Amazon;// union find
import java.util.*;
public class MinCostToConnectAllNodes {
    static int[] parent;
    static int[] rank;
    static int parts;
    private static int find(int x) {
        if(parent[x]!=x) parent[x] = find(parent[x]);
        return parent[x];
    }
    private static void union(int x, int y) {
        parts--;
        int x_root = find(x);
        int y_root = find(y);
        if(x_root == y_root) return;
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
    }

    private static boolean isUnion(int x, int y) {
        return find(x)==find(y);
    }



    // 正式
    public static int minCost(int n, int edges[][], int newEdges[][]){
        // n个nodes，1-n
        parts = n;
        parent = new int[n+1];
        rank = new int[n+1];
        for(int i=0; i<=n; i++) parent[i] = i;
        for(int[] edge:edges) {
            if(!isUnion(edge[1],edge[0])) union(edge[0],edge[1]);
        }
        if(parts == 1) return 0;
        Arrays.sort(newEdges, (a,b)->(a[2]-b[2]));
        int cost = 0;
        for(int[] newEdge : newEdges) {
            if(!isUnion(newEdge[0], newEdge[1])) {
                cost += newEdge[2];
                union(newEdge[0],newEdge[1]);
                if(parts == 1) return cost;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[][] edges = {{1,4},{4,5},{2,3}};;
        int[][] newEdges = {{1, 2, 5},{1, 3, 10},{1, 6, 2},{5, 6, 5}};
        int n = 6;
        int res = minCost(n,edges,newEdges);
        System.out.println(res);
    }


}
