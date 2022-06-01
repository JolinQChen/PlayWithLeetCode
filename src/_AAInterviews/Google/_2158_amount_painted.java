package _AAInterviews.Google;
import java.util.*;

public class _2158_amount_painted {
    public int[] amountPainted(int[][] paint) {
        int[] res = new int[paint.length];
        List<int[]> sort_merged = new ArrayList<>();
        for(int i=0; i<paint.length; i++) {
            int[] to_be_insert = paint[i];
            List<int[]> merge_process = new ArrayList<>();
            int len = to_be_insert[1] - to_be_insert[0];
            // boolean inserted = false;
            for(int j=0; j<sort_merged.size(); j++) {
                int[] merge_interval = sort_merged.get(j);
                if(merge_process.size() == 0 && to_be_insert[1] < merge_interval[0] || merge_process.size()>0 && to_be_insert[0] > merge_process.get(merge_process.size()-1)[1] && to_be_insert[1] < merge_interval[0]) {
                    // see if this to_be_insert can be added without interruption
                    merge_process.add(to_be_insert);
                    // inserted = true;
                }
                // compare to_be_insert and merge_interval, who goes first or if any overlap exist
                if(merge_interval[1]<to_be_insert[0] || merge_interval[0]>to_be_insert[1]) {
                    merge_process.add(merge_interval);
                } else {
                    // has overlap
                    // shrink
                    int[] overlap = new int[2];
                    overlap[0] = Math.max(to_be_insert[0], merge_interval[0]);
                    overlap[1] = Math.min(to_be_insert[1], merge_interval[1]);
                    len = len - (overlap[1] - overlap[0]);
                    to_be_insert[0] = Math.min(to_be_insert[0], merge_interval[0]);
                    to_be_insert[1] = Math.max(to_be_insert[1], merge_interval[1]);
                }
            }
            if(merge_process.size() == 0 || merge_process.get(merge_process.size()-1)[1] < to_be_insert[0]) {
                merge_process.add(to_be_insert);
            }
            res[i] = len;
            sort_merged = merge_process;
        }
        return res;

    }
}
