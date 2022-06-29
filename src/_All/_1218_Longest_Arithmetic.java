package _All;
import java.util.*;
public class _1218_Longest_Arithmetic {
//	int maxStep;
//    int[] steps;
//    Map<Integer, List<Integer>> map;
//    public int longestSubsequence(int[] arr, int difference) {
//    	maxStep = 0;
//        steps = new int[arr.length];
//        Arrays.fill(steps, 0);
//        map = new HashMap<>();
//        for(int i=0; i<arr.length; i++) {
//        	if(map.containsKey(arr[i])) {
//        		map.get(arr[i]).add(i);
//        	} else {
//        		List<Integer> list = new ArrayList<>();
//        		list.add(i);
//        		map.put(arr[i],list);
//        	}
//        }
//        for(int i=0; i<arr.length; i++) generateStep(arr, i, difference);
//
//        return maxStep;
//
//    }
//
//    private int generateStep(int[] arr, int curPos, int difference) {
//    	// return longest subsequence from current position
//    	if(steps[curPos]>0) return steps[curPos];
//    	int step = 1;
//    	if(map.containsKey(arr[curPos] + difference)) {
//    		List<Integer> possibleNext = map.get(arr[curPos]+difference);
//    		int maxNextStep = 0;
//    		for(Integer next:possibleNext) {
//    			if(next>curPos) {
//    				maxNextStep = Math.max(maxNextStep, generateStep(arr, next, difference));
//    			}
//    		}
//    		step += maxNextStep;
//    		steps[curPos] = step;
//    	}
//    	maxStep = Math.max(maxStep, step);
//    	return step;
//    }

	Map<Integer, TreeSet<Integer>> graphMap;
	int max;
	int[] memoStep;
	public int longestSubsequence(int[] arr, int difference) {
		graphMap = new HashMap<>();

		int n = arr.length;
		max = 0;
		memoStep = new int[n];
		Arrays.fill(memoStep, 0);
		for(int i=0; i<n; i++) {
			if(!graphMap.containsKey(arr[i])) graphMap.put(arr[i], new TreeSet<>());
			graphMap.get(arr[i]).add(i);
		}
		for(int i=0; i<n; i++) {
			if(n-i<=max) break;
			generateStep(arr, i, difference);
			max = Math.max(max, memoStep[i]);
		}
		return max;

	}

	private void generateStep(int[] arr, int pos, int dif) {
		if(memoStep[pos]>0) return;
		if(!graphMap.containsKey(arr[pos]+dif)) {
			memoStep[pos] = 1;
			return;
		}
		Integer next = graphMap.get(arr[pos]+dif).higher(pos);
		if(next==null) {
			memoStep[pos] = 1;
			return;
		}
		else generateStep(arr, next, dif);
		memoStep[pos] = 1+memoStep[next];
	}


}
