package _All;

import java.util.Deque;
import java.util.LinkedList;

public class _362_Design_Hit_Counter {

    /**
     *
     * Design a hit counter which counts the number of hits received in the past 5 minutes.
     * 统计过去5分钟之内的敲击次数
     * Each function accepts a timestamp parameter (in seconds granularity) and you may
     * assume that calls are being made to the system in chronological order (ie, the timestamp
     * is monotonically increasing). You may assume that the earliest timestamp starts at 1.
     *
     * It is possible that several hits arrive roughly at the same time.
     *
     * example:
     * HitCounter counter = new HitCounter();
     * 在这个环境下：_All._362_Design_Hit_Counter counter = new _All._362_Design_Hit_Counter();
     *
     * // hit at timestamp 1.
     * counter.hit(1);
     *
     * // hit at timestamp 2.
     * counter.hit(2);
     *
     * // hit at timestamp 3.
     * counter.hit(3);
     *
     * // get hits at timestamp 4, should return 3.
     * counter.getHits(4);
     *
     * // hit at timestamp 300.
     * counter.hit(300);
     *
     * // get hits at timestamp 300, should return 4.
     * counter.getHits(300);
     *
     * // get hits at timestamp 301, should return 3.
     * counter.getHits(301);
     * */
/**
 * 思路1：双队列deque
 * 思路2：循环数组
 * */



    /** Initialize your data structure here. */
    class Pair<U,V>{
        public U key;
        public V val;

        public Pair(U key, V val) {
            this.key = key;
            this.val = val;
        }
    }

    private Deque<Pair<Integer, Integer>> deque;
    private int count;
    public _362_Design_Hit_Counter() {
        this.count = 0;
        this.deque = new LinkedList<>();
    }

    /** Record a hit.
     @param timestamp - The current timestamp (in seconds granularity). */
    public void hit(int timestamp) {
        if(!deque.isEmpty() && deque.getLast().key==timestamp) {
            int new_val = deque.pollLast().val+1;
            deque.offerLast(new Pair<>(timestamp,new_val));
        }
        else deque.offerLast(new Pair<>(timestamp,1));
        count++;
    }

    /** Return the number of hits in the past 5 minutes.
     @param timestamp - The current timestamp (in seconds granularity). */
    //应该不需要在当前时间保存5分钟之前的hit记录
    public int getHits(int timestamp) {
        while(!deque.isEmpty() && timestamp - deque.getFirst().key + 1 >300){
            count -= deque.pollFirst().val;
        }
        return count;
    }
    /**
     * Your HitCounter object will be instantiated and called as such:
     * HitCounter obj = new HitCounter();
     * obj.hit(timestamp);
     * int param_2 = obj.getHits(timestamp);
     */
}
