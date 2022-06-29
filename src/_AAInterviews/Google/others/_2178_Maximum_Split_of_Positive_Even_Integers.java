package _AAInterviews.Google.others;
import java.util.*;
public class _2178_Maximum_Split_of_Positive_Even_Integers {
    public List<Long> maximumEvenSplit(long finalSum) {
        if(finalSum % 2 == 1) return new LinkedList<>();
        long sum = finalSum / 2;
        List<Long> res = new LinkedList<>();
        processSplit(sum, res);
        return res;
    }

    private void processSplit(long sum, List<Long> res) {
        if(sum <= Long.valueOf(2)) {
            res.add(sum*2);
            return;
        }
        // System.out.println(sum);
        if(res.size() == 0) {
            res.add(2 * Long.valueOf(1));
            processSplit(sum-1, res);
        }
        else {
            // compare current sum/2 to res.last
            long max = res.get(res.size()-1) / 2;
            if(sum / 2 <= max || sum == (max+Long.valueOf(1))*2) {
                res.add(sum * 2);
                return;
            }
            res.add((res.get(res.size()-1) / 2 + Long.valueOf(1)) * 2);
            processSplit(sum - res.get(res.size()-1) / 2, res);
        }
    }
}
