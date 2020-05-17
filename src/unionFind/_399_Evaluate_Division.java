package unionFind;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 给出方程式 A / B = k, 其中 A 和 B 均为代表字符串的变量， k 是一个浮点型数字。根据已知方程式求解问题，并返回计算结果。如果结果不存在，则返回 -1.0。
 *
 * 示例 :
 * 给定 a / b = 2.0, b / c = 3.0
 * 问题: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ? 
 * 返回 [6.0, 0.5, -1.0, 1.0, -1.0 ]
 *
 * equations(方程式) = [ ["a", "b"], ["b", "c"] ],
 * values(方程式结果) = [2.0, 3.0],
 * queries(问题方程式) = [ ["a", "c"], ["b", "a"], ["a", "e"], ["a", "a"], ["x", "x"] ].
 *
 *
 * */

/*
* 用union find先把有联系的两个数字聚类
* 增加一个变量value[]，代表当前节点是直接父节点的多少倍
* */

public class _399_Evaluate_Division {

    class Solution {
        private int[] parent;
        private int[] rank;
        private double[] val; //初始值全部为1
        private Map<String, Integer> map; // 记录String在parent,rank中的对应位置

        private int find(int i){
            if(parent[i]!=i) {
                val[i] *= val[parent[i]];
                parent[i] = find(parent[i]);
            }
            return parent[i];
        }

        private void union(int x, int y, double value){
            int xRoot = find(x);
            int yRoot = find(y);
            if(xRoot == yRoot) return;

            int xRootRank = rank[xRoot];
            int yRootRank = rank[yRoot];

            if(xRootRank<yRootRank) {
                parent[xRoot]=yRoot;
                val[xRoot] = val[y] * value/val[x];
                //val[x] = value * val[y];
            }
            else if(xRootRank>yRootRank) {
                parent[yRoot]=xRoot;
                val[yRoot] = val[x] /(value*val[y]);
                //val[y] = val[x] / value;
            }
            else{
                parent[xRoot]=yRoot;
                val[xRoot] = val[y] * value/val[x];
                //val[x] = value * val[y];
                rank[yRoot]++;
            }
        }

        public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
            int len_query = queries.size();
            int len_equation = values.length;
            if(len_query==0) return null;
            double[] res = new double[len_query];
            Arrays.fill(res, -1.0);
            if(equations==null || equations.size()==0) return res;

            // initialization
            map = new HashMap<>();
            int index = 0;
            for(List<String> list:equations){
                if(!map.containsKey(list.get(0)))
                    map.put(list.get(0),index++);
                if(!map.containsKey(list.get(1)))
                    map.put(list.get(1),index++);
            }
            int n = index;
            parent = new int[n];
            for(int i=0; i<n;i++) parent[i]=i;
            val = new double[n];
            Arrays.fill(val, 1.0);
            rank = new int[n];

            // 遍历equations，图聚类
            for(int i=0; i<len_equation; i++){
                union(map.get(equations.get(i).get(0)), map.get(equations.get(i).get(1)), values[i]);
            }
            // 更新所有的val
            for(int i=0; i<len_equation; i++){
                find(i);
            }

            // 遍历queries，若能find上则有解
            for(int i=0; i<len_query; i++){
                String a = queries.get(i).get(0);
                String b = queries.get(i).get(1);
                if(!map.containsKey(a)||!map.containsKey(b)) continue;
                int a_index = map.get(a);
                int b_index = map.get(b);
                if(find(a_index) == find(b_index)) {
                    // 有解
                    res[i] = val[a_index] / val[b_index];
                }
            }
            for(int i=0; i<n; i++){
                System.out.println(val[i]);
            }
            return res;
        }
    }


}
