package _AAInterviews.Verkada;
import java.util.*;
public class MinMeetingRoom {
    public int minMeetingRooms(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> {
            if (a[0] != b[0]) {
                return a[0] - b[0];
            } else {
                return a[1] - b[1];
            }
        });
        PriorityQueue<Integer> endPq = new PriorityQueue<>();
        int res = 0;
        for (int[] interval : intervals) {
            int start = interval[0], end = interval[1];
            while (!endPq.isEmpty() && endPq.peek() <= start) {
                endPq.poll();
            }
            endPq.offer(end);
            res = Math.max(res, endPq.size());
        }
        return res;
    }
}
