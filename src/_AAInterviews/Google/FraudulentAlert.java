package _AAInterviews.Google;

import java.util.*;
public class FraudulentAlert {
    static int[] num;
    public static int activityNotifications(List<Integer> expenditure, int d) {
        // Write your code here
        num = new int[201];
        int idx = 0;
        while (idx<d) {
            num[expenditure.get(idx)]++;
            idx++;
        }
        int res = 0;
        // how to get median from existing queue
        while (idx<expenditure.size()) {
            double mid = findMid(d);
            if(expenditure.get(idx)>=mid) res++;
            num[expenditure.get(idx-d)]--;
            idx++;
            num[expenditure.get(idx)]++;
        }
        return res;
    }

    private static double findMid(int d){
        int count = 0;
        if(d%2==0) {
            // find (num[d/2]+num[d/2-1])/2
            int pre = -1;
            int next = -1;
            for(int i=0; i<201; i++) {
                if(num[i]>0) {
                    count += num[i];
                    if (count == d / 2) {
                        pre = num[i];
                    }
                    if(count > d/2) {
                        if(pre<0) pre = num[i];
                        next = num[i];
                    }
                    if(pre>=0 && next>=0) break;
                }
            }
            return ((double) (pre+next)) / 2.0;
        } else {
            // find num[d/2]
            for(int i=0; i<201; i++) {
                if(num[i]>0) {
                    count += num[i];
                    if (count >= d / 2 + 1) return (double) num[i];
                }
            }
        }
        return (double) -1;
    }
}
