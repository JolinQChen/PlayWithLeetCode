package _AAInterviews.Verkada;
import org.junit.Test;

import java.util.*;
public class ThresholdIntervals {

    class SampleNode {
        int timestamp;
        double value;
        public SampleNode (int t, double v) {
            this.timestamp = t;
            this.value = v;
        }
    }
    public List<int[]> intervalsAboveThreshold(List<SampleNode> samples, double interval){
        List<int[]> res = new ArrayList<>();
        int[] curInterval = new int[]{-1,-1};
        int size = samples.size();
        int idx = 0;
        while(idx<size) {
            if(samples.get(idx).value>=interval) {
                curInterval[0] = samples.get(idx).timestamp;
                while(idx<size && samples.get(idx).value>=interval) idx++;
                curInterval[1] = samples.get(idx-1).timestamp;
                res.add(new int[]{curInterval[0], curInterval[1]});
                curInterval = new int[]{-1,-1};
            } else idx++;
        }
        return res;
    }

    @Test
    public void test() {
        List<SampleNode> samples = new ArrayList<>();
        samples.add(new SampleNode(1,0.8));
        samples.add(new SampleNode(3,0.9));
        samples.add(new SampleNode(4,0.3));
        samples.add(new SampleNode(6,1.0));
        List<int[]> res = intervalsAboveThreshold(samples, 0.8);
        for(int[] r:res) {
            System.out.println(r[0]+", "+r[1]);
        }
    }
}
