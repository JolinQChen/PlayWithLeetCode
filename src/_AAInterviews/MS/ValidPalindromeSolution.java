package _AAInterviews.MS;

public class ValidPalindromeSolution {
    public static boolean isPalindrome(String s) {
        String str = s.toLowerCase();
        System.out.println(str);
        char[] c = str.toCharArray();
        int l=0,r=c.length-1;
        while(l<r) {
            while(l<r && !isValid(c[l])) l++;
            while(r>l && !isValid(c[r])) r--;
            if(l<r) {
                if(c[l]==c[r]) {
                    l++;
                    r--;
                    System.out.println("l:" + l + ", r:"+r);
                }
                else {
                    System.out.println(c[l]+" , " +c[r]);
                    System.out.println(l+" , " +r);
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean isValid(char c) {
        if((c>='a' && c<='z') || (c>='A' && c<='Z') || (c>='0' && c<='9')) return true;
        return false;
    }
    public static void main(String[] args) {
        System.out.println(isPalindrome("race a car"));
    }
}
