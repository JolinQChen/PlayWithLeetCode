package _AAInterviews.Citrix.FullTime;

public class DecodeWay {
    // start from 0-25, a-z
    private static int res;
    public static int countInterpretations(String input) {
        res = 0;
        helper(input,0);
        return res;
    }

    public static void helper(String input, int nextPos) {
        if(nextPos == input.length()) res++;
        else {
            helper(input, nextPos+1);
            if(nextPos+1<input.length() && Integer.parseInt(input.substring(nextPos, nextPos+1))!=0 && Integer.parseInt(input.substring(nextPos, nextPos+2))<26)
                helper(input, nextPos+2);
        }
    }

    public static void main(String[] args) {
        String input = "100200300";
        System.out.println(countInterpretations(input));
    }
}
