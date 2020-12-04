package _AAInterviews.Amazon;

import java.util.*;
public class KClosestPointsToOrigin {
    public int[][] kClosest(int[][] points, int K) {
        if(K>=points.length) return points;
        int[][] res = new int[K][2];

        PriorityQueue<int[]> pq = new PriorityQueue<>(
                new Comparator<int[]>() {
                    @Override
                    public int compare(int[] o1, int[] o2) {
                        return distance(o2) - distance(o1);
                    }
                }
        );
        for(int i=0; i<points.length; i++) {
            pq.add(points[i]);
            if(pq.size()>K) pq.poll();
        }
        int idx = 0;
        while (pq.size()>0) {
            int[] i = pq.poll();
            res[idx++] = i;
        }
        return res;
    }
    private int distance(int[] point) {
        return point[0] * point[0] + point[1] * point[1];
    }
}
