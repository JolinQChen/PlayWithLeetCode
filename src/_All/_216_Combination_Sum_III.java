package _All;

import java.util.*;
/**
 * Find all possible combinations of k numbers that add up to a number n, given that only numbers from 1 to 9 can be used and each combination should be a unique set of numbers.
 *
 * Note:
 * All numbers will be positive integers.
 * The _All.solution set must not contain duplicate combinations.
 * */

public class _216_Combination_Sum_III {
    private List<List<Integer>> res;
    private int k_;
    private int n_;
    public List<List<Integer>> combinationSum3(int k, int n) {
        res = new LinkedList<>();
        this.k_ = k;
        this.n_ = n;
        //边界条件
        if(k>9) return res;
        int min = 0;
        int max = 0;
        for(int i=1; i<=k; i++){
            min += i;
            max += 10-i;
        }
        if(n<min || n>max) return res;
        //正常条件
        backTracking(new LinkedList<Integer>(),0,0,0);
        return res;

    }

    private void backTracking(List<Integer> cur, int pos, int sum, int min){
        // min表示现有cur中最小值
        if(pos == k_ && sum == n_) {
            res.add(new LinkedList<>(cur));
            return;
        }
        for(int i=min+1; i<=9; i++){
            cur.add(i);
            backTracking(cur, pos+1, sum+i,i);
            cur.remove(cur.size()-1);
        }
    }


}
