package Graph;

import java.util.*;

/**
 * 拓扑排序、DFS、入度判断，入度全为安全节点即可
 *
 * 题解：
 * 对于一个节点 u，如果我们从 u 开始任意行走能够走到一个环里，那么 u 就不是一个安全的节点。换句话说，u 是一个安全的节点，
 * 当且仅当 u 直接相连的节点（u 的出边相连的那些节点）都是安全的节点。
 *
 * 因此我们可以首先考虑没有任何出边的节点，它们一定都是安全的。随后我们再考虑仅与这些节点直接相连的节点，它们也一定是安全的，以此类推。
 * 这样我们可以将所有的边全部反向，首先所有没有任何入边的节点都是安全的，我们把这些节点全部移除。随后新的图中没有任何入边的节点都是安全的，以此类推。
 * 我们发现这种做法实际上就是对图进行拓扑排序。
 *
 * 我们将所有的边反向，得到反向图 rgraph，随后将 rgraph 中所有没有入边的节点加入队列中。每一次我们取出队列中的一个节点 u，将它从图中删除，
 * 如果此时某个节点 v 存在从 u 到 v 的一条边，并且在删掉了这条边后，v 变成了没有入边的节点，那么就把 v 加入队列。以此类推，直到队列为空。
 * 最后所有加入过队列的节点即为安全的节点。
 *
 * */

public class _802_Find_Eventual_Safe_States {
    /** BFS */
    public List<Integer> eventualSafeNodes_bfs(int[][] G) {
        int N = G.length;
        boolean safe[] = new boolean[N];

        List<Set<Integer>> graph = new LinkedList<>();
        List<Set<Integer>> reversedGraph = new LinkedList<>();

        for(int i=0; i<N; i++){
            graph.add(new HashSet<>());
            reversedGraph.add(new HashSet<>());
        }
        Queue<Integer> queue = new LinkedList<>();
        for(int i=0; i<N; i++){
            if(G[i].length==0) queue.add(i); //没有出路，安全
            else{
                for(int node:G[i]){
                    graph.get(i).add(node);
                    reversedGraph.get(node).add(i); //变为入度判断
                }
            }
        }
        while(!queue.isEmpty()){
            int node = queue.poll();
            safe[node] = true;
            for(int in : reversedGraph.get(node)){
                graph.get(in).remove(node);
                if(graph.get(in).isEmpty()) queue.offer(in);
            }
        }
        List<Integer> res = new LinkedList<>();
        for(int i=0; i<N; i++){
            if(safe[i]) res.add(i);
        }
        return res;
    }

    /** DFS */

    /**
     * 染色问题
     *
     * 我们同样可以使用深度优先搜索的方法判断图中的每个节点是否能走到环中。对于每个节点，我们有三种染色的方法：
     * 白色表示该节点还没有被访问过；
     * 灰色表示该节点在栈中（这一轮搜索中被访问过）或者在环中；
     * 黑色表示该节点的所有相连的节点都被访问过，且该节点不在环中。（安全）
     *
     * 当我们第一次访问一个节点时，我们把它从白色变成灰色，并继续搜索与它相连的节点。如果在搜索过程中我们遇到一个灰色的节点，
     * 那么说明找到了一个环，此时退出搜索，所有的灰色节点保持不变（即从任意一个灰色节点开始，都能走到环中），如果搜索过程中，
     * 我们没有遇到灰色的节点，那么在回溯到当前节点时，我们把它从灰色变成黑色，即表示它是一个安全的节点。
     *
     * */
    public List<Integer> eventualSafeNodes_dfs(int[][] graph) {
        int N = graph.length;
        int[] color = new int[N]; //初始状态都是0, 1为灰色，2为安全
        List<Integer> res = new LinkedList<>();

        for(int i=0; i<N; i++){
            //从每个节点开始进行dfs搜索
            if(dfs(i, color, graph)) res.add(i);
        }
        return res;
    }

    private boolean dfs(int node, int[] color, int[][] graph){
        if(color[node]>0) {
            //已经被涉及过了
            return color[node]==2;
        }
        color[node] = 1; //标记为待定
        for(int next:graph[node]){
            if(color[next]==2) continue;//这条路安全
            if(color[next]==1) return false; //只要有一条路进入环就死了
            if(!dfs(next, color, graph)) return false;
        }
        color[node] = 2; // node为安全节点，标记，以node为起点的搜索结束，剩下的标记为1的节点全部在环里面
        return true;
    }
}
