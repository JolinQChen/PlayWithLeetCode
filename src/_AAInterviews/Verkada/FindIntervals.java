package _AAInterviews.Verkada;

import org.junit.Test;

import java.util.*;
public class FindIntervals {
    public List<int[]> findIntervalsWithOddPeopleAlive(int[][] peopleLives){
        int livePeople;
        List<int[]> res = new ArrayList<>();
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        Map<Integer, Integer> map = new HashMap<>();
        for(int[] people:peopleLives) {
            pq.add(people[0]);
            pq.add(people[1]);
            map.put(people[0], map.getOrDefault(people[0],0)+1);
            map.put(people[1], map.getOrDefault(people[1],0)-1);
        }
        int left = pq.poll();
        int right = left;
        int curPeople = map.get(left);
        while(!pq.isEmpty()) {
            right = pq.poll();
            int nextPeople = map.get(right)+curPeople;
            if(curPeople%2==1 && nextPeople%2==0) {
                res.add(new int[]{left, right - 1});
                left = right;
                curPeople = nextPeople;
            } else if(curPeople%2==0) {
                left = right;
                curPeople = nextPeople;
            } else {
                curPeople = nextPeople;
            }

        }
        return res;
    }
    @Test
    public void test(){
        int[][] peopleLives = new int[][]{{1,6},{3,15},{4,22},{8,20}};
        List<int[]> res = findIntervalsWithOddPeopleAlive(peopleLives);
        for(int[] i:res) {
            System.out.println(i[0]+", "+i[1]);
        }

    }
}
