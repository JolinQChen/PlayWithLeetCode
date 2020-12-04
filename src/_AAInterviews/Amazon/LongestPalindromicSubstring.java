package _AAInterviews.Amazon;

public class LongestPalindromicSubstring {
    public String longestPalindrome(String s) {
        String res = "";
        int len = s.length();
        if(len<=1) return s;
        for(int i=0; i<len; i++) {
            String tmp1 = longestByCenter(s,i,i);
            String tmp2 = longestByCenter(s,i,i+1);
            res = res.length()<tmp1.length()?tmp1:res;
            res = res.length()<tmp2.length()?tmp2:res;
        }
        return res;
    }

    public String longestByCenter(String s, int start, int end) {
        int len = s.length();
        while (end<len && start>=0 && s.charAt(end) == s.charAt(start)) {

            start--;
            end++;

        }
        return s.substring(start+1, end);
    }
}
