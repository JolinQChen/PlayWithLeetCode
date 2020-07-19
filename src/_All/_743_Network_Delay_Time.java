package _All;

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
    /** 解法1：基本实现方法 O(N*2)，略*/

    /** 解法2：堆实现方法 O(N*log(N))*/
    public int networkDelayTime_2(int[][] times, int N, int K) {
        Map<Integer, List<int[]>> graph = new HashMap<>();
        for (int[] edge: times) {
            if (!graph.containsKey(edge[0]))
                graph.put(edge[0], new ArrayList<int[]>());
            graph.get(edge[0]).add(new int[]{edge[1], edge[2]});
        }
        PriorityQueue<int[]> heap = new PriorityQueue<>(
                new Comparator<int[]>() {
                    @Override
                    public int compare(int[] o1, int[] o2) {
                        return o1[1]-o2[1];
                    }
                }
        );
        heap.add(new int[]{K,0});
        Map<Integer, Integer> dist = new HashMap<>();
        while(!heap.isEmpty()){
            int[] info = heap.poll();
            int d = info[1];
            int node = info[0];
            if(dist.containsKey(node)) continue; //已经遍历过了
            dist.put(node,d);
            if(graph.containsKey(node)){
                for(int[] edge:graph.get(node)){
                    int next = edge[0];
                    int d2 = edge[1];
                    if(!dist.containsKey(next)){
                        heap.add(new int[]{next,d+d2});
                    }
                }
            }
        }
        if(dist.size()!=N) return -1;
        int ans = 0;
        for (int cand: dist.values())
            ans = Math.max(ans, cand);
        return ans;

    }





    public static void main(String[] args){
        _743_Network_Delay_Time test = new _743_Network_Delay_Time();
        int[][] times = {{1,2,1},{1,3,3},{2,3,1}};
        System.out.println(test.networkDelayTime_2(times,3,1));
    }
    /**
     * [[1,2,1],[2,3,2],[1,3,2]]
     *             3
     *             1
     */

}
