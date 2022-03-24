package _All;

import java.util.*;
public class _1423_Maximum_Points_You_Can_Obtain_from_Cards {
    private int maxScore;
    public int maxScore(int[] cardPoints, int k) {
        maxScore = 0;
        backTrack(cardPoints, k, 0, cardPoints.length-1, 0);
        return maxScore;
    }

    private void backTrack(int[] cardPoints, int leftCards, int start, int end, int curScore){
        if(leftCards==0) {
            maxScore = Math.max(maxScore, curScore);
            return;
        }
        backTrack(cardPoints, leftCards-1, start+1, end, curScore+cardPoints[start]);
        backTrack(cardPoints, leftCards-1, start, end-1, curScore+cardPoints[end]);
    }

    /* way2: DP maybe?? */
    public int maxScore2(int[] cardPoints, int k) {
        int maxRes = 0;
        int len = cardPoints.length;

        int[] leftDP = new int[k];
        leftDP[0] = cardPoints[0];
        int[] rightDP = new int[k];
        rightDP[0] = cardPoints[len-1];
        for(int i=1; i<k; i++) {
            leftDP[i] = cardPoints[i] + leftDP[i-1];
            rightDP[i] = rightDP[i-1] + cardPoints[len-i-1];
        }
        for (int i=0; i<k-1; i++) {
            maxRes = Math.max(maxRes, leftDP[i]+rightDP[k-i-1]);
        }
        maxRes = Math.max(maxRes, leftDP[k-1]);
        maxRes = Math.max(maxRes, rightDP[k-1]);
        return maxRes;

    }
}
