package Design;
import java.util.*;
public class _729_My_Calendar_I {
    class MyCalendar_BruteForce {
        //暴力法
        private List<int[]> calendar;
        public MyCalendar_BruteForce() {
            calendar = new ArrayList<>();
        }

        public boolean book(int start, int end) {
            for(int[] arr:calendar) {
                if(arr[0]<end && arr[1]>start) return false;

            }
            calendar.add(new int[]{start,end});
            return true;
        }
    }
    class MyCalendar_BalancedTree{
        //平衡树
        private TreeMap<Integer, Integer> calendar;
        public MyCalendar_BalancedTree() {
            calendar = new TreeMap<>();
        }

        public boolean book(int start, int end) {
            Integer prev = calendar.floorKey(start);
            Integer next = calendar.ceilingKey(start);
            if((prev==null || calendar.get(prev)<=start) && (next==null || next>=end)){
                calendar.put(start,end);
                return true;
            }
            return false;
        }
    }

}
