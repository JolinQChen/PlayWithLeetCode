package _All;

import java.util.*;
/**
 * Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes),
 * write a function to find the number of connected components in an undirected graph.
 *
 * Input: n = 5 and edges = [[0, 1], [1, 2], [3, 4]]
 *
 *      0          3
 *      |          |
 *      1 --- 2    4
 *
 * Output: 2
 *
 * Input: n = 5 and edges = [[0, 1], [1, 2], [2, 3], [3, 4]]
 *
 *      0           4
 *      |           |
 *      1 --- 2 --- 3
 *
 * Output:  1
 * */

public class _323_Number_of_Connected_Components_in_an_Undirected_Graph {
    private int[] parent;
    private int[] rank;

    private int find(int i){
        if(parent[i]!=i) parent[i] = find(parent[i]);
        return parent[i];
    }

    private boolean union(int x, int y){
        int xRoot = find(x);
        int yRoot = find(y);
        int xRank = rank[x];
        int yRank = rank[y];
        if(xRoot == yRoot) return false;
        if(xRank < yRank) parent[xRoot] = yRoot;
        else if(xRank > yRank) parent[yRoot] = xRoot;
        else {
            parent[xRoot] = yRoot;
            rank[xRoot]++;
        }
        return true;
    }



    public int countComponents(int n, int[][] edges) {
        if(edges==null || edges.length==0) return n;
        // initiate
        parent = new int[n];
        for(int i=0; i<n; i++) parent[i] = i;
        rank = new int[n];
        int count = n;
        for(int i=0; i<edges.length; i++){
            // 合并每一组
            if(union(edges[i][0], edges[i][1])) {
                System.out.println(i);
                count--;
            }
        }
        return count;
    }

}
