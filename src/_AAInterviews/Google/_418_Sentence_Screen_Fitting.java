package _AAInterviews.Google;

import org.junit.Test;

public class _418_Sentence_Screen_Fitting {
    public int wordsTyping(String[] sentence, int rows, int cols) {
        String str = String.join(" ",sentence)+" ";
        int nextChar = 0;
        int count = 0;
        for(int i=0; i<rows; i++) {
            nextChar = nextChar + cols;
            System.out.println(nextChar);
            count += nextChar/str.length();
            nextChar = nextChar % str.length();
            if(str.charAt(nextChar)==' ') {
                nextChar++;
            } else if(nextChar>0 && str.charAt(nextChar-1)!=' ') {
                while (nextChar-1>=0 && str.charAt(nextChar-1)!=' ') nextChar--;
            }
        }
        return count + nextChar/str.length();
    }

    @Test
    public void test(){
        String[] sentence = {"f","p","a"};
        System.out.println(wordsTyping(sentence, 8, 7));
    }
}
