package _Trie;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class _2135_Count_Words_Obtained {
	public int wordCount(String[] startWords, String[] targetWords) {
        String[] startWords_sorted = new String[startWords.length];
        String[] targetWords_sorted = new String[targetWords.length];
        for(int i=0; i<startWords.length; i++) {
        	startWords_sorted[i] = sortString(startWords[i]);
        }  
        for(int i=0; i<targetWords.length; i++) {
        	targetWords_sorted[i] = sortString(targetWords[i]);
        }
        Set<String> startSet = new HashSet(Arrays.asList(startWords_sorted));
        int res = 0;
        for(String target:targetWords_sorted) {
        	if(findPrev(target, startSet)) res++;
        }
        return res;
    }

    private String sortString(String str) {
    	char[] c = str.toCharArray();
    	Arrays.sort(c);
    	StringBuilder sb = new StringBuilder();
    	for(char cc:c) sb.append(cc);
    	return sb.toString();
    }

    private boolean findPrev(String target, Set<String> startSet) {
    	char[] c = target.toCharArray();
    	int idx =  0;
    	while(idx<c.length) {
    		if(idx<c.length-1 && c[idx]==c[idx+1] || (idx>0 && c[idx]==c[idx-1])) idx++;
    		else {
    			String tmp = target.substring(0,idx)+target.substring(idx+1, c.length);
    			if(startSet.contains(tmp)) return true;
    		}
    	}
    	return false;
    }
}
