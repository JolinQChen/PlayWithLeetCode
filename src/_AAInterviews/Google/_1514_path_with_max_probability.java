package _AAInterviews.Google;
import java.util.*;
public class _1514_path_with_max_probability {
    // dfs超时
    double maxProb;
    Map<Integer, Set<Integer>> map;
    Map<String, Double> weightMap;
    public double maxProbability_dfs(int n, int[][] edges, double[] succProb, int start, int end) {
        maxProb = 0.0;
        map = new HashMap<>();
        weightMap = new HashMap<>();
        for(int i=0; i<edges.length; i++) {
            if(!map.containsKey(edges[i][0])) map.put(edges[i][0], new HashSet<>());
            map.get(edges[i][0]).add(edges[i][1]);
            if(!map.containsKey(edges[i][1])) map.put(edges[i][1], new HashSet<>());
            map.get(edges[i][1]).add(edges[i][0]);
            String key1 = String.valueOf(edges[i][0]) + "_"+ String.valueOf(edges[i][1]);
            String key2 = String.valueOf(edges[i][1]) + "_"+ String.valueOf(edges[i][0]);
            weightMap.put(key1, succProb[i]);
            weightMap.put(key2, succProb[i]);
        }

        if(!map.containsKey(start) || !map.containsKey(end)) return 0.0;
        dfs(start, end, 1.0, new HashSet<>());
        return maxProb;
    }

    private void dfs(int curNode, int target, double prob, Set<Integer> visited){
        if(prob <= maxProb || !map.containsKey(curNode)) return;
        if(curNode == target) {
            maxProb = prob;
            return;
        }
        Set<Integer> nexts = map.get(curNode);
        visited.add(curNode);
        for(Integer next: nexts) {
            if(!visited.contains(next)) {
                String key = next + "_" + curNode;
                dfs(next, target, prob * weightMap.get(key), visited);
            }
        }
        visited.remove(curNode);
    }

    /**
     * Heap
     * */

    class WeightNode {
        int node;
        double prob;
        public WeightNode(int node, double prob) {
            this.node = node;
            this.prob = prob;
        }
    }
    public double maxProbability(int n, int[][] edges, double[] succProb, int start, int end) {
        double maxProb = 0.0;
        Map<Integer, Set<Integer>>map = new HashMap<>();
        Map<String, Double> weightMap = new HashMap<>();
        Map<Integer, Double> visitedMap = new HashMap<>();
        for(int i=0; i<edges.length; i++) {
            if(!map.containsKey(edges[i][0])) map.put(edges[i][0], new HashSet<>());
            map.get(edges[i][0]).add(edges[i][1]);
            if(!map.containsKey(edges[i][1])) map.put(edges[i][1], new HashSet<>());
            map.get(edges[i][1]).add(edges[i][0]);
            String key1 = String.valueOf(edges[i][0]) + "_"+ String.valueOf(edges[i][1]);
            String key2 = String.valueOf(edges[i][1]) + "_"+ String.valueOf(edges[i][0]);
            weightMap.put(key1, succProb[i]);
            weightMap.put(key2, succProb[i]);
        }
        visitedMap.put(start, 1.0);
        if(!map.containsKey(start) || !map.containsKey(end)) return 0.0;
        PriorityQueue<WeightNode> pq = new PriorityQueue<>((o1,o2)-> {
            if (o1.prob == o2.prob) return 0;
            if(o1.prob-o2.prob>0) return -1;
            return 1;
        });

        pq.add(new WeightNode(start, 1.0));
        while (!pq.isEmpty()) {
            WeightNode node = pq.poll();
            if(node.node==end) return node.prob;
            if(!map.containsKey(node.node)) continue;
            Set<Integer> nexts = map.get(node.node);
            for(Integer next:nexts) {
                double prob = node.prob * weightMap.get(next+"_"+node.node);
                if(!visitedMap.containsKey(next) || visitedMap.get(next)<prob) {
                    pq.add(new WeightNode(next, prob));
                    visitedMap.put(next, prob);
                }
            }
        }
        return 0;
    }
}
