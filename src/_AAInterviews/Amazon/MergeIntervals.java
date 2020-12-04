package _AAInterviews.Amazon;

import java.util.*;
public class MergeIntervals {
    public int[][] merge(int[][] intervals) {
        if(intervals == null || intervals.length==0 || intervals.length == 1) return intervals;

        // sort based on begin time
        Arrays.sort(
                intervals,
                new Comparator<int[]>() {
                    public int compare(int[] a, int[] b) {
                        return a[0]-b[0];
                    }
                }
        );
        List<int[]> res = new LinkedList<>();
        int index = 0;
        int len = intervals.length;
        while(index < len) {
            int[] tmp = new int[2];
            tmp[0] = intervals[index][0];
            tmp[1] = intervals[index][1];
            while(index<len-1 && intervals[index+1][0]>=tmp[0] && intervals[index+1][0]<=tmp[1]) {
                tmp[1] = Math.max(tmp[1],intervals[index+1][1]);
                index++;
            }
            res.add(tmp);
            index++;

        }
        int resLen = res.size();
        int[][] ans = new int[resLen][2];
        for(int i=0; i<resLen; i++) {
            ans[i][0] = res.get(i)[0];
            ans[i][1] = res.get(i)[1];
        }
        return ans;

    }
}
