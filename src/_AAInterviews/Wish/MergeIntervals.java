package _AAInterviews.Wish;

import java.util.*;

/**
 * Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
 * Output: [[1,6],[8,10],[15,18]]
 * Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
 * */
public class MergeIntervals {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[0]!=o2[0]) return o1[0]-o2[0];
                return o1[1]-o2[1];
            }
        });

        List<int[]> res = new ArrayList<>();
        int idx = 0;
        while (idx<intervals.length) {
            int[] tmp = new int[2];
            tmp[0] = intervals[idx][0];
            tmp[1] = intervals[idx][1];
            while (idx<intervals.length-1 && tmp[1]>=intervals[idx+1][0]) {
                tmp[1] = intervals[idx+1][1];
                idx++;
            }
            res.add(tmp);
            idx++;
        }
        int resSize = res.size();
        int[][] ans = new int[resSize][2];
        for(int i=0; i<resSize; i++){
            ans[i][0] = res.get(i)[0];
            ans[i][1] = res.get(i)[1];
        }
        return ans;
    }
}
