package unionFind;

/**
 * UNION FIND
 *
 * 在本问题中, 树指的是一个连通且无环的无向图。
 *
 * 输入一个图，该图由一个有着N个节点 (节点值不重复1, 2, ..., N) 的树及一条附加的边构成。
 * 附加的边的两个顶点包含在1到N中间，这条附加的边不属于树中已存在的边。
 *
 * 结果图是一个以边组成的二维数组。每一个边的元素是一对[u, v] ，满足 u < v，表示连接顶点u和v的无向图的边。
 *
 * 返回一条可以删去的边，使得结果图是一个有着N个节点的树。如果有多个答案，则返回二维数组中最后出现的边。
 * 答案边 [u, v] 应满足相同的格式 u < v。
 *
 * 示例 1：
 *
 * 输入: [[1,2], [1,3], [2,3]]
 * 输出: [2,3]
 * 解释: 给定的无向图为:
 *   1
 *  / \
 * 2 - 3
 *
 * 示例 2：
 *
 * 输入: [[1,2], [2,3], [3,4], [1,4], [1,5]]
 * 输出: [1,4]
 * 解释: 给定的无向图为:
 * 5 - 1 - 2
 *     |   |
 *     4 - 3
 *
 * The size of the input 2D-array will be between 3 and 1000;
 * Every integer represented in the 2D-array will be between 1 and N, where N is the size of the input array.
 * */

public class _684_Redundant_Connection {
    private int[] parent;
    private int[] rank;

    private int find(int i){
        if(parent[i]!=i) parent[i] = find(parent[i]);
        return parent[i];
    }

    private void union(int x, int y){
        int xRoot = find(x);
        int yRoot = find(y);
        int xRootRank = rank[xRoot];
        int yRootRank = rank[yRoot];

        if(xRootRank<yRootRank) parent[xRoot] = yRoot;
        else if(yRootRank<xRootRank) parent[yRoot] = xRoot;
        else {
            parent[xRoot] = yRoot;
            rank[yRootRank]++;
        }
    }
    private boolean isConnected(int x, int y){
        return find(x)==find(y);
    }

    public int[] findRedundantConnection(int[][] edges) {
        // Initialization
        int N = edges.length;
        int[] res = new int[2];
        parent = new int[N];
        rank = new int[N];
        for(int i=0; i<N; i++) parent[i] = i;
        union(edges[0][0]-1, edges[0][1]-1); // add the first edge to union area
        for(int i=1; i<N; i++){
            if(isConnected(edges[i][0]-1, edges[i][1]-1)){
                res[0] = edges[i][0];
                res[1] = edges[i][1];
                return res;
            }
            else union(edges[i][0]-1, edges[i][1]-1);
        }
        return res;
    }
}
