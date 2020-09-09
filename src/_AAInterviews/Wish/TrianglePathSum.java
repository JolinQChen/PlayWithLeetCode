package _AAInterviews.Wish;

import java.util.*;
public class TrianglePathSum {
    public int minimumTotal(List<List<Integer>> triangle) {
        List<List<Integer>> dp = new ArrayList<>();
        int levels = triangle.size();
        if(levels==0) return 0;
        if(levels == 1) return triangle.get(0).get(0);
        for(int i=0; i<levels; i++) dp.add(new ArrayList<>());
        for(int i=0; i<triangle.get(levels-1).size(); i++) dp.get(levels-1).add(triangle.get(levels-1).get(i));
        for(int i=levels-2; i>=0 ;i--){
            int len = triangle.get(i).size();
            for(int j=0; j<len; j++){
                dp.get(i).add(triangle.get(i).get(j) + Math.min(dp.get(i+1).get(j),dp.get(i+1).get(j+1)));
            }
        }
        return dp.get(0).get(0);
    }
}
