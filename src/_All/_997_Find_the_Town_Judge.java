package _All;

import java.util.*;
/**
 * In a town, there are N people labelled from 1 to N.  There is a rumor that one of these people is
 * secretly the town judge.
 *
 * If the town judge exists, then:
 *
 * The town judge trusts nobody.
 * Everybody (except for the town judge) trusts the town judge.
 * There is exactly one person that satisfies properties 1 and 2.
 * You are given trust, an array of pairs trust[i] = [a, b] representing that the person labelled a
 * trusts the person labelled b.
 *
 * If the town judge exists and can be identified, return the label of the town judge.  Otherwise, return -1.
 *
 * Input: N = 3, trust = [[1,3],[2,3]]
 * Output: 3
 *
 * Input: N = 3, trust = [[1,3],[2,3],[3,1]]
 * Output: -1
 * */

public class _997_Find_the_Town_Judge {
    public int findJudge(int N, int[][] trust) {
        int res = -1;
        int[][] in_out_degree = new int[N][2];
        for (int[] t:trust){
            in_out_degree[t[1]-1][0] += 1; //入度
            in_out_degree[t[0]-1][1] += 1; //出度
        }
        for(int i=0; i<N; i++){

            if(in_out_degree[i][0]==N-1 && in_out_degree[i][1]==0) {
                res = i+1;
                break;
            }
        }

        return res;
    }
}
