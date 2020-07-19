package _All;

import sun.awt.image.ImageWatched;

import java.util.*;
// 考察Trajan Algorithm
/**
 * 有 n 台服务器，分别按从 0 到 n-1 的方式进行了编号。
 *
 * 它们之间以「服务器到服务器」点对点的形式相互连接组成了一个内部集群，其中连接 connections 是无向的。
 *
 * 从形式上讲，connections[i] = [a, b] 表示服务器 a 和 b 之间形成连接。任何服务器都可以直接
 * 或者间接地通过网络到达任何其他服务器。
 *
 * 「关键连接」是在该集群中的重要连接，也就是说，假如我们将它移除，便会导致某些服务器无法访问其他服务器。
 *
 * 请你以任意顺序返回该集群内的所有 「关键连接」。
 *
 * 输入：n = 4, connections = [[0,1],[1,2],[2,0],[1,3]]
 * 输出：[[1,3]]
 * 解释：[[3,1]] 也是正确的。
 *
 * */

public class _1192_Critical_Connections_in_a_Network {
    /**
     * Trajan Algorithm
     * https://zhuanlan.zhihu.com/p/101923309
     * 解题思路
     * 如果一个边是关键路径，当且仅当该边不在环中。
     *
     * 使用深度优先搜索查找环。
     *
     * Build a graph first
     *
     * DFS each node, find lowest timestamp for each nested child nodes during backtracking
     * If lowest timestamp is still larger after backtracking means it is not part of
     * the cricle, then it is critical connection.
     *
     * */
    private int time=1; //设置一个时间戳，从1开始计数
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {

        /**Initialization*/
        List<List<Integer>> res = new ArrayList<>();
        Map<Integer, List<Integer>> graphMap = new HashMap<>();


        for (List<Integer> connection : connections) {
            addConnectionToGraph(graphMap, connection.get(0), connection.get(1));
            addConnectionToGraph(graphMap, connection.get(1), connection.get(0));
        }
        int[] times = new int[n]; // timestamp of traversing order during DFS
        int[] lows = new int[n]; // lowest time current node can get
        int[] parents = new int[n]; // record parent of each node, so child won't go to parent in DFS
        for (int i = 0; i < parents.length; i++) {
            parents[i] = -1;
        }
        for (int i = 0; i < n; i++) {
            if (times[i] > 0) continue; // means visited
            dfs(i, graphMap, times, lows, parents, res);
        }
        return res;
    }
    private void dfs(int node, Map<Integer, List<Integer>> map, int[] times, int[] lows, int[] parents, List<List<Integer>> res) {
        // base cases
        if (times[node] > 0) return; // visited before

        lows[node] = times[node] = time++;

        // recursive rule
        for (int child : map.get(node)) {
            if (parents[node] == child) continue; // child never goes to it's parent again
            if (times[child] > 0 && child != parents[node]) { // child never goes to it's parent again
                lows[node] = Math.min(lows[node], lows[child]);
            }
            if (times[child] > 0) continue;

            parents[child] = node;

            dfs(child, map, times, lows, parents, res);
            if(lows[child] > times[node]) { // critical connection, not part of the circle
                // record critical connection
                List<Integer> list = new ArrayList<>();
                list.add(node);
                list.add(child);
                res.add(list);
            }
            lows[node] = Math.min(lows[node], lows[child]); // back tracking, updating the lowest time
        }
    }

    private void addConnectionToGraph(Map<Integer, List<Integer>> graphMap, int node1, int node2) {
        List<Integer> neighbours = graphMap.getOrDefault(node1, new ArrayList<>());
        neighbours.add(node2);
        graphMap.put(node1, neighbours);
    }
}
