package _All;

import java.util.*;
/**
 * 最短路径，要求颜色交替
 * */

public class _1129_Shortest_Path_with_Alternating_Colors {
    private List<List<Integer>> redGraph;
    private List<List<Integer>> blueGraph;
    public int[] shortestAlternatingPaths(int n, int[][] red_edges, int[][] blue_edges) {
        //先将graph表示出来
        redGraph = new ArrayList<>();
        blueGraph = new ArrayList<>();
        for(int i=0; i<n; i++){
            redGraph.add(new ArrayList<>());
            blueGraph.add(new ArrayList<>());
        }
        for(int[] red:red_edges) redGraph.get(red[0]).add(red[1]);
        for(int[] blue:blue_edges) blueGraph.get(blue[0]).add(blue[1]);
        int[][] answer = new int[n][2];

        for(int i=1; i<n; i++){
            //初始化所有距离为max,出发点为0
            answer[i][0] = Integer.MAX_VALUE;
            answer[i][1] = Integer.MAX_VALUE;
        }
        dfs(answer, 0, 0);//从红线出发
        dfs(answer, 1, 0);
        int[] res = new int[n];
        for(int i=0; i<n; i++){
            if(answer[i][0]==Integer.MAX_VALUE && answer[i][1]==Integer.MAX_VALUE) res[i]=-1;
            else res[i] = Math.min(answer[i][0], answer[i][1]);
        }
        return res;

    }

    private void dfs(int[][] answer, int color, int i){
        // color表示从红色或者蓝色出发，color=0表示红线，color=1表示蓝线
        // i表示当前node
        List<List<Integer>> graph = color==0?redGraph:blueGraph;
        for(int j:graph.get(i)){
            if(answer[i][color]+1<answer[j][Math.abs(color-1)]){
                //更新
                answer[j][Math.abs(color-1)] = answer[i][color]+1;
                dfs(answer, Math.abs(color-1), j);
            }
        }
    }
}
