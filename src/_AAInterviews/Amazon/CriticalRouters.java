package _AAInterviews.Amazon;

import java.util.*;
public class CriticalRouters {
    //无向图的桥边/割点问题 -- Trajan Algorithm
    //本题找割点
    private static int time = 1; // global timestamp
    private static int[] dfn;
    private static int[] low;
    private static int[] father;
    private static Set<Integer> vertex;
    private static Map<Integer, List<Integer>> graph;

    public static List<Integer> findVertex(int numNodes, int numEdges, int[][] edges) {
        // construct graph
        graph = new HashMap<>();
        for(int i=0; i<numNodes; i++) graph.put(i, new ArrayList<>());
        for(int[] edge:edges) {
            int v = edge[0];
            int u = edge[1];
            graph.get(v).add(u);
            graph.get(u).add(v);
        }

        // begin Trajan

        dfn = new int[numNodes];
        low = new int[numNodes];
        father = new int[numNodes];
        Arrays.fill(father,-1);
        vertex = new HashSet<>();
        for(int i=0; i<numNodes; i++) {
            if(dfn[i]==0) dfs(i);
        }
        return new ArrayList<>(vertex);

    }

    private static void dfs(int x) {
        time++;
        dfn[x] = low[x] = time;
        // 定义一个child基数，以child个数为判决条件
        /**
         * 条件1：为root,child >= 2;
         * 条件2：不为root，low[child]>=dfn[x];
         * */
        int child = 0;
        List<Integer> nextNode = graph.get(x);
        for(int y:nextNode) {
            if(dfn[y]==0) {
                // y还没有被访问过，此时y是x的child
                child++;
                father[y] = x;
                dfs(y);
                if(father[x] == -1 && child>=2) vertex.add(x);
                else if(father[x]!=-1 && low[y]>=dfn[x]) vertex.add(x);

                /** 更新low!!!*/
                low[x] = Math.min(low[x], low[y]);
            }
            else if(y!=father[x]) {
                // y已经被访问过，且y不是x的father （child->father不处理）
                low[x] = Math.min(low[x],low[y]);

            }
        }


    }
    public static void main(String[] args) {
        int numNodes = 7, numEdges = 7;
        int[][] edges = {{0, 1}, {0, 2}, {1, 3}, {2, 3}, {2, 5}, {5, 6}, {3, 4}};
        List<Integer> res = findVertex(numNodes, numEdges, edges);
        for(int r:res) System.out.println(r);
    }

}
