package _All;
import java.util.*;
public class _1291_Sequential_Digits {
    public List<Integer> sequentialDigits(int low, int high) {
        int lowLen = String.valueOf(low).length();
        int highLen = String.valueOf(high).length();
        String sample = "123456789";
        List<Integer> res = new ArrayList<>();
        for(int len = lowLen; len<=highLen; len++) {
            for(int left = 0; left<10-len; left++) {
                int curVal = Integer.parseInt(sample.substring(left, left+len));
                if (curVal<low) continue;
                if(curVal>high) break;
                res.add(curVal);
            }
        }
        return res;

    }
}
