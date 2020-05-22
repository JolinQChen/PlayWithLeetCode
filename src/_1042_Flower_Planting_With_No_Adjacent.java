import sun.awt.image.ImageWatched;

import java.util.*;
/**
 * 简单的染色问题，不用考虑回溯
 *
 *
 * 有 N 个花园，按从 1 到 N 标记。在每个花园中，你打算种下四种花之一。
 *
 * paths[i] = [x, y] 描述了花园 x 到花园 y 的双向路径。
 *
 * 另外，没有花园有 3 条以上的路径可以进入或者离开。
 *
 * 你需要为每个花园选择一种花，使得通过路径相连的任何两个花园中的花的种类互不相同。
 *
 * 以数组形式返回选择的方案作为答案 answer，其中 answer[i] 为在第 (i+1) 个花园中种植的
 * 花的种类。花的种类用  1, 2, 3, 4 表示。保证存在答案。
 *
 * 输入：N = 3, paths = [[1,2],[2,3],[3,1]]
 * 输出：[1,2,3]
 * 1 <= N <= 10000
 * 0 <= paths.size <= 20000
 * 不存在花园有 4 条或者更多路径可以进入或离开。
 * 保证存在答案。
 * */
public class _1042_Flower_Planting_With_No_Adjacent {
    public int[] gardenNoAdj(int N, int[][] paths) {
        int[] res = new int[N];
        //构建图
        List<List<Integer>> graph = new ArrayList<>();
        for(int i=0; i<N; i++) graph.add(new ArrayList<>());
        for(int[] path:paths){
            //注意path中node的编号是从1开始的，这里在graph索引里面我们把它变成0开始
            graph.get(path[0]-1).add(path[1]-1);
            graph.get(path[1]-1).add(path[0]-1);
        }
        for(int i=0; i<N; i++){
            boolean[] used = new boolean[5];
            //查看当前节点的所有相邻节点的颜色
            for(int adj:graph.get(i)){
                used[res[adj]] = true;
            }
            //染色
            for(int j=1; j<=4; j++){
                if(!used[j]) {
                    res[i] = j;
                    break;
                }
            }
        }
        return res;

    }
}
