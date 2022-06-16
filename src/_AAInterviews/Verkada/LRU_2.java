package _AAInterviews.Verkada;
import java.util.*;
class LRUCache_2 {
    TreeMap<Integer, Integer> timeMap;
    Map<Integer, Integer> map;
    Map<Integer, Integer> getTimeMap;
    int capacity;
    int count;
    int timestamp;
    public LRUCache_2(int capacity) {
        this.capacity = capacity;
        timeMap = new TreeMap<>();
        map = new HashMap<>();
        getTimeMap = new HashMap<>();
        timestamp = 0;
        count = 0;
    }

    public int get(int key) {
        if(!map.containsKey(key)) return -1;
        int preTime = getTimeMap.get(key);
        getTimeMap.put(key, timestamp);
        timeMap.remove(preTime);
        timeMap.put(timestamp, key);
        timestamp++;
        return map.get(key);
    }

    public void put(int key, int value) {
        if(!map.containsKey(key)) count++;

        else{
            int preTime = getTimeMap.get(key);
            timeMap.remove(preTime);
        }
        map.put(key,value);
        getTimeMap.put(key, timestamp);
        timeMap.put(timestamp, key);
        timestamp++;
        if(count>capacity) {
            // remove
            int k = timeMap.get(timeMap.firstKey());
            map.remove(k);
            getTimeMap.remove(k);
            timeMap.remove(timeMap.firstKey());
            count--;
        }
    }
}

