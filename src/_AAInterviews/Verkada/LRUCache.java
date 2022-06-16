package _AAInterviews.Verkada;
import netscape.security.UserTarget;

import java.util.*;
public class LRUCache {
//    class Node {
//        int key;
//        int val;
//        public Node(int key, int val) {
//            this.key = key;
//            this.val = val;
//        }
//    }
    Deque<Integer> deque; // store keys
    Map<Integer, Integer> map;
    int capacity;
    public LRUCache(int capacity) {
        this.capacity = capacity;
        deque = new LinkedList<>();
        map= new HashMap<>();
    }

    public int get(int key) {
        // move key to front mode in deque
        if(map.containsKey(key)) {
            Stack<Integer> tmp = new Stack<>();
            while(!deque.isEmpty()) {
                int cur = deque.pollFirst();
                if(cur!=key) tmp.push(cur);
                else break;
            }
            while(!tmp.isEmpty()) {
                deque.addFirst(tmp.pop());
            }
            deque.addFirst(key);
        }
        return map.get(key);
    }

    public void put(int key, int value) {
        if(map.containsKey(key)) {
            Stack<Integer> tmp = new Stack<>();
            while(!deque.isEmpty()) {
                int cur = deque.pollFirst();
                if(cur!=key) tmp.push(cur);
                else break;
            }
            while(!tmp.isEmpty()) {
                deque.addFirst(tmp.pop());
            }
        }
        deque.addFirst(key);
        if(deque.size()>capacity) {
            int remove = deque.pollLast();
            map.remove(remove);
        }
        map.put(key,value);
    }
}
