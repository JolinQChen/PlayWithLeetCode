package _AAInterviews.Roblox;

public class FirstMaxEvenWord {
    private static String even_word(String str){
        str = str + " "; //append to last place
        int len = str.length();
        int start = -1; // store start index
        int cur_len = 0;
        int max_len = 0;
        for(int i=0; i<len; i++){
            if(str.charAt(i)==' '){
                if(cur_len%2==0) {
                    if(max_len<cur_len) {
                        max_len = cur_len;
                        start = i - cur_len;
                    }
                }
                cur_len = 0;
            }
            else cur_len++;
        }
        if(start == -1) return "";
        return str.substring(start,start+max_len);
    }

    public static void main(String[] args){
        String str = "this is testoh";
        String res = even_word(str);
        System.out.println(res);
    }
}
