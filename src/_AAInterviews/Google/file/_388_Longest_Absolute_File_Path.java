package _AAInterviews.Google.file;

import org.junit.Test;

import java.util.Stack;

public class _388_Longest_Absolute_File_Path {
    public int lengthLongestPath(String input) {
        String[] layers = input.split("\n");
        Stack<String> stack = new Stack<>();
        int maxLen = 0;
        int totalLen = 0;
        for(int i=0; i<layers.length; i++) {
            int len = layers[i].length();
            int j=0;
            int curLayer = 0;
            while (j<len && layers[i].charAt(j)=='\t') {
                curLayer++;
                j++;
            }
            while(stack.size()>curLayer) {
                totalLen -= stack.pop().length();
            }
            String toAdd = layers[i].substring(j);
            stack.push(toAdd+"/");
            totalLen = totalLen + toAdd.length()+1;
            if(toAdd.split("\\.").length>1) {
                maxLen = Math.max(maxLen, totalLen-1);
            }
        }
        return maxLen;
    }

    @Test
    public void test() {
        String input = "a.tar.gz";
        System.out.println(lengthLongestPath(input));
    }
}
