package _All;
import java.util.*;
public class _1834_Single_Threaded_CPU {
    public int[] getOrder(int[][] tasks) {
        int n = tasks.length;
        int[][] tasksReorg = new int[n][3];
        for(int i=0; i<n; i++) {
            tasksReorg[i][0] = tasks[i][0];
            tasksReorg[i][1] = tasks[i][1];
            tasksReorg[i][2] = i;
        }
        Arrays.sort(tasksReorg, Comparator.comparingInt(task -> task[0]));
        // create a heap, sort first by processing time, then by index
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> (a[1]==b[1] ? a[2]-b[2] : a[1]-b[1]));

        int CPUTimePointer = 0;
        int[] res = new int[n];
        int idx = 0;
        int i=0;
        while(idx<n) {
            // nothing in pq, time point to prev task's end or next task's start
            if(pq.isEmpty()) CPUTimePointer = Math.max(CPUTimePointer, tasksReorg[i][0]);
            // add all the skipped tasks into pq
            while (i<n && tasksReorg[i][0]<=CPUTimePointer) pq.offer(tasksReorg[i++]);
            // execute next task picked from pq
            int[] cur = pq.poll();
            res[idx++] = cur[2];
            CPUTimePointer += cur[1];

        }
        return res;
    }
}
