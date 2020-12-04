package _AAInterviews.Amazon;

import java.util.*;
public class PrisonCellsAfterNDays {
    public int[] prisonAfterNDays(int[] cells, int N) {
        List<int[]> cycle = new LinkedList<>();
        int begin = -1;
        if(N==0) return cells;
        int len = cells.length;
        cycle.add(cells.clone());
        int cycleLength = 0;
        while(N>0){
            int[] tmp = new int[8];
            tmp[0]=0;
            tmp[7]=0;

            for(int i=1; i<7; i++) {
                if((cells[i-1]==1 && cells[i+1]==1) || (cells[i-1]==0 && cells[i+1]==0) ) tmp[i]=1;
                else tmp[i]=0;
            }
            cycleLength = cycle.size();
            for(int ii=0; ii<cycleLength; ii++) {
                int flag = 1;
                for(int jj=0; jj<8; jj++) {
                    if(tmp[jj]!=cycle.get(ii)[jj]) {
                        flag=0;
                        break;
                    }
                }
                if(flag == 1) {
                    // find cycle begin point
                    begin = ii;
                    break;
                }
            }

            if(begin != -1) {
                // find cycle length
                cycleLength = cycle.size() - begin;
                //N--;
                break;
            }
            N--;
            cells = tmp.clone();
            cycle.add(tmp.clone());
        }
        int remainDays = N % cycleLength;
        if(remainDays == 0) return cycle.get(cycle.size()-1);
        else return cycle.get(begin+remainDays-1);

    }
}
