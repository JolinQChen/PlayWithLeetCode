package _AAInterviews.Google;
import java.util.*;
public class RaceCar {
    public int racecar(int target) {
        // BFS
        Set<int[]> stateSet = new HashSet<>();
        int count = 0;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0,1}); // {pos, speed}
        stateSet.add(queue.peek());
        while(!queue.isEmpty()) {
            int size = queue.size();
            while (size > 0) {
                size--;
                int[] node = queue.poll();
                if(node[0]==target) return count;
                // A
                int[] A = {node[0]+node[1], node[1]*2};
                if(!stateSet.contains(A)) {
                    queue.add(new int[]{node[0]+node[1], node[1]*2});
                    stateSet.add(new int[]{node[0]+node[1], node[1]*2});
                }
                // R

                if(node[1]>0) {
                    int[] R = {node[0], -1};
                    if(!stateSet.contains(R)) {
                        queue.add(new int[]{node[0], -1});
                        stateSet.add(new int[]{node[0], -1});
                    }

                }
                else {
                    int[] R = {node[0], 1};
                    if(!stateSet.contains(R)) {
                        queue.add(new int[]{node[0], 1});
                        stateSet.add(new int[]{node[0], 1});
                    }

                }
            }
            count++;
        }
        return -1;
    }
}
