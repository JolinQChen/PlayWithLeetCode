package _AAInterviews.Amazon;

import java.util.*;
public class WarehouseShipment {
    static private long getMaxUnit(int num, int[] boxes, int[] unitsPerBox, long truckSize){
        Map<Integer, Integer> map = new HashMap<>();

        // {unitsPerBox,boxNumber}
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int i=0; i<num; i++) {
            int n = boxes[i];
            for(int j=0; j<n; j++) {
                pq.add(unitsPerBox[i]);
                if(pq.size()>truckSize) pq.poll();
            }
        }
        int max = 0;
        while (!pq.isEmpty()) {
            max += pq.poll();
        }
        return max;
    }

    public static void main(String[] args) {
        int[] boxes = {5,2,5};
        int[] unitsPerBox = {10,5,6};
        long res = getMaxUnit(3,boxes,unitsPerBox, 7);
        System.out.println(res);
    }
}
