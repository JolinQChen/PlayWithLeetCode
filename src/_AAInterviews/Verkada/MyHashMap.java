package _AAInterviews.Verkada;
import java.util.*;
public class MyHashMap {
    class Pair {
        int key;
        int val;
        public Pair(int k, int v) {
            this.key = k;
            this.val = v;
        }
    }
    int len = 1000;
    List<Pair>[] map;
    public MyHashMap() {
        map = new List[len];
    }

    private int findBucket(int key){
        return key%len;
    }

    public void put(int key, int value) {
        int idx = findBucket(key);
        if(get(key)>-1) {
            List<Pair> bucket = map[idx];
            for(Pair p:bucket) {
                if(p.key==key) {
                    p.val = value;
                    return;
                }
            }
        } else {
            if(map[idx]==null) map[idx] = new LinkedList<>();
            map[idx].add(new Pair(key, value));
        }
    }

    public int get(int key) {
        int idx = findBucket(key);
        if(map[idx]==null || map[idx].size()==0) return -1;
        for(Pair p:map[idx]) {
            if(p.key == key) return p.val;
        }
        return -1;
    }

    public void remove(int key) {
        int idx = findBucket(key);
        if(map[idx]==null || map[idx].size()==0) return;
        for(Pair p:map[idx]) {
            if(p.key==key) {
                map[idx].remove(p);
                return;
            }
        }
    }
}
