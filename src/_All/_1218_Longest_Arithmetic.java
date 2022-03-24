package _All;
import java.util.*;
public class _1218_Longest_Arithmetic {
	int maxStep;
    int[] steps;
    Map<Integer, List<Integer>> map;
    public int longestSubsequence(int[] arr, int difference) {
    	maxStep = 0;
        steps = new int[arr.length];
        Arrays.fill(steps, 0);
        map = new HashMap<>();
        for(int i=0; i<arr.length; i++) {
        	if(map.containsKey(arr[i])) {
        		map.get(arr[i]).add(i);
        	} else {
        		List<Integer> list = new ArrayList<>();
        		list.add(i);
        		map.put(arr[i],list);
        	}
        }
        for(int i=0; i<arr.length; i++) generateStep(arr, i, difference);
        
        return maxStep;

    }

    private int generateStep(int[] arr, int curPos, int difference) {
    	// return longest subsequence from current position
    	if(steps[curPos]>0) return steps[curPos];
    	int step = 1;
    	if(map.containsKey(arr[curPos] + difference)) {
    		List<Integer> possibleNext = map.get(arr[curPos]+difference);
    		int maxNextStep = 0;
    		for(Integer next:possibleNext) {
    			if(next>curPos) {
    				maxNextStep = Math.max(maxNextStep, generateStep(arr, next, difference));
    			}
    		}
    		step += maxNextStep;
    		steps[curPos] = step;
    	}
    	maxStep = Math.max(maxStep, step);
    	return step;
    }
}
