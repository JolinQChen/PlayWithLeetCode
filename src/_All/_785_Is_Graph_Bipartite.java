package _All;

import java.util.*;
/**
 *
 * 着色问题, 用stack进行DFS
 *
 * 判断二分图
 * 给定一个无向图graph，当这个图为二分图时返回true。
 *
 * 如果我们能将一个图的节点集合分割成两个独立的子集A和B，并使图中的每一条边的两个节点一个来自A集合，
 * 一个来自B集合，我们就将这个图称为二分图。
 *
 * graph将会以邻接表方式给出，graph[i]表示图中与节点i相连的所有节点。每个节点都是一个
 * 在0到graph.length-1之间的整数。这图中没有自环和平行边： graph[i] 中不存在i，
 * 并且graph[i]中没有重复的值。
 *
 * 示例 1:
 * 输入: [[1,3], [0,2], [1,3], [0,2]]
 * 输出: true
 * 解释:
 * 无向图如下:
 * 0----1
 * |    |
 * |    |
 * 3----2
 * 我们可以将节点分成两组: {0, 2} 和 {1, 3}。
 *
 * 示例 2:
 * 输入: [[1,2,3], [0,2], [0,1,3], [0,2]]
 * 输出: false
 * 解释:
 * 无向图如下:
 * 0----1
 * | \  |
 * |  \ |
 * 3----2
 * 我们不能将节点分割成两个独立的子集。
 * */


public class _785_Is_Graph_Bipartite {
    /**
     * 使用数组（或者哈希表）记录每个节点的颜色： color[node]。颜色可以是 -1， 1，或者未着色
     * （-1 或者 null）。
     *
     * 搜索节点时，需要考虑图是非连通的情况。对每个未着色节点，从该节点开始深度优先搜索着色。
     * 每个邻接点都可以通过当前节点着相反的颜色。如果存在当前点和邻接点颜色相同，则着色失败。
     *
     * 使用栈完成深度优先搜索，栈类似于节点的“to do list"，存储着下一个要访问节点的顺序。
     * 在 graph[node] 中，对每个未着色邻接点，着色该节点并将其放入到栈中。
     *
     * */
    public boolean isBipartite(int[][] graph) {
        int n = graph.length;
        int[] color = new int[n];//初始未着色，为0

        for(int i=0; i<n; i++){
            if(color[i]==0){
                //i点还未着色
                Stack<Integer> stack = new Stack<>();
                stack.push(i);
                color[i] = -1;
                while(!stack.isEmpty()){
                    //dfs
                    int node = stack.pop();
                    for(int next:graph[node]){
                        if(color[next]==0){
                            stack.push(next);
                            color[next] = color[node]==-1?1:-1;
                        }
                        else if(color[next] == color[node]) return false;
                    }
                }
            }
        }
        return true;
    }
}
