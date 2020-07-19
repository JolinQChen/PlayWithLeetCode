package _All;

import java.util.*;
/*反向BSF，自下而上，入度判断*/


/**
 * For an undirected graph with tree characteristics, we can choose any node as the root.
 * The result graph is then a rooted tree. Among all possible rooted trees, those with minimum
 * height are called minimum height trees (MHTs). Given such a graph, write a function to find all
 * the MHTs and return a list of their root labels.
 *
 * Format
 * The graph contains n nodes which are labeled from 0 to n - 1. You will be given the number
 * n and a list of undirected edges (each edge is a pair of labels).
 * You can assume that no duplicate edges will appear in edges. Since all edges are undirected,
 * [0, 1] is the same as [1, 0] and thus will not appear together in edges.
 *
 * Example 1 :
 *
 * Input: n = 4, edges = [[1, 0], [1, 2], [1, 3]]
 *
 *         0
 *         |
 *         1
 *        / \
 *       2   3
 *
 * Output: [1]
 *
 * Example 2 :
 *
 * Input: n = 6, edges = [[0, 3], [1, 3], [2, 3], [4, 3], [5, 4]]
 *
 *      0  1  2
 *       \ | /
 *         3
 *         |
 *         4
 *         |
 *         5
 *
 * Output: [3, 4]
 *
 * The height of a rooted tree is the number of edges on the longest downward path between the root and a leaf.
 * */


public class _310_Minimum_Height_Trees {
    //暴力依次BFS，超时
    public List<Integer> findMinHeightTrees_1(int n, int[][] edges) {

        boolean[] visited;// = new int[n];
        int curMin = Integer.MAX_VALUE;
        List<Integer> res = new LinkedList<>();
        if(n==1) {
            res.add(0);
            return res;
        }
        else if(n==0) return res;
        //用一个map表示graph
        Map<Integer, List<Integer>> graphMap = new HashMap<>();
        for(int[] edge:edges){
            List<Integer> list0 = graphMap.getOrDefault(edge[0],new LinkedList<>());
            list0.add(edge[1]);
            graphMap.put(edge[0],list0);

            List<Integer> list1 = graphMap.getOrDefault(edge[1],new LinkedList<>());
            list1.add(edge[0]);
            graphMap.put(edge[1],list1);
        }
        for(int i=0; i<n; i++){
            //以每个点作为root遍历得到深度
            visited = new boolean[n];
            Queue<Integer> queue = new LinkedList<>();
            queue.add(i);
            int depth = 0;
            int curLevelNum = 1;
            int nextLevelNum = 0;
            while (!queue.isEmpty()){
                while(curLevelNum>0){
                    int cur = queue.poll();
                    //System.out.println(cur);
                    visited[cur] = true;
                    curLevelNum--;
                    for (int next:graphMap.get(cur)){
                        if(!visited[next]) {
                            //System.out.println(next);
                            queue.add(next);
                            nextLevelNum++;
                        }
                    }
                }
                depth++;
                //System.out.println(depth);
                curLevelNum = nextLevelNum;
                nextLevelNum = 0;
                if(depth>curMin) break;
            }
            if(depth<curMin){
                res = new LinkedList<>();
                res.add(i);
                curMin = depth;
            }
            else if(depth == curMin) res.add(i);
        }
        return res;
    }

    // 改进
    /**
     * 看图（题目介绍里面的图）分析一下，发现，越是靠里面的节点越有可能是最小高度树
     * 我们可以倒着来。
     * 从边缘开始，先找到所有出度为1的节点（有潜力作为叶子结点），然后把所有出度为1的节点进队列，然后不断地bfs，
     * 最后找到的就是两边同时向中间靠近的节点，那么这个中间节点就相当于把整个距离二分了，
     * 那么它当然就是到两边距离最小的点啦，也就是到其他叶子节点最近的节点了。
     *
     * 反向BFS
     * */
    public List<Integer> findMinHeightTrees_2(int n, int[][] edges) {
        List<Integer> res = new LinkedList<>();
        if(n==1) {
            res.add(0);
            return res;
        }
        else if(n==0) return res;

        /** 建立各个节点的出度表 */
        int[] degree = new int[n];
        /** 建立图关系，在每个节点的list中存储相连节点 */
        List<List<Integer>> map = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            map.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            degree[edge[0]]++;
            degree[edge[1]]++;/*出度++*/
            map.get(edge[0]).add(edge[1]);/*添加相邻节点*/
            map.get(edge[1]).add(edge[0]);
        }

        /** 建立队列*/
        Queue<Integer> queue = new LinkedList<>();
        /** 把所有的出度为1的节点，即叶子结点加入队列中*/
        for(int i=0; i<n; i++) {
            if(degree[i]==1) queue.add(i);
        }
        while (!queue.isEmpty()){
            res = new LinkedList<>(); //每层循环都要新new一个结果,这样最后保存的就是最终的最小高度树了
            int size = queue.size(); //每一层节点的数量，比如第一次循环中为叶子结点的数量
            for(int i=0; i<size; i++){
                int cur = queue.poll();
                res.add(cur); //将当前节点加入res中
                List<Integer> neighbors = map.get(cur);
                for(int neighbor:neighbors){
                    degree[neighbor]--;
                    if(degree[neighbor]==1) queue.offer(neighbor);
                }

            }

        }
        return res;
    }
    public static void main(String[] args){
        _310_Minimum_Height_Trees test = new _310_Minimum_Height_Trees();
        int[][] edges = {{0,3},{1,3},{2,3},{4,3},{5,4}};
        List<Integer> res = test.findMinHeightTrees_2(6,edges);
        for(int i:res) System.out.println(i);
    }
}
