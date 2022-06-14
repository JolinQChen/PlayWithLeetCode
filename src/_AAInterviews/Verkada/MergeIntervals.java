package _AAInterviews.Verkada;
import java.util.*;
public class MergeIntervals {
    // lc 56
    public int[][] merge(int[][] intervals) {
        if(intervals.length<=1) return intervals;
        List<int[]> res = new ArrayList<>();
        Arrays.sort(intervals, (a,b)->(a[0]==b[0]?a[1]-b[1]:a[0]-b[0]));
        for(int[] interval:intervals) {
            if(res.size()==0 || res.get(res.size()-1)[1]<interval[0]) res.add(interval);
            else res.get(res.size()-1)[1] = Math.max(interval[1], res.get(res.size()-1)[1]);
        }
        int[][] ans = new int[res.size()][2];
        for(int i=0; i<res.size(); i++) {
            ans[i] = res.get(i);
        }
        return ans;
    }
}
