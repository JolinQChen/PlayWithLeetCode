package _AAInterviews.Verkada;
import java.util.*;
public class _435_Non_overlapping_Intervals {
    public int eraseOverlapIntervals(int[][] intervals) {
        if(intervals.length<=1) return 0;
        // 谁的脚伸的远erase谁
        Arrays.sort(intervals, (o1,o2)->(o1[0]==o2[0]?o1[1]-o2[1]:o1[0]-o2[0]));
        List<int[]> list = new ArrayList<>();
        list.add(intervals[0]);
        for(int i=1; i<intervals.length; i++) {
            int[] interval = intervals[i];
            if(interval[0]>=list.get(list.size()-1)[1]) list.add(interval);
            else {
                if (interval[1] < list.get(list.size() - 1)[1]) {
                    list.remove(list.size() - 1);
                    list.add(interval);
                }
            }
        }
        return intervals.length - list.size();

    }
}
