package _All;
import java.util.*;
public class _460_LFU_Cache {

    private class LFUCache {
        /**
        private class Node {
            public int val;
            public int freq;
            public int timeTicket;
            public Node(int val, int freq, int time) {
                this.val = val;
                this.freq = freq;
                this.timeTicket = time;
            }
        }
        private int time;
        private PriorityQueue<Node> pq;
        private Map<Integer, Node> map;
        private int capa;
        private int count;

        public LFUCache(int capacity) {
            count = 0;
            capa = capacity;
            pq = new PriorityQueue<>(
                    new Comparator<Node>() {
                        @Override
                        public int compare(Node o1, Node o2) {
                            if(o1.freq!=o2.freq) return o1.freq-o2.freq;
                            else return o1.timeTicket - o1.timeTicket;
                        }
                    }
            );
            time = 0;
            map = new HashMap<>();
        }

        public int get(int key) {
            time++;
            if(map.containsKey(key) && map.get(key).freq>0) {
                map.get(key).freq = map.get(key).freq+1;
                map.get(key).timeTicket = time;
                return map.get(key).val;
            }
            return -1;

        }

        public void put(int key, int value) {
            time++;
            if(!map.containsKey(key)) {
                count++;
                if(count>capa) {

                    // evict
                    Node evict = pq.poll();
                    System.out.println("evict " + evict.val);
                    evict.freq = 0;
                    pq.add(evict);
                    count--;
                }

                Node node = new Node(value, 1, time);
                map.put(key, node);
                pq.add(node);

            }
            else {
                Node node = map.get(key);
                node.val = value;
                node.freq++;
                node.timeTicket = time;
            }
        }
        */

        class Node{
            private int key;
            private int val;
            private int frequency;
            Node(int key, int val, int frequency){
                this.key = key;
                this.val = val;
                this.frequency = frequency;
            }
        }

        private int minFrequency = Integer.MAX_VALUE;
        private int capacity = 0;
        public LFUCache(int capacity) {
            this.capacity = capacity;
        }

        private Map<Integer, Node> cache2 = new HashMap<>();
        // Use LinkedHashMap as value here to track the insertion (least-recently-used) order.
        private Map<Integer, LinkedHashMap<Integer, Node>> frequencyMap2 = new HashMap<>();

        public int get(int key) {
            Node node = cache2.get(key);
            if(node == null) return -1;

            updateNode(node);
            return node.val;
        }

        public void put(int key, int value) {
            if(capacity <= 0) return;
            Node node = cache2.get(key);
            if(node == null){
                node = new Node(key, value, 0);
            }
            node.val = value;
            updateNode(node);
        }
        private void updateNode(Node node){
            int freq = node.frequency;
            node.frequency++;
            // It's a new node.
            if(freq == 0){
                if(cache2.size() == capacity){
                    Map<Integer, Node> nodes = frequencyMap2.get(minFrequency);
                    Iterator<Integer> it = nodes.keySet().iterator();
                    int k = it.next();
                    // Remove the least frequently and recently used key-value.
                    it.remove();
                    if(nodes.isEmpty()) frequencyMap2.remove(minFrequency);
                    cache2.remove(k);
                }
                minFrequency = 1;
                cache2.put(node.key, node);
            }else{
                Map<Integer, Node> nodes = frequencyMap2.get(freq);
                nodes.remove(node.key);
                if(nodes.isEmpty()){
                    frequencyMap2.remove(freq);
                    if(freq == minFrequency){
                        minFrequency++;
                    }
                }
            }
            frequencyMap2.compute(node.frequency, (k,v) ->{
                if(v == null){
                    v = new LinkedHashMap<>();
                }
                // Re-insert the node into the LinkedHashMap to track the least-recently-used order.
                v.remove(node.key);
                v.put(node.key, node);
                return v;
            });
        }

    }
}
