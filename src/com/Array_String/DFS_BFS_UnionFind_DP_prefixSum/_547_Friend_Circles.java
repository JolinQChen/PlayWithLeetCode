package com.Array_String.DFS_BFS_UnionFind_DP_prefixSum;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 班上有 N 名学生。其中有些人是朋友，有些则不是。他们的友谊具有是传递性。如果已知 A 是 B 的朋友，B 是 C 的朋友，
 * 那么我们可以认为 A 也是 C 的朋友。所谓的朋友圈，是指所有朋友的集合。
 *
 * 给定一个 N * N 的矩阵 M，表示班级中学生之间的朋友关系。如果M[i][j] = 1，表示已知第 i 个和 j 个学生互为朋友关系，
 * 否则为不知道。你必须输出所有学生中的已知的朋友圈总数。
 *
 * 输入:
 * [[1,1,0],
 *  [1,1,0],
 *  [0,0,1]]
 * 输出: 2
 * 说明：已知学生0和学生1互为朋友，他们在一个朋友圈。
 * 第2个学生自己在一个朋友圈。所以返回2。
 *
 * 输入:
 * [[1,1,0],
 *  [1,1,1],
 *  [0,1,1]]
 * 输出: 1
 * 说明：已知学生0和学生1互为朋友，学生1和学生2互为朋友，所以学生0和学生2也是朋友，所以他们三个在一个朋友圈，返回1。
 *
 * 注意：
 * N 在[1,200]的范围内。
 * 对于所有学生，有M[i][i] = 1。
 * 如果有M[i][j] = 1，则有M[j][i] = 1。
 * */
public class _547_Friend_Circles {
    private int[][] people;
    int n;
    Set<Integer> set;
    public int findCircleNum(int[][] M) {
        set = new HashSet<>();
        this.people = M;
        this.n = M.length;
        if(n<=1) return n;
        int count = 0;
        for(int i=0; i<n; i++){
            if(set.contains(i)) continue;
            for(int j=0; j<n; j++){
                if(people[i][j]==1){
                    count++;
                    disCircle(i);
                    break;
                }
            }
        }
        for(int[] tmp1:people){
            for(int num : tmp1){
                System.out.println(num + " ");
            }
            System.out.println();
        }
        return count;
    }

    private void disCircle(int index){
        if(set.contains(index)) return;
        set.add(index);
        people[index][index] = 0;
        for(int i=0; i<n ; i++){
            //把index对应的所有关系全部消除
            if(!set.contains(i) &&people[index][i]==1){
                people[i][index] = 0;
                people[index][i] = 0;
                disCircle(i);
            }
        }
    }
    /**法2：官方DFS*/

    public void dfs(int[][] M, int[] visited, int i) {
        for (int j = 0; j < M.length; j++) {
            if (M[i][j] == 1 && visited[j] == 0) {
                visited[j] = 1;
                dfs(M, visited, j);
            }
        }
    }
    public int findCircleNum_2(int[][] M) {
        int[] visited = new int[M.length];
        int count = 0;
        for (int i = 0; i < M.length; i++) {
            if (visited[i] == 0) {
                dfs(M, visited, i);
                count++;
            }
        }
        return count;
    }



    /**
     * 法3：并查集 union find
     * 统计图中连通块数量常用方法
     * 初始化有N个同学，每个同学为一个圈子，然后使用并查集将这些同学合并起来。
     * */

    int find(int parent[], int i) {
        if (parent[i] == -1)
            return i;
        return find(parent, parent[i]);
    }

    void union(int parent[], int x, int y) {
        int xset = find(parent, x);
        int yset = find(parent, y);
        if (xset != yset)
            parent[xset] = yset;
    }

    public int findCircleNum_3(int[][] M) {
        //初始化有N个同学，每个同学为一个圈子，然后使用并查集将这些同学合并起来。
        int[] parent = new int[M.length];
        Arrays.fill(parent, -1);
        for (int i = 0; i < M.length; i++) {
            for (int j = 0; j < M.length; j++) {
                if (M[i][j] == 1 && i != j) {
                    union(parent, i, j);
                }
            }
        }
        int count = 0;
        for (int i = 0; i < parent.length; i++) {
            if (parent[i] == -1)
                count++;
        }
        return count;
    }




}
