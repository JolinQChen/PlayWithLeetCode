package _AAInterviews.Google;
import java.util.*;
public class _274_H_Index {
    public int hIndex(int[] citations) {
        Arrays.sort(citations);
        int size = citations.length;
        for(int i=size-1; i>=0; i++) {
            if(size-i<=citations[i] && (i>0 && size-i>citations[i-1] || i==0)) return size-i;
        }
        return -1;
    }
}
