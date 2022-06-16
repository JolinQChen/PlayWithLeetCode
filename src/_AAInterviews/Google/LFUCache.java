package _AAInterviews.Google;
import java.util.*;
class LFUCache {
    int capacity;
    Map<Integer, Integer> findNodeMap; // (key -> val)
    Map<Integer, Integer> frequencyMap; // (key -> frequency)
    TreeMap<Integer, Deque<Integer>> frequencyNodeMap; // (frequency -> list of keys)
    int counter;
    public LFUCache(int capacity) {
        this.capacity = capacity;
        findNodeMap = new HashMap<>();
        frequencyMap = new HashMap<>();
        frequencyNodeMap = new TreeMap<>();
        counter = 0;
    }

    public int get(int key) {
        if(!findNodeMap.containsKey(key)) return -1;
        int res = findNodeMap.get(key);
        int freq = frequencyMap.get(key);
        frequencyMap.put(key, freq+1);
        // System.out.println("get key: "+key);
        frequencyNodeMap.get(freq).remove(key);
        if(frequencyNodeMap.get(freq).isEmpty()) frequencyNodeMap.remove(freq);
        if(!frequencyNodeMap.containsKey(freq+1)) frequencyNodeMap.put(freq+1, new LinkedList<>());
        frequencyNodeMap.get(freq+1).addLast(key);

        return res;

    }

    public void put(int key, int value) {
        if(capacity==0) return;
        // System.out.println("key: "+key+", value: "+value);
        if(findNodeMap.containsKey(key)) {
            // update
            findNodeMap.put(key, value);
            int freq = frequencyMap.get(key);
            frequencyMap.put(key, freq+1);
            frequencyNodeMap.get(freq).remove(key);
            if(frequencyNodeMap.get(freq).isEmpty()) frequencyNodeMap.remove(freq);
            if(!frequencyNodeMap.containsKey(freq+1)) frequencyNodeMap.put(freq+1, new LinkedList<>());
            frequencyNodeMap.get(freq+1).addLast(key);
        } else {

            if(counter==capacity) {
                counter--;
                // remove LFU
                int leastFreq = frequencyNodeMap.firstKey();
                int removeKey = frequencyNodeMap.get(leastFreq).pollFirst();
                if(frequencyNodeMap.get(leastFreq).isEmpty()) frequencyNodeMap.remove(leastFreq);
                frequencyMap.remove(removeKey);
                findNodeMap.remove(removeKey);
            }
            // add new entry
            counter++;
            findNodeMap.put(key, value);
            frequencyMap.put(key,1);
            if(!frequencyNodeMap.containsKey(1)) frequencyNodeMap.put(1, new LinkedList<>());
            frequencyNodeMap.get(1).addLast(key);

        }
    }

}

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
