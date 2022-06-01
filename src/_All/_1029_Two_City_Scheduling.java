package _All;
import java.util.*;
public class _1029_Two_City_Scheduling {
    public int twoCitySchedCost(int[][] costs) {
        int cost = 0;
        List<Integer> toA = new ArrayList<>();
        List<Integer> toB = new ArrayList<>();
        for(int i=0; i<costs.length; i++) {
        	if(costs[i][0]<=costs[i][1]) {
        		cost += costs[i][0];
        		toA.add(i);
        	} else {
        		cost += costs[i][1];
        		toB.add(i);
        	}
        }

        if(toA.size()==toB.size()) return cost;
        if(toA.size()>toB.size()) {
        	Collections.sort(toA, (Integer i, Integer j) -> ((costs[i][1]-costs[i][0]) - (costs[j][1]-costs[j][0])));
        	int ds = (toA.size() - toB.size())/2;
        	for (int i=0; i<ds; i++) {
        		int idx = toA.get(i);
        		cost = cost + costs[idx][1] - costs[idx][0];
        	}
        } else {
        	Collections.sort(toB, (Integer i, Integer j) -> ((costs[i][0]-costs[i][1]) - (costs[j][0]-costs[j][1])));
			int ds = (toB.size() - toA.size())/2;
			for (int i=0; i<ds; i++) {
        		int idx = toB.get(i);
        		cost = cost + costs[idx][0] - costs[idx][1];
        	}
        }
        return cost;
    }
}
