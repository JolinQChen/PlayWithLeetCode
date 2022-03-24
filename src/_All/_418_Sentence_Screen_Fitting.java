package _All;

import org.junit.Assert;
import org.junit.Test;

public class _418_Sentence_Screen_Fitting {
    public int wordsTyping(String[] sentence, int rows, int cols) {
        int[] lenList = new int[sentence.length];
        int res = 0;
        int idx = 0;
        for(String word:sentence){
            lenList[idx++] = word.length();
            if(word.length()>cols) return 0;
        }
        int maxPos = rows * cols;
        idx = 0;
        int pos = 0; // the position to put the current character
        while (pos<maxPos) {
            System.out.println("pos: "+pos+", idx: "+idx);
            // check left spaces
            int leftSpace = cols - pos % cols;
            if(leftSpace>=lenList[idx]){
                // stay on the same line
                pos += lenList[idx];
            } else {
                // change a line
                pos = pos + leftSpace + lenList[idx];
            }
            idx++;
            if(pos<=maxPos && idx==sentence.length) {
                res++;
                idx = 0; // start another sentence
            }
            if(pos % cols != 0) {
                // instead of just reached the start of the line
                pos++;
            }
        }
        return res;
    }

    @Test
    public void test(){
        String[] sentence = {"a","b","c"};
        int res = wordsTyping(sentence, 3, 1);
        Assert.assertEquals(1,res);
    }
}
