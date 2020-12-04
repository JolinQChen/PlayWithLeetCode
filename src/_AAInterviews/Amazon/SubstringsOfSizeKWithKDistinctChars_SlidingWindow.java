package _AAInterviews.Amazon;// sliding window
import java.util.*;
public class SubstringsOfSizeKWithKDistinctChars_SlidingWindow {
    private static List<String> Count(String S,int k){
        int distinct=0,idx=0;
        int [] memo=new int[26];
        int len = S.length();
        Set<String> set = new HashSet<>();
        for (;idx<k;idx++){
            if (memo[S.charAt(idx)-'a']==0)
                distinct+=1;
            memo[S.charAt(idx)-'a']+=1;
        }
        if(distinct == k) set.add(S.substring(0,idx));
        while (idx<len) {
            if(memo[S.charAt(idx)-'a'] == 0) distinct++;
            memo[S.charAt(idx)-'a']++;
            memo[S.charAt(idx-k)-'a']-=1;
            if(memo[S.charAt(idx-k)-'a']==0) distinct--;
            ++idx;
            if(distinct == k) set.add(S.substring(idx-k,idx));
        }

        return new ArrayList<>(set);
    }

    public static void main(String[] args) {
        String S = "awaglknagawunagwkwagl";
        int k = 4;
        List<String> res = Count(S,k);
        for(String str:res) System.out.println(str);
        System.out.println(res.size());
    }
}
