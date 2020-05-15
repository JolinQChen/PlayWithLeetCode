import java.util.*;

/**
 * Given n nodes labeled from 0 to n-1 and a list of undirected edges
 * (each edge is a pair of nodes), write a function to check whether these
 * edges make up a valid tree.
 *
 * Note:
 * you can assume that no duplicate edges will appear in edges.
 * Since all edges are undirected, [0,1] is the same as [1,0] and thus will
 * not appear together in edges.
 *
 * Input: n = 5, and edges = [[0,1], [1,2], [2,3], [1,3], [1,4]]
 * Output: false
 * */
public class _261_Graph_Valid_Tree {
    private int[] parent;
    private int[] rank;
    private int find(int i){
        if(parent[i]!=i) parent[i] = find(parent[i]);
        return parent[i];
    }
    private boolean union(int x, int y){
        int xRoot = find(x);
        int yRoot = find(y);
        //已经是联通了，再次出现说明成环
        if(xRoot == yRoot) return false;
        int xRank = rank[x];
        int yRank = rank[y];
        if(xRank<yRank){
            parent[xRoot] = yRoot;
        }
        else if(xRank>yRank){
            parent[yRoot] = xRoot;
        }
        else {
            parent[xRoot] = yRoot;
            rank[xRoot]++;
        }
        return true;
    }
    public boolean validTree(int n, int[][] edges) {
        if(edges==null || edges.length==0||edges[0].length==0) return false;
        // initialization
        parent = new int[n];
        rank = new int[n];
        for(int i=0; i<n; i++) parent[i] = i;

        int R = edges.length;
        int C = edges[0].length;
        for(int i=0; i<R; i++){
            boolean res = union(edges[i][0], edges[i][1]);
            if(res == false) return false;
        }
        return true;
    }
}
