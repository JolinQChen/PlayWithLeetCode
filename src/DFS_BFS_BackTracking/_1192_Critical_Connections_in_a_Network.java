package DFS_BFS_BackTracking;

import java.util.*;

/**There are n servers numbered from 0 to n-1 connected by undirected server-to-server connections
 * forming a network where connections[i] = [a, b] represents a connection between servers a and b.
 * Any server can reach any other server directly or indirectly through the network.
 *
 * A critical connection is a connection that, if removed, will make some server unable to reach
 * some other server.
 *
 * Return all critical connections in the network in any order.
 *
 * Example:
 * Input: n = 4, connections = [[0,1],[1,2],[2,0],[1,3]]
 * Output: [[1,3]]
 * Explanation: [[3,1]] is also accepted.
 * */

/** 经典的Trajan算法 */

public class _1192_Critical_Connections_in_a_Network {

    private int times;
    private int[] parent;
    private Map<Integer, List<Integer>> graphMap; //Map表示graph: NodeIndex -> List (following nodes)
    private int[] low;
    private int[] dfn;
    private List<List<Integer>> res; //添加最终结果


    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        /** Initialization */
        times = 1;
        parent = new int[n];
        Arrays.fill(parent,-1);
        low = new int[n];
        dfn = new int[n];
        res = new LinkedList<>();

        /** construct graph*/
        graphMap = new HashMap<>();
        for(List<Integer> list: connections){
            List<Integer> tmp0 = graphMap.getOrDefault(list.get(0),new LinkedList<>());
            List<Integer> tmp1 = graphMap.getOrDefault(list.get(1),new LinkedList<>());
            tmp0.add(list.get(1));
            tmp1.add(list.get(0));
            graphMap.put(list.get(0), tmp0);
            graphMap.put(list.get(1),tmp1);
        }

        /** begin dfs */
        for(int i=0; i<n; i++){
            if(dfn[i]==0){
                dfs(i);
            }
        }
        return res;

    }

    public void dfs(int x){
        // 对x node进行dfs遍历并判定桥，将最终判定结果加入res中
        dfn[x] = low[x] = times++;
        List<Integer> list = graphMap.get(x);
        for(int y:list){
            if(dfn[y]==0){
                //y并没有被访问过
                parent[y] = x;
                dfs(y);
                //开始判定
                if(low[y]>dfn[x]){
                    //满足桥的要求
                    List<Integer> tmp = new LinkedList<>();
                    tmp.add(x);
                    tmp.add(y);
                    res.add(tmp);
                }
                low[x] = Math.min(low[x],low[y]);//更新父节点的low
            }
            else if(y!=parent[x]){
                //y已经被访问过，但是y不是x的father
                low[x] = Math.min(low[y],low[x]); //更新
            }
        }
    }
    /*
    public static void main(String[] args){

        List<List<Integer>> input = new LinkedList<>();
        int n=4;
        List<Integer> input1 = new LinkedList<>();
        input1.add(0);input1.add(1);
        input.add(input1);

        List<Integer> input2 = new LinkedList<>();
        input2.add(1);input2.add(2);
        input.add(input2);

        List<Integer> input3 = new LinkedList<>();
        input3.add(2);input3.add(0);
        input.add(input3);

        List<Integer> input4 = new LinkedList<>();
        input4.add(1);input4.add(3);
        input.add(input4);

        _1192_Critical_Connections_in_a_Network test = new _1192_Critical_Connections_in_a_Network();
        List<List<Integer>> test_res = test.criticalConnections(n, input);
        for(List<Integer> list:test_res){
            for(int t:list) System.out.print(t + "\t");
            System.out.println();
        }
    }
    */
}
