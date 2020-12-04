package _AAInterviews.Amazon;

import java.util.*;
public class CriticalConnections {
    // 本题找桥边
    private int timestamp = 0;
    private int[] parent;
    private int[] low; // 最早访问到x相连点的时间点
    private int[] dfn; // 访问到x的时间点
    private Map<Integer, List<Integer>> map;
    private List<List<Integer>> res;

    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        res = new ArrayList<>();
        map = new HashMap<>();
        parent = new int[n];
        Arrays.fill(parent,-1);
        low = new int[n];
        dfn = new int[n];
        // construct graph
        for(List<Integer> connection:connections) {
            int a = connection.get(0);
            int b = connection.get(1);
            if(!map.containsKey(a)) map.put(a,new ArrayList<>());
            if(!map.containsKey(b)) map.put(b, new ArrayList<>());
            map.get(a).add(b);
            map.get(b).add(a);
        }

        // begin dfs
        for(int i=0; i<n; i++) {
            if(dfn[i]==0) dfs(i);
        }
        return res;

    }

    private void dfs(int x) {
        // 对x进行dfs
        timestamp++;
        dfn[x] = low[x] = timestamp;
        for(int y:map.get(x)) {
            if(dfn[y]==0) {
                // y没有被访问过,y是x的child
                parent[y] = x;
                dfs(y);
                // 开始判定
                if(low[y]>dfn[x]) {
                    // 满足桥的要求
                    List<Integer> list = new ArrayList<>();
                    list.add(x);
                    list.add(y);
                    res.add(list);
                }
                // 更新父节点的low
                low[x] = Math.min(low[x],low[y]);
            }
            else if(parent[x]!=y) {
                // y已经被访问，但是不是x的parent
                low[x] = Math.min(low[x],low[y]);
            }
        }

    }
}
