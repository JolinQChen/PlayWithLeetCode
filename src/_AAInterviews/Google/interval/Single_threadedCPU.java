package _AAInterviews.Google.interval;
import java.util.*;
public class Single_threadedCPU {
    public int[] getOrder(int[][] tasks) {
        int n = tasks.length;
        int[][] sorted = new int[n][3];
        for(int i=0; i<n; i++) {
            sorted[i][0] = tasks[i][0];
            sorted[i][1] = tasks[i][1];
            sorted[i][2] = i;
        }

        Arrays.sort(sorted, Comparator.comparingInt(o -> o[0]));
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1,o2)->(o1[1]==o2[1]?o1[2]-o2[2]:o1[1]-o2[1]));

        int time = 0;
        int[] res = new int[n];
        int count = 0;
        int idx = 0;
        int i_res=0;
        while(count<n) {
            if(pq.isEmpty()) {
                time = Math.max(time,sorted[idx][0]);
            }

            while(idx<n && sorted[idx][0]<=time) pq.add(sorted[idx++]);
            int[] nextToProcess = pq.poll();

            res[i_res++] = nextToProcess[2];
            time += nextToProcess[1];
            count++;

        }
        return res;

    }
}
