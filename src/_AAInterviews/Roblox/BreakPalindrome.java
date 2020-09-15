package _AAInterviews.Roblox;

public class BreakPalindrome {
    public String breakPalindrome(String palindrome) {
        if(palindrome.length()<=1) return "";
        char[] c = palindrome.toCharArray();
        for(int i=0; i<c.length/2; i++) {
            if(c[i]=='a') continue;
                // else return palindrome.substring(0,i)+"a"+palindrome.substring(i+1,len);
            else {
                c[i]='a';
                return String.valueOf(c);
            }
        }
        c[c.length-1]='b';
        return String.valueOf(c);
    }
}
