package _AAInterviews.Google;

import org.junit.Test;

import java.util.*;
public class RangeModule {
    // ??????? 不对
    List<int[]> list;
    public RangeModule() {
        list = new ArrayList<>(); // list is resorted in the process
    }

    public void addRange(int left, int right) {
        if(list.isEmpty()) list.add(new int[]{left, right});
        else {
            int[] toInsert = new int[]{left, right};
            List<int[]> merge = new ArrayList<>();
            for(int[] cur:list) {
                if(merge.size()==0 && toInsert[1]<cur[0] || merge.size()>0 && toInsert[1]<cur[0] && toInsert[0]>merge.get(merge.size()-1)[1]) {
                    // no overlap and can insert now
                    merge.add(toInsert);
                }
                if(toInsert[0]>cur[1] || cur[0] > toInsert[1]){
                    merge.add(cur);
                } else {
                    // has overlap
                    toInsert[0] = Math.min(cur[0], toInsert[0]);
                    toInsert[1] = Math.max(cur[1], toInsert[1]);
                }
            }
            if(merge.size()==0 || toInsert[0]> merge.get(merge.size()-1)[1]) merge.add(toInsert);
            list = merge;
        }
    }

    public boolean queryRange(int left, int right) {
        if(list.size()==0) return false;
        // binary search find left
        int pos = findPosLeft(left);
        if(pos==-1) return false;
        if(list.get(pos)[1]<right) return false;
        return true;
    }

    public void removeRange(int left, int right) {
        List<int[]> merge = new ArrayList<>();
        int left_pos = findPosLeft(left);
        int right_pos = findPosRight(right);
        if(left_pos >0) {
            for(int i=0; i<left_pos; i++) {
                merge.add(list.get(i));
            }
        }
        if(left_pos>=0 && list.get(left_pos)[0]<left) merge.add(new int[]{list.get(left_pos)[0], left});
        if(right_pos<list.size()) {
            if(list.get(right_pos)[1]>right+1) merge.add(new int[]{right, list.get(right_pos)[1]});
            for(int j=right_pos+1; j<list.size(); j++) {
                merge.add(list.get(j));
            }
        }
        list = merge;

    }

    private int findPosLeft(int val) {
        // find the max value that's smaller than or equals val in list[0]
        int l = 0, r = list.size()-1;
        while (l<=r) {
            int mid = l + (r-l)/2;
            if(list.get(mid)[0]==val) return mid;
            if(list.get(mid)[0]>val) r = mid-1;
            else {
                if(mid==r || list.get(mid+1)[0]>val) return mid;
                l = mid+1;
            }
        }
        return -1;
    }

    private int findPosRight(int val) {
        // find the min value that's larger than or equals val in list[1]
        int l = 0, r = list.size()-1;
        while (l<=r) {
            int mid = l + (r-l)/2;
            if(list.get(mid)[1]==val) return mid;
            if(list.get(mid)[1]<val) l = mid+1;
            else {
                if(mid==l || list.get(mid-1)[1]<val) return mid;
                r = mid-1;
            }
        }
        return list.size();
    }
    
    @Test
    public void test(){
        RangeModule rangeModule = new RangeModule();
        rangeModule.addRange(44,53);
        rangeModule.addRange(69,89);
        System.out.println(rangeModule.queryRange(23,26));
        System.out.println(rangeModule.queryRange(80,84));
//
//        rangeModule.removeRange(14,16);
//        System.out.println(rangeModule.queryRange(10,14));
//        System.out.println(rangeModule.queryRange(13,15));
//        System.out.println(rangeModule.queryRange(16,17));
//        for(int[] i:rangeModule.list) System.out.println(i[0]+"fuck"+i[1]);

    }
}
