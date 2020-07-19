package _All;

public class _91_Decode_Ways {
    // ===== dfs ======有点慢，递归过程中存在大量重复计算
    private int count;
    private String str;
    private int len;
    public int numDecodings_1(String s) {
        count = 0;
        this.str = s;
        this.len = s.length();
        dfs(0);
        return count;
    }

    private void dfs(int start){
        if(start == len){
            count++;
            return;
        }
        if(str.charAt(start) == '0') return;

        int tmp_single =str.charAt(start)-'0';
        if(tmp_single>0) dfs(start+1);
        if(start+1<len){
            int tmp_double = tmp_single * 10 + (str.charAt(start+1)-'0');
            if(tmp_double <= 26 && tmp_double > 0) dfs(start+2);
        }

        return;
    }

    // ======== dfs改进：记忆法，从后往前，动态规划

    public int numDecodings_2(String s){
        if(s == null || s.length() == 0 || s.charAt(0)=='0') return 0;
        int len = s.length();
        int dp[] = new int[len+1];
        dp[len] = 1;
        if(s.charAt(len-1)=='0') dp[len-1]=0;
        else dp[len-1] = 1;


        for(int i=len-2; i>=0; i--) {
            if (s.charAt(i) == '0') dp[i] = 0;
            else {
                int tmp_1 = s.charAt(i) - '0';
                int tmp_2 = tmp_1 * 10 + s.charAt(i + 1) - '0';
                if (tmp_2 <= 26) {
                    dp[i] = dp[i + 1] + dp[i + 2];
                } else dp[i] = dp[i + 1];
            }
        }
        return dp[0];
    }


}
