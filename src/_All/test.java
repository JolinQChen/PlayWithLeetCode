package _All;

import org.junit.Test;

import java.util.Arrays;

public class test {
    private int max;
    public int longestStrChain(String[] words) {
        if(words.length <= 1) return words.length;
        max = 1;
        Arrays.sort(words, (o1, o2) -> o1.length() - o2.length());
        int[] dp = new int[words.length];
        Arrays.fill(dp,1);
        for(int i=1; i<words.length; i++) {
            for(int j=0; j<i; j++) {
                if(words[j].length() == words[i].length()) break;
                if(match(words[j].toCharArray(),words[i].toCharArray())) {
                    dp[i] = Math.max(dp[i], dp[j]+1);
                }
            }
            System.out.println(i+", "+dp[i]);
            max = Math.max(max, dp[i]);
        }
        return max;
    }


    private boolean match(char[] prevStr, char[] nextStr){
        if(nextStr.length != prevStr.length+1) return false;
        // count diff
        int idx = 0;
        while(idx<prevStr.length && prevStr[idx] == nextStr[idx]) {
            idx++;
        }
        if(idx==prevStr.length) return true;
        while(idx<prevStr.length && prevStr[idx]==nextStr[idx+1]) idx++;
        return idx==prevStr.length;
    }

    @Test
    public void test(){
        String[] testStr = new String[]{"ksqvsyq","ks","kss","czvh","zczpzvdhx","zczpzvh","zczpzvhx","zcpzvh","zczvh","gr","grukmj","ksqvsq","gruj","kssq","ksqsq","grukkmj","grukj","zczpzfvdhx","gru"};
        Arrays.sort(testStr, (o1,o2)->o1.length()-o2.length());
//        for (String str:testStr) {
//            System.out.println(str);
//        }
        int res = longestStrChain(testStr);
    }
}
