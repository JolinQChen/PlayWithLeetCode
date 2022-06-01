package Graph;

import javafx.util.Pair;
import org.junit.Test;

import java.util.*;

/**
 * 迪杰斯特拉最短路径算法（Dijkstra's）[Accepted]
 * 本质为一个加权有向图
 * There are N network nodes, labelled 1 to N.
 *
 * Given times, a list of travel times as directed edges times[i] = (u, v, w), where u is the source node,
 * v is the target node, and w is the time it takes for a signal to travel from source to target.
 *
 * Now, we send a signal from a certain node K. How long will it take for all nodes to receive the signal?
 * If it is impossible, return -1.
 *
 * Input: times = [[2,1,1],[2,3,1],[3,4,1]], N = 4, K = 2
 * Output: 2
 * */

public class _743_Network_Delay_Time {
    //private int res;
    /** 这个方法超时了... DFS找出到达每个点的所有路径的最小时间再取最大 */
    private int[] reached; //到达每个点需要的时间
    public int networkDelayTime_1(int[][] times, int N, int K) {
        //res = 0; //最大时间
        Map<Integer, List<int[]>> map = new HashMap<>();
        //构建图
        for(int[] edge:times){
            List<int[]> list = map.getOrDefault(edge[0],new LinkedList<>());
            int[] tmp = new int[2];
            tmp[0] = edge[1];
            tmp[1] = edge[2];
            list.add(tmp);
            map.put(edge[0],list);
        }
        boolean[] visited = new boolean[N+1];
        visited[K] = true;
        reached = new int[N+1];
        Arrays.fill(reached,Integer.MAX_VALUE);
        //reached[K] = 0;
        backTracking(visited, K, map,0);
        int res = 0;
        for(int i=1; i<=N; i++) {
            if(reached[i] == Integer.MAX_VALUE) return -1;
            res = Math.max(res, reached[i]);
        }
        return res;
    }
    private void backTracking(boolean[] visited, int curNode, Map<Integer, List<int[]>> map, int curSum){
        reached[curNode] = Math.min(reached[curNode],curSum);
        boolean reachEnd = true;
        if(map.containsKey(curNode)){
            for(int[] list:map.get(curNode)){
                if(!visited[list[0]]){
                    reachEnd = false;
                    visited[list[0]] = true;
                    backTracking(visited, list[0],map,curSum+list[1]);
                    visited[list[0]] = false;
                    //System.out.println(list[1]);
                }
            }
        }
        if(reachEnd)return;
    }



    /** 参考解法: 迪杰斯特拉最短路径算法（Dijkstra's）[Accepted]
     * Dijkstra's 算法是每次扩展一个距离最短的点，更新与其相邻点的距离。
     * wiki: Dijkstra's algorithm (or Dijkstra's Shortest Path First algorithm,
     * SPF algorithm)[2] is an algorithm for finding the shortest paths between
     * nodes in a graph, which may represent, for example, road networks. It was
     * conceived by computer scientist Edsger W. Dijkstra in 1956 and published
     * three years later.[3][4][5]
     * */
    /** 解法1：基本实现方法 BFS O(N*E)*/
    private int[] reachedBFS;
    public int networkDelayTime_bfs(int[][] times, int n, int k) {
        reachedBFS = new int[n];
        Arrays.fill(reachedBFS, -1);
        Map<Integer, List<int[]>> map = new HashMap<>();
        for(int[] time:times) {
            if(!map.containsKey(time[0])) map.put(time[0], new ArrayList<>());
            map.get(time[0]).add(new int[]{time[1], time[2]});
        }
        reachedBFS[k-1] = 0;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(k);
        int reachedCount = 1;
        while(!queue.isEmpty()) {
            int cur = queue.poll();
            if(!map.containsKey(cur)) continue;
            List<int[]> nexts = map.get(cur);
            for(int[] next:nexts) {
                if(reachedBFS[next[0]-1] == -1) {
                    reachedBFS[next[0]-1] = reachedBFS[cur-1] + next[1];
                    reachedCount++;
                    queue.add(next[0]);
                }
                else if(reachedBFS[next[0]-1] > reachedBFS[cur-1] + next[1]) {
                    reachedBFS[next[0]-1] = reachedBFS[cur-1] + next[1];
                    queue.add(next[0]);
                }


            }
        }
        if(reachedCount<n) return -1;
        int res = -1;
        for(int i=0; i<n; i++) {
            res = Math.max(res, reachedBFS[i]);
        }
        return res;
    }

    /** 解法2：堆实现方法 O(N*log(N))*/
    public int networkDelayTime_heap(int[][] times, int n, int k) {
        // heap
        int countReached = 1;
        int[] dist = new int[n];
        Arrays.fill(dist, -1);
        dist[k-1] = 0;
        Map<Integer, List<int[]>> graph = new HashMap<>();
        for(int[] time:times) {
            if(!graph.containsKey(time[0])) graph.put(time[0], new ArrayList<>());
            graph.get(time[0]).add(new int[]{time[1], time[2]});
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
        pq.add(new int[]{k,0});
        while(!pq.isEmpty()) {
            int[] cur = pq.poll(); // next shortest
            if(graph.containsKey(cur[0])) {
                List<int[]> nexts = graph.get(cur[0]);
                for(int[] next:nexts) {
                    if(dist[next[0]-1] == -1 || dist[next[0]-1] > cur[1] + next[1]) {
                        if(dist[next[0]-1] == -1) countReached++;
                        dist[next[0]-1] = cur[1] + next[1];
                        pq.add(new int[]{next[0], dist[next[0]-1]});
                    }
                }
            }

        }

        if(countReached < n) return -1;
        int res = -1;
        for(int d : dist) {
            res = Math.max(res, d);
        }
        return res;

    }


// dijkstra
    Map<Integer, List<Pair<Integer, Integer>>> adj = new HashMap<>();

    private void dijkstra(int[] signalReceivedAt, int source, int n) {
        Queue<Pair<Integer, Integer>> pq = new PriorityQueue<Pair<Integer,Integer>>
                (Comparator.comparing(Pair::getKey));
        pq.add(new Pair(0, source));

        // Time for starting node is 0
        signalReceivedAt[source] = 0;

        while (!pq.isEmpty()) {
            Pair<Integer, Integer> topPair = pq.remove();

            int currNode = topPair.getValue();
            int currNodeTime = topPair.getKey();

            if (currNodeTime > signalReceivedAt[currNode]) {
                continue;
            }

            if (!adj.containsKey(currNode)) {
                continue;
            }

            // Broadcast the signal to adjacent nodes
            for (Pair<Integer, Integer> edge : adj.get(currNode)) {
                int time = edge.getKey();
                int neighborNode = edge.getValue();

                // Fastest signal time for neighborNode so far
                // signalReceivedAt[currNode] + time :
                // time when signal reaches neighborNode
                if (signalReceivedAt[neighborNode] > currNodeTime + time) {
                    signalReceivedAt[neighborNode] = currNodeTime + time;
                    pq.add(new Pair(signalReceivedAt[neighborNode], neighborNode));
                }
            }
        }
    }

    public int networkDelayTime(int[][] times, int n, int k) {
        // Build the adjacency list
        for (int[] time : times) {
            int source = time[0];
            int dest = time[1];
            int travelTime = time[2];

            adj.putIfAbsent(source, new ArrayList<>());
            adj.get(source).add(new Pair(travelTime, dest));
        }

        int[] signalReceivedAt = new int[n + 1];
        Arrays.fill(signalReceivedAt, Integer.MAX_VALUE);

        dijkstra(signalReceivedAt, k, n);

        int answer = Integer.MIN_VALUE;
        for (int i = 1; i <= n; i++) {
            answer = Math.max(answer, signalReceivedAt[i]);
        }

        // INT_MAX signifies atleat one node is unreachable
        return answer == Integer.MAX_VALUE ? -1 : answer;
    }

    @Test
    public void test(){
        int[][] times = {{1,2,1},{1,3,3},{2,3,1}};
        System.out.println(networkDelayTime_bfs(times,3,1));
    }
    /**
     * [[1,2,1],[2,3,2],[1,3,2]]
     *             3
     *             1
     */

}
