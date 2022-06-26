package _AAInterviews.Google;

import java.util.*;

public class TwoCompeter {
    public int findMostPossibleWinner(int n, double[][] matrix) {
        int groups = (n+1)/2;
        double[] possibilities = new double[n+1];
        Arrays.fill(possibilities, 1.0);
        List<List<Integer>> list = new ArrayList<>();
        for(int i=1; i<=groups; i++) {
            List<Integer> tmp = new ArrayList<>();
            tmp.add(i);
            list.add(tmp);
        }
        while (groups>1) {
            groups = (groups+1)/2;
            List<List<Integer>> curList = new ArrayList<>();
            int idx = 0;
            for(int i=0; i<groups; i++) {
                List<Integer> tmp = new ArrayList<>();
                for(int j=0; j<list.get(idx).size(); j++) {
                    tmp.add(list.get(idx).get(j));
                }
                idx++;
                if(idx<list.size()) {
                    for(int j=0; j<list.get(idx).size(); j++) {
                        tmp.add(list.get(idx).get(j));
                    }
                }
                idx++;
                curList.add(tmp);
            }

            idx = 0;
            double[] nextLevelPossibilities = Arrays.copyOf(possibilities, n+1);
            while (idx<groups-1) {
                List<Integer> competeGroup_a = curList.get(idx);
                List<Integer> competeGroup_b = curList.get(idx+1);
                for(Integer a:competeGroup_a) {
                    double aWin = 0;
                    for(Integer b:competeGroup_b) {
                        aWin += possibilities[b] * matrix[a][b];
                    }
                    nextLevelPossibilities[a] = possibilities[a] * aWin;
                }

                for(Integer b:competeGroup_b) {
                    double bWin = 0;
                    for(Integer a:competeGroup_a) {
                        bWin += possibilities[a] * matrix[b][a];
                    }
                    nextLevelPossibilities[b] = possibilities[b] * bWin;
                }
                idx += 2;
            }

            possibilities = Arrays.copyOf(nextLevelPossibilities, n+1);
        }
        double res = 0;
        int idx = 0;
        for(int i=1; i<=n; i++) {
            if(possibilities[i]>res) {
                res = possibilities[i];
                idx = i;
            }
        }
        return idx;
    }
}
