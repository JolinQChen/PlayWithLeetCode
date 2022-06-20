package _AAInterviews.Google;

import java.util.HashMap;
import java.util.Map;

public class _2188_Minimum_Time_to_Finish_the_Race_WTF {
    long[][] dp;
    int minCost;
    int[] upMostLaps;
    int[] leastLaps;
    public int minimumFinishTime(int[][] tires, int changeTime, int numLaps) {
        upMostLaps = new int[tires.length]; // upMostLaps[i] -> the max lap that ith tire can go without change
        leastLaps = new int[tires.length];
        int tmp = Integer.MAX_VALUE;
        for(int i=0; i<tires.length; i++) {
            tmp = Math.min(tmp, tires[i][0]);
            int lap = 0;
            int cost = changeTime + tires[i][0];
            int curCost = tires[i][0];
            while (curCost<cost) {
                curCost *= tires[i][1];
                lap++;
            }
            upMostLaps[i] = lap;
        }
        minCost = tmp + changeTime;
        for(int i=0; i<tires.length; i++) {
            int lap = 0;
            int curCost = tires[i][0];
            while (curCost<minCost) {
                curCost *= tires[i][1];
                lap++;
            }
            leastLaps[i] = lap;
        }

        dp = new long[tires.length][numLaps]; // dp[i][j]: start with ith tire, to finish j laps
        for(int i=0; i<tires.length; i++) {
            dp[i][1] = tires[i][0];
            for(int j=2; i<=leastLaps[i]; j++) {
                dp[i][j] = dp[i][j-1] * tires[i][1];
            }
        }

        long minTime = (long) minCost * (numLaps-1) + tmp;
        for(int i=0; i<tires.length; i++) {
//            minTime = Math.min(minTime, dfsCountMin(numLaps, i, tires, changeTime));
        }
        return (int) minTime;
    }
//    private long dfsCountMin(int remainLaps, int start, int[][] tires, int changeTime){
//        if(remainLaps == 0) return 0
//        if(dp[start][remainLaps]>0) return dp[start][remainLaps];
//        else {
////            remainLaps -= leastLaps[start];
//            long preCost = dp[start][leastLaps[start]];
//            long nextCost = (long) minCost * (remainLaps-1) + minCost-changeTime;
//            for(int i=leastLaps[start]; i<=upMostLaps[start]; i++) {
//                // use the same tire for another round
//                nextCost = Math.min(nextCost, dfsCountMin(remainLaps-i, start, tires, changeTime));
//                // use a new tire for another round
//                nextCost = Math.min(nextCost, dfsCountMin(remainLaps))
//            }
//        }
//    }
}
