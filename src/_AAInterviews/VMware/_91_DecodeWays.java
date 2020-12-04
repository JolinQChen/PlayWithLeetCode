package _AAInterviews.VMware;

public class _91_DecodeWays {
    public int numDecodings(String s){
        // in range 1-26
        // ...123... DP[curr] = DP[curr-1] + DP[curr-2];(if s.substring(curr-1,curr+1) <= 26)
        // ...103... DP[curr] = DP[curr-1]
        // ...120... DP[curr] = DP[curr-2]; ---130-- return 0
        int len = s.length();
        if(len==0) return 0;

        if(s.charAt(0)=='0') return 0;
        if(len == 1) return 1;
        // initialize
        int[] DP = new int[len];
        DP[0] = 1;

        if(Integer.parseInt(s.substring(0,2)) <= 26 ) {
            if(s.charAt(1)!='0') DP[1]=2;
            else DP[1] = 1;
        }
        else if(s.charAt(1)=='0') return 0;
        else DP[1] = 1;

        for(int i=2; i<len; i++) {
            if(s.charAt(i)!='0') {
                if(Integer.parseInt(s.substring(i-1,i+1)) <= 26 && s.charAt(i-1)!='0') DP[i] = DP[i-2]+DP[i-1];
                else DP[i] = DP[i-1];
            }
            else {
                if(Integer.parseInt(s.substring(i-1,i+1))>26 || s.charAt(i-1)=='0') return 0;
                else DP[i] = DP[i-2];
            }
        }
        return DP[len-1];
    }
}
