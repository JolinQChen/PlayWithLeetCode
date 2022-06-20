package _AAInterviews.Google;
import java.util.*;
class AllOne {
    Map<String, Integer> map;
    TreeMap<Integer, Set<String>> treeMap;
    public AllOne() {
        map = new HashMap<>();
        treeMap = new TreeMap<>();
    }

    public void inc(String key) {
        if(!map.containsKey(key)) {
            map.put(key, 1);
            if(!treeMap.containsKey(1)) treeMap.put(1, new HashSet<>());
            treeMap.get(1).add(key);
        } else {
            int count = map.get(key);
            treeMap.get(count).remove(key);
            if(treeMap.get(count).size()==0) treeMap.remove(count);
            map.put(key, count+1);
            if(!treeMap.containsKey(count+1)) treeMap.put(count+1, new HashSet<>());
            treeMap.get(count+1).add(key);
        }

    }

    public void dec(String key) {
        int count = map.get(key);
        treeMap.get(count).remove(key);
        if(treeMap.get(count).size()==0) treeMap.remove(count);
        if(count==1) map.remove(key);
        else {
            map.put(key, count - 1);
            if(!treeMap.containsKey(count-1)) treeMap.put(count-1, new HashSet<>());
            treeMap.get(count-1).add(key);
        }

    }

    public String getMaxKey() {
        if(treeMap.size()==0) return "";
        Set<String> res = treeMap.get(treeMap.lastKey());
        for(String str:res) return str;
        return "";
    }

    public String getMinKey() {
        if(treeMap.size()==0) return "";
        Set<String> res = treeMap.get(treeMap.firstKey());
        for(String str:res) return str;
        return "";
    }
}
