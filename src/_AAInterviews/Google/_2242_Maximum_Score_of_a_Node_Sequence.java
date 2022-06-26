package _AAInterviews.Google;
import java.util.*;
public class _2242_Maximum_Score_of_a_Node_Sequence {
    public int maximumScore_normal(int[] scores, int[][] edges) {
        Queue<int[]> queue = new LinkedList<>();
        Map<Integer, PriorityQueue<Integer>> map = new HashMap<>();
        for(int[] edge:edges) {
            if(!map.containsKey(edge[0])) map.put(edge[0], new PriorityQueue<>());
            if(!map.containsKey(edge[1])) map.put(edge[1], new PriorityQueue<>());
            map.get(edge[0]).add(edge[1]);
            map.get(edge[1]).add(edge[0]);
            // if(map.get(edge[0]).size()>3) map.get(edge[0]).poll();
            // if(map.get(edge[1]).size()>3) map.get(edge[1]).poll();
        }
        int n = scores.length;
        int len = 1;
        for(int i=0; i<n; i++) {
            int[] tmp = new int[5];
            Arrays.fill(tmp, -1);
            tmp[0] = scores[i];
            tmp[1] = i;
            queue.add(tmp);
        }

        while(len<4) {
            int size = queue.size();
            while(size>0) {
                size--;
                int[] cur = queue.poll();
                if(!map.containsKey(cur[len])) continue;
                PriorityQueue<Integer> nexts = map.get(cur[len]);
                for(Integer next:nexts) {
                    boolean valid = true;
                    for(int i=1; i<=len; i++) {
                        if(next == cur[i]) {
                            valid = false;
                            break;
                        }
                    }
                    if(valid) {
                        int[] pathTmp = Arrays.copyOf(cur, 5);
                        pathTmp[len+1] = next;
                        pathTmp[0] += scores[next];
                        queue.add(pathTmp);
                    }
                }
            }
            len++;
        }

        int max = -1;
        while(!queue.isEmpty()) {
            max = Math.max(queue.poll()[0], max);
        }
        return max;
    }


    /**
     * 巧妙方法：中心发散
     * */
    public int maximumScore(int[] scores, int[][] edges) {
        Map<Integer, PriorityQueue<Integer>> map = new HashMap<>();

        for(int[] edge:edges) {
            if(!map.containsKey(edge[0])) map.put(edge[0], new PriorityQueue<>(Comparator.comparingInt(o -> scores[o])));
            if(!map.containsKey(edge[1])) map.put(edge[1], new PriorityQueue<>(Comparator.comparingInt(o -> scores[o])));
            map.get(edge[0]).add(edge[1]);
            map.get(edge[1]).add(edge[0]);
            if(map.get(edge[0]).size()>3) map.get(edge[0]).poll();
            if(map.get(edge[1]).size()>3) map.get(edge[1]).poll();
        }
        int max = -1;
        for(int[] edge:edges) {
            PriorityQueue<Integer> pq1 = map.get(edge[0]);
            PriorityQueue<Integer> pq2 = map.get(edge[1]);
            for(Integer left:pq1) {
                for(Integer right:pq2) {
                    if(left.equals(right) || left.equals(edge[1]) || right.equals(edge[0])) continue;
                    max = Math.max(max, scores[left]+scores[right]+scores[edge[0]]+scores[edge[1]]);
                    System.out.println(left + ", "+edge[0]+", "+ edge[1] +", " +right);
                }
            }
        }
        return max;
    }
}
