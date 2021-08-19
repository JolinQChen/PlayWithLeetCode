package _All;

public class _32_Longest_Valid_Parentheses {
    public int longestValidParentheses(String s) {
        /** DP
         * DP[i] means the longest valid substring ends at i position
         * */
        int res = 0;
        char[] ss = s.toCharArray();
        if(ss.length<2) return 0;
        int[] dp = new int[ss.length];
        dp[0] = 0;
        if(ss[0]=='(' && ss[1]==')') {
            dp[1] = 2;
            res = 2;
        }
        else dp[1] = 0;
        for(int i=2; i<ss.length; i++) {
            if(ss[i]=='(') dp[i] = 0;
            else {
                // ss[i] == ')'
                if(dp[i-1]==0) {
                    if(ss[i-1]=='(') dp[i] = dp[i-2]+2;
                    else dp[i]=0;
                }
                else {
                    if(i-dp[i-1]-1>=0 && ss[i-dp[i-1]-1] == '(') {
                        dp[i] = dp[i-1]+2;
                        if(i-dp[i-1]-2>=0) dp[i] += dp[i-dp[i-1]-2];
                    }
                    else {
                        dp[i]=0;
                    }

                }
            }
            res = Math.max(res,dp[i]);
        }
        return res;
    }
}
