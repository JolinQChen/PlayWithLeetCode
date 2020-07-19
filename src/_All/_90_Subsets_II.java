package _All;

import java.util.*;
public class _90_Subsets_II {
    //用回溯法试试？
    private List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        if(nums.length == 0) return res;
        List<Integer> tmp = new ArrayList<Integer>();
        dfs(tmp, 0,nums);
        return res;
    }

    private void dfs(List<Integer> cur, int start, int[] nums){
        res.add(new ArrayList<>(cur));
        for(int i=start;i<nums.length;i++){
            if(i>start && nums[i]==nums[i-1]) continue; //去重
            cur.add(nums[i]);
            dfs(cur,i+1, nums);
            cur.remove(cur.size()-1);
        }
    }
}
