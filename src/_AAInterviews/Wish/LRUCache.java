package _AAInterviews.Wish;
import java.util.*;
class LRUCache {

    class DLNode {
        int key;
        int val;
        DLNode next;
        DLNode prev;

    }

    private int capacity;
    private int size;
    private DLNode head;
    private DLNode end;
    private Map<Integer, DLNode> map;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.head = new DLNode();
        this.end = new DLNode();
        head.next = end;
        end.prev = head;
        map = new HashMap<>();
    }


    private void moveToFront(int key) {
        DLNode curr = map.get(key);
        // move curr to front
        curr.prev.next = curr.next;
        curr.next.prev = curr.prev;
        curr.prev = head;
        curr.next = head.next;
        head.next.prev = curr;
        head.next = curr;
    }

    private void addToFront(DLNode node) {
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }



    public int get(int key) {
        if(map.containsKey(key) && map.get(key)!=null) {
            // move to front
            moveToFront(key);
            return map.get(key).val;
        }
        return -1;
    }

    private void removeLast() {
        map.put(end.prev.key, null);
        end.prev = end.prev.prev;
        end.prev.next = end;
    }


    public void put(int key, int value) {

        if(!map.containsKey(key) || map.get(key)==null) {
            DLNode node = new DLNode();
            node.key = key;
            node.val = value;
            size++;
            map.put(key, node);
            addToFront(node);
            if(size>capacity) {
                // remove last
                removeLast();
                size--;
            }
        }
        else {
            DLNode node = map.get(key);
            node.val = value;
            moveToFront(key);
        }


    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
