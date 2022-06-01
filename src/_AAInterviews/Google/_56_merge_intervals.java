package _AAInterviews.Google;

import java.util.*;

public class _56_merge_intervals {
    public int[][] merge(int[][] intervals) {
        if(intervals.length<=1) return intervals;
        Arrays.sort(intervals, (a,b)-> a[0]-b[0]==0 ? a[1]-b[1] : a[0]-b[0]);
        List<int[]> merged = new ArrayList<>();
        for(int i=0; i<intervals.length; i++) {
            int[] cur = intervals[i];
            if(merged.size()==0 || cur[0]>merged.get(merged.size()-1)[1]) {
                // no overlap
                merged.add(cur);
            }
            else {
                merged.get(merged.size()-1)[1] = Math.max(cur[1], merged.get(merged.size()-1)[1]);
            }
        }
        int[][] res = new int[merged.size()][2];
        for(int i=0; i<merged.size(); i++) res[i] = merged.get(i);
        return res;

    }
}
