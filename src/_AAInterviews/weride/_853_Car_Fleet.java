package _AAInterviews.weride;
import java.util.*;
public class _853_Car_Fleet {
    public int carFleet(int target, int[] position, int[] speed) {
        Map<Integer, Double> carMap = new HashMap<>();
        PriorityQueue<Integer> pq = new PriorityQueue<>((o1,o2)->(o2-o1));
        for(int i=0; i<position.length; i++) {
            pq.add(position[i]);
            double time = (double) (target-position[i]) / (double) speed[i];
            carMap.put(position[i], time);
        }
        double curMaxTime = -1.0;
        int fleet = 0;
        while (!pq.isEmpty()) {
            int curCar = pq.poll();
            double originalTime = carMap.get(curCar);
            if(originalTime>curMaxTime) {
                fleet++;
                curMaxTime = originalTime;
            }
        }
        return fleet;
    }
    // monotonic stack
    public int carFleet1(int target, int[] position, int[] speed) {
        Map<Integer, Double> carMap = new HashMap<>();
        for(int i=0; i<position.length; i++) {
            double time = (double) (target-position[i]) / (double) speed[i];
            carMap.put(position[i], time);
        }
        Arrays.sort(position);
        double maxTime = -1.0;
        int res = 0;
        for(int i=position.length-1; i>=0; i--) {
            if(carMap.get(position[i])>maxTime) {
                res++;
                maxTime = carMap.get(position[i]);
            }
        }
        return res;
    }
}
