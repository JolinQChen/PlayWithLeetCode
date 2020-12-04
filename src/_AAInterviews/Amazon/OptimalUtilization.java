package _AAInterviews.Amazon;

import java.util.*;
/*
    Input:
    a = [[1, 8], [2, 15], [3, 9]]
    b = [[1, 8], [2, 11], [3, 12]]
    target = 20

    Output: [[1, 3], [3, 2]]
* */

public class OptimalUtilization {
    private static List<int[]> getPair(List<int[]> a, List<int[]> b, int target) {
        List<int[]> res = new ArrayList<>();
        Collections.sort(a,(i,j)->i[1]-j[1]);
        Collections.sort(b,(i,j)->i[1]-j[1]);
        int max = Integer.MIN_VALUE;
        int a_len = a.size();
        int b_len = b.size();
        int a_idx = 0;
        int b_idx = b_len-1;
        while (a_idx<a_len && b_idx>=0) {
            int curSum = a.get(a_idx)[1] + b.get(b_idx)[1];
            if(curSum > target) {
                b_idx--;
            }
            else {
                if(curSum>=max) {
                    if(curSum>max){
                        max = curSum;
                        res = new ArrayList<>();
                    }
                    int nb = b.get(b_idx)[1];
                    int na = a.get(a_idx)[1];
                    int pa = a_idx;
                    while (b_idx>=0 && b.get(b_idx)[1] == nb) {
                        pa = a_idx;
                        while(pa<a_len && a.get(pa)[1] == na) {
                            res.add(new int[]{a.get(pa)[0], b.get(b_idx)[0]});
                            pa++;
                        }
                        b_idx--;
                    }
                    a_idx = pa;
                }
                else a_idx++;
            }
        }

        return res;
    }

    public static void main(String[] args) {

        List<int[]> a = new ArrayList<>();
        List<int[]> b = new ArrayList<>();

        a.add(new int[]{1,15});
        a.add(new int[]{2,8});
        a.add(new int[]{3,8});
        a.add(new int[]{4,9});


        b.add(new int[]{1,8});
        b.add(new int[]{2,11});
        b.add(new int[]{3,12});
        List<int[]> res = getPair(a,b,20);
        for(int[] p:res) {
            System.out.println(p[0]+" , "+p[1]);
        }
    }
}
