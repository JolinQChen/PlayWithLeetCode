package _All;

import org.junit.Test;

import java.util.*;

public class _2007_Find_Original_Array_From_Doubled_Array {

    public int[] findOriginalArray(int[] changed) {
        int len = changed.length;
        if (len % 2 != 0)
            return new int[0];

        Arrays.sort(changed);
        int[] res = new int[len / 2];
        int resIdx = 0;
        int startIdx = 0;
        for(int doubleIdx = 0; doubleIdx<len; doubleIdx++) {
            if(doubleIdx!=startIdx && changed[doubleIdx] == changed[startIdx]*2) {
                res[resIdx++] = changed[startIdx];
                changed[doubleIdx]=-1;
                startIdx++;
                while (startIdx<len && changed[startIdx]==-1) startIdx++;
            }
            else if(changed[doubleIdx]>changed[startIdx]*2) return new int[]{};
        }

        return res.length == resIdx ? res : new int[]{};
    }

    @Test
    public void testLoop() {
        int i2 = 10;
        for(int i=i2; i>=0; i--) {
            if(i2<12) {
                i2++;
            }
            System.out.println("current i is: "+i + ", i2 is: "+i2);
        }
    }

}
