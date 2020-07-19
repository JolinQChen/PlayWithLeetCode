package _All;

import java.util.*;
public class _1229_Meeting_Scheduler {
    public List<Integer> minAvailableDuration(int[][] slots1, int[][] slots2, int duration) {
        int p1 = 0, p2 = 0;
        int len1 = slots1.length;
        int len2 = slots2.length;
        Arrays.sort(slots1, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0]-o2[0];
            }
        });
        Arrays.sort(slots2, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0]-o2[0];
            }
        });
        while(p1<len1 && p2<len2){
            //无交集情况
            if(slots1[p1][0]>=slots2[p2][1]) p2++;
            else if(slots1[p1][1]<=slots2[p2][0]) p1++;
            else {
                //有交集
                if(Math.min(slots1[p1][1],slots2[p2][1]) - Math.max(slots1[p1][0], slots2[p2][0])>=duration){
                    List<Integer> list = new ArrayList<>();
                    list.add(Math.max(slots1[p1][0], slots2[p2][0]));
                    list.add(Math.max(slots1[p1][0], slots2[p2][0])+duration);
                    return list;
                }
                else {
                    if(slots1[p1][1]>slots2[p2][1]) p2++;
                    else p1++;
                }
            }
        }
        return new ArrayList<>();
    }
    public static void main(String[] args){

    }
}
