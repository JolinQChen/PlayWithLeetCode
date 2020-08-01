package _All;
// 滚动数组优化
public class _97_Interleaving_String {
    // 法一：超时
    public boolean isInterleave(String s1, String s2, String s3) {
        if(s3.length() != s1.length()+s2.length()) return false;
        return helper(s1, s2, s3);
    }

    private boolean helper(String s1, String s2, String s3) {

        if(s1.length()==0) {
            if(s3.equals(s2)) return true;
            return false;
            }
        if(s2.length()==0) {
            if(s3.equals(s1))return true;
            return false;
            }

        boolean check1 = false;
        boolean check2 = false;
        if(s3.charAt(0) == s1.charAt(0)) {
            check1 = helper(s1.substring(1), s2, s3.substring(1));
            if(check1) return true;
        }
        if(s3.charAt(0) == s2.charAt(0)) {
            check2 = helper(s1, s2.substring(1), s3.substring(1));
            return check2;
        }
        return false;
    }



    // 法二：recursion with memory
    public boolean isInterleave2(String s1, String s2, String s3) {
        int n = s1.length(), m = s2.length(), t = s3.length();

        if (n + m != t) {
            return false;
        }

        boolean[][] f = new boolean[n + 1][m + 1];

        f[0][0] = true;
        for (int i = 0; i <= n; ++i) {
            for (int j = 0; j <= m; ++j) {
                int p = i + j - 1;
                if (i > 0) {
                    f[i][j] = f[i][j] || (f[i - 1][j] && s1.charAt(i - 1) == s3.charAt(p));
                }
                if (j > 0) {
                    f[i][j] = f[i][j] || (f[i][j - 1] && s2.charAt(j - 1) == s3.charAt(p));
                }
            }
        }

        return f[n][m];
    }



    public static void main(String[] args) {
        _97_Interleaving_String test = new _97_Interleaving_String();
        String s1 = "aabcc";
        String s2 = "dbbca";
        String s3 = "aadbbcbcac";
        boolean res = test.isInterleave(s1, s2, s3);
        System.out.println(res);
    }
}
