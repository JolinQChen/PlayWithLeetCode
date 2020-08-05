package _AAInterviews.MS;

public class ReverseWordsInAString {
    public String reverseWords(String s) {
        int idx = 0;
        int len = s.length();
        String res = "";
        StringBuilder sb = new StringBuilder();
        while (idx<len) {
            while(idx<len && s.charAt(idx)==' ') idx++;
            sb = new StringBuilder();
            while(idx<len && s.charAt(idx)!=' ') {
                sb.append(s.charAt(idx++));
            }
            if(sb.length()>0) {
                if(res.length()==0) res+=sb.toString();
                else {
                    res =" "+res;
                    res = sb.toString() + res;
                }
            }
        }
        return res;
    }

    private String reverse(String word) {
        int idx = word.length()-1;
        StringBuilder sb = new StringBuilder();
        while (idx>=0) sb.append(word.charAt(idx--));
        return sb.toString();
    }

    public static void main(String[] args){
        ReverseWordsInAString test = new ReverseWordsInAString();
        String str = "the  sky is blue!";
        System.out.println(test.reverseWords(str));
    }
}
