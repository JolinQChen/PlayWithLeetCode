package _AAInterviews.VMware;

import sun.java2d.pipe.SpanIterator;

import java.awt.image.ImageProducer;
import java.util.*;

public class LRUCache {

    class Node {
        Node pre;
        Node next;
        int key;
        int value;
        public Node(int k, int v){}
        public Node(){}
    }


    class Pair {
        int key;
        int value;
        public Pair(int k, int v) {
            key = k;
            value = v;
        }
    }

    public int capacity;
    private Deque<Pair> deque;
    Map<Integer, Node> map;

    public LRUCache(int capacity) {
        map = new HashMap<>();
        Node head = new Node();
        Node end = new Node();

        this.capacity = capacity;
        deque = new LinkedList<>();

    }
    public void moveToLast(Node node){}
    public void removeFirst(Node node){}

    public int get(int key) {
        List<Pair> list = new ArrayList<>();
        int res = -1;
        Pair p = null;
        while (!deque.isEmpty()) {
            Pair tmp = deque.pollFirst();
            if(tmp.key!=key) list.add(tmp);
            else {
                p = tmp;
                res = tmp.value;
            }
        }
        int len = list.size();
        for(int i=len-1; i>=0; i--) {
            deque.addFirst(list.get(i));
        }
        if(p!=null) deque.addLast(p);

        return res;

    }

    public void put(int key, int value){
        List<Pair> list = new ArrayList<>();
        while (!deque.isEmpty()) {
            Pair tmp = deque.pollFirst();
            if(tmp.key!=key) list.add(tmp);
            else {
                tmp.value = value;
                int len = list.size();
                for(int i=len-1; i>=0; i--) {
                    deque.addFirst(list.get(i));
                }
                deque.addLast(tmp);
                return;
            }
        }
        int len = list.size();
        for(int i=len-1; i>=0; i--) {
            deque.addFirst(list.get(i));
        }

        deque.addLast(new Pair(key,value));
        if(deque.size()>capacity) {
            deque.pollFirst();
        }
    }

}
