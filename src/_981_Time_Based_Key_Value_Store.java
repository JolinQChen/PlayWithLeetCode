import java.util.*;

/**
 * 创建一个基于时间的键值存储类 TimeMap，它支持下面两个操作：
 *
 * 1. set(string key, string value, int timestamp)
 * 存储键 key、值 value，以及给定的时间戳 timestamp。
 *
 * 2. get(string key, int timestamp)
 * 返回先前调用 set(key, value, timestamp_prev) 所存储的值，其中 timestamp_prev <= timestamp。
 * 如果有多个这样的值，则返回对应最大的  timestamp_prev 的那个值。
 * 如果没有值，则返回空字符串（""）。
 *
 * */

public class _981_Time_Based_Key_Value_Store {
    /** Initialize your data structure here. */
    private class Pair<U,V>{
        public U timestamp;
        public V val;
        public Pair(U timeStamp, V val){
            this.timestamp = timeStamp;
            this.val = val;
        }
    }

    private Map<String, List<Pair<Integer, String>>> map;
    public _981_Time_Based_Key_Value_Store() {
        map = new HashMap<>();
    }

    public void set(String key, String value, int timestamp) {
        if(!map.containsKey(key)) map.put(key, new ArrayList<>());
        map.get(key).add(new Pair<Integer, String>(timestamp, value));
    }

    public String get(String key, int timestamp) {
        // binary search查找最大stored_timestamp对应的val
        if(!map.containsKey(key)) return "";
        List<Pair<Integer, String>> list = map.get(key);
        return binarySearch(list, timestamp, 0, list.size()-1);
    }

    private String binarySearch(List<Pair<Integer, String>> list, int timestamp, int start, int end){
        if(list.get(end).timestamp<=timestamp) return list.get(end).val;
        if(start>end || list.get(start).timestamp>timestamp) return "";
        int mid = (start+end)/2;
        if(list.get(mid).timestamp>timestamp) return binarySearch(list,timestamp,start,mid-1);
        else if(list.get(mid).timestamp == timestamp) return list.get(mid).val;
        else {
            //要么为mid，要么右边
            if(mid+1>=list.size()||(mid+1<=list.size()-1 && list.get(mid+1).timestamp>timestamp)) return list.get(mid).val;
            else return binarySearch(list, timestamp, mid+1, end);
        }
    }
}
